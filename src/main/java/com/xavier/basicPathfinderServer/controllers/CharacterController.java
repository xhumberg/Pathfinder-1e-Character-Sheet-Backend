package com.xavier.basicPathfinderServer.controllers;

import java.net.URISyntaxException;
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
import com.xavier.basicPathfinderServer.GoogleAuthenticationResponseJson;
import com.xavier.basicPathfinderServer.PathfinderCharacter;
import com.xavier.basicPathfinderServer.Prosopa;
import com.xavier.basicPathfinderServer.ResultSetMappers.AccessibleCharactersMapper;
import com.xavier.basicPathfinderServer.databaseLayer.CharacterFromDatabaseLoader;
import com.xavier.basicPathfinderServer.databaseLayer.DatabaseAccess;

@RestController
public class CharacterController {

	PathfinderCharacter defaultProsopa;
	Gson gson;
	
	private final String GET_CHARACTERS_FOR_USER_QUERY = "SELECT CharacterName, PathfinderCharacter.CharacterID FROM UserIDToEmail INNER JOIN UserAccess ON UserIDToEmail.UserID = UserAccess.UserID INNER JOIN PathfinderCharacter ON UserAccess.CharacterID = PathfinderCharacter.CharacterID WHERE UserIDToEmail.UserEmail = (?) OR UserAccess.UserID = -1;";
	
	@Autowired
	public CharacterController() {
		defaultProsopa = Prosopa.get();
		gson = new Gson();
	}
	
	@GetMapping("/character/{id}")
	public String getProsopa(@PathVariable String id, @RequestParam(required = false) String token) {
		if (id.equals("prosopa")) {
			System.out.println("Fetching default Prosopa");
			Gson gson = new Gson();
			return gson.toJson(defaultProsopa.convertToJson());
		} else if (token == null){
			PathfinderCharacter character = new PathfinderCharacter(-1, "Error: cannot access character without logging in", "");
			return gson.toJson(character.convertToJson());
		} else {
			System.out.println("Request to get character id " + id);
			PathfinderCharacter character = loadCharacterID(id, token);
			return gson.toJson(character.convertToJson());
		}
	}
	
	private PathfinderCharacter loadCharacterID(String id, String token) {
		GoogleAuthenticationResponseJson authenticatedGoogleToken = authenticateToken(token);
		System.out.println(authenticatedGoogleToken.getEmail() + " wants to load character " + id);
		
		return CharacterFromDatabaseLoader.loadCharacter(id);
	}

	@GetMapping("character/load")
	public String loadFromDatabase(@RequestParam String token) throws URISyntaxException, ClassNotFoundException {
		//TODO: query to make sure the user has access permission instead of just assuming they do
		GoogleAuthenticationResponseJson authenticatedGoogleToken = authenticateToken(token);
		System.out.println("Load characters for " + authenticatedGoogleToken.getEmail());
		
        DatabaseAccess<List<String>> db = new DatabaseAccess<>();
        List<String> response = db.executeSelectQuery(new AccessibleCharactersMapper(), GET_CHARACTERS_FOR_USER_QUERY, authenticatedGoogleToken.getEmail());
        db.close();
        if (response != null) {
        	return gson.toJson(response);
        }
        //If error
		return "[]";
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
		defaultProsopa.toggleAdjustment(adjustmentName);
	}
	
}