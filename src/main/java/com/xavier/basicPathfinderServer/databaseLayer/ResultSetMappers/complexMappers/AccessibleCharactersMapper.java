package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.ResultSetMapper;
import com.xavier.basicPathfinderServer.json.LoadCharacterJson;

public class AccessibleCharactersMapper implements ResultSetMapper<List<String>> {

	@Override
	public List<String> map(ResultSet resultSet) {
		Gson gson = new Gson();
		List<String> availableCharacters = new LinkedList<>();
		try {
			while (resultSet.next()) {
				String characterName = resultSet.getString("CharacterName");
				String characterID = resultSet.getString("CharacterID");
				LoadCharacterJson loadCharacterJson = new LoadCharacterJson(characterName, characterID);
				availableCharacters.add(gson.toJson(loadCharacterJson));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return availableCharacters;
	}

}
