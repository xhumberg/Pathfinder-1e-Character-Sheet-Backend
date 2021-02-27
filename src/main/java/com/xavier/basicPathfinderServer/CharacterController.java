package com.xavier.basicPathfinderServer;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

@RestController
public class CharacterController {

	PathfinderCharacter character;
	Gson gson;
	
	private final String GET_CHARACTERS_FOR_USER_QUERY = "SELECT CharacterName, PathfinderCharacter.CharacterID FROM UserIDToEmail INNER JOIN UserAccess ON UserIDToEmail.UserID = UserAccess.UserID INNER JOIN PathfinderCharacter ON UserAccess.CharacterID = PathfinderCharacter.CharacterID WHERE UserIDToEmail.UserEmail = (?) OR UserAccess.UserID = -1;";
	private final String GET_CHARACTER_QUERY = "SELECT * FROM PathfinderCharacter WHERE CharacterID = ?";
	
	@Autowired
	public CharacterController() {
		character = Prosopa.get();
		gson = new Gson();
	}
	
	@GetMapping("/character/{id}")
	public String getProsopa(@PathVariable String id, @RequestParam(required = false) String token) {
		if (id.equals("prosopa")) {
			System.out.println("Fetching default Prosopa");
			Gson gson = new Gson();
			return gson.toJson(character.convertToJson());
		} else if (token == null){
			PathfinderCharacter character = new PathfinderCharacter("Error: cannot access character without logging in", "");
			return gson.toJson(character.convertToJson());
		} else {
			System.out.println("Request to get character id " + id);
			PathfinderCharacter character = loadCharacterID(id, token);
			return gson.toJson(character.convertToJson());
		}
	}
	
	private PathfinderCharacter loadCharacterID(String id, String token) {
		try {
			GoogleAuthenticationResponseJson authenticatedGoogleToken = authenticateToken(token);
			System.out.println(authenticatedGoogleToken.getEmail() + " wants to load character " + id);
			
			Connection databaseConnection = null;
			try {
				databaseConnection = establishConnectionToDatabase();
				
				ResultSet resultSet = queryDb(databaseConnection, GET_CHARACTER_QUERY, Integer.parseInt(id));
				PathfinderCharacter loadedCharacter = convertResultsToCharacter(resultSet);
				resultSet.close();
				
				System.out.println("Loaded character " + id + ", " + loadedCharacter.name);
				return loadedCharacter;
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeConnectionToDatabase(databaseConnection);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new PathfinderCharacter("Error: Couldn't load character", "https://www.aautomate.com/images/easyblog_shared/November_2018/11-12-18/human_error_stop_400.png");
	}
	
	private PathfinderCharacter convertResultsToCharacter(ResultSet resultSet) throws SQLException {
		if (resultSet.next()) {
			String characterName = resultSet.getString("CharacterName");
			String characterImageUrl = resultSet.getString("CharacterImageURL");
			int str = resultSet.getInt("Strength");
			int dex = resultSet.getInt("Dexterity");
			int con = resultSet.getInt("Constitution");
			int intel = resultSet.getInt("Intelligence");
			int wis = resultSet.getInt("Wisdom");
			int cha = resultSet.getInt("Charisma");
			
			PathfinderCharacter character = new PathfinderCharacter(characterName, characterImageUrl);
			character.setAbility("Strength", str);
			character.setAbility("Dexterity", dex);
			character.setAbility("Constitution", con);
			character.setAbility("Intelligence", intel);
			character.setAbility("Wisdom", wis);
			character.setAbility("Charisma", cha);
			return character;
		}
		return new PathfinderCharacter("Error: couldn't find character", "");
	}

	@GetMapping("character/load")
	public String loadFromDatabase(@RequestParam String token) throws URISyntaxException, ClassNotFoundException {
		//TODO: query to make sure the user has access permission instead of just assuming they do
		GoogleAuthenticationResponseJson authenticatedGoogleToken = authenticateToken(token);
		System.out.println("Load characters for " + authenticatedGoogleToken.getEmail());
		
		Connection databaseConnection = null;
        try {
        	databaseConnection = establishConnectionToDatabase();
        	
        	ResultSet resultSet = queryDb(databaseConnection, GET_CHARACTERS_FOR_USER_QUERY, authenticatedGoogleToken.getEmail());
        	List<String> availableCharacters = convertResultsToAvailableCharactersJsons(resultSet);
        	resultSet.close();
        	
        	System.out.println(authenticatedGoogleToken.getEmail() + " can access " + availableCharacters);
        	return gson.toJson(availableCharacters);
        } catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnectionToDatabase(databaseConnection);
		}
        //If error
		return "[]";
	}

	private void closeConnectionToDatabase(Connection conn) {
		System.out.println("Connection to database closed");
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {}
	}

	private List<String> convertResultsToAvailableCharactersJsons(ResultSet resultSet) throws SQLException {
		List<String> availableCharacters = new LinkedList<>();
		while (resultSet.next()) {
			String characterName = resultSet.getString("CharacterName");
			int characterID = resultSet.getInt("CharacterID");
			LoadCharacterJson loadCharacterJson = new LoadCharacterJson(characterName, characterID);
			availableCharacters.add(gson.toJson(loadCharacterJson));
		}
		return availableCharacters;
	}
	
	private ResultSet queryDb(Connection conn, String query, Object ...queryParams) throws SQLException {
		conn.setAutoCommit(false);
		PreparedStatement statement = conn.prepareStatement(query);
		for (int i = 0; i < queryParams.length; i++) {
			if (queryParams[i] instanceof String) {
				statement.setString(i+1, (String)queryParams[i]);
			} else if (queryParams[i] instanceof Integer) {
				statement.setInt(i+1, (Integer)queryParams[i]);
			} else {
				System.out.println("ERROR: statement tried to set unsupported type " + queryParams[i].getClass());
			}
		}
		System.out.println("Executing statement: " + statement.toString());
		
		ResultSet result = statement.executeQuery();
		return result;
	}
	
	private Connection establishConnectionToDatabase() throws URISyntaxException, ClassNotFoundException, SQLException {
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        if (connection.isValid(1000)) {
        	System.out.println("Successfully connected to database");
        } else {
        	System.out.println("Did not connect to database. Check credentials.");
        }
        return connection;
	}

	private GoogleAuthenticationResponseJson authenticateToken(String tokenString) {
		//Authenticate Token
		RestTemplate restTemplate = new RestTemplate();
		String urlToHit = "https://oauth2.googleapis.com/tokeninfo?id_token=" + tokenString.replace("\"", "");
		ResponseEntity<String> response = restTemplate.getForEntity(urlToHit, String.class);
		GoogleAuthenticationResponseJson responseJson = gson.fromJson(response.getBody(), GoogleAuthenticationResponseJson.class);
		return responseJson;
	}
	
	//TODO: Once adjustments are in the database, generalize this
	@PutMapping("/character/prosopa/toggle/{adjustmentName}") 
	public void toggleAdjustment(@PathVariable String adjustmentName) {
		System.out.println("Time to toggle " + adjustmentName);
		character.toggleAdjustment(adjustmentName);
	}
	
}