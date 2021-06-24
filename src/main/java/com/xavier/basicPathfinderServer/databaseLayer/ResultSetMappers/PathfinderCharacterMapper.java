package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xavier.basicPathfinderServer.PathfinderCharacter;

public class PathfinderCharacterMapper implements ResultSetMapper<Object> {

	@Override
	public PathfinderCharacter map(ResultSet resultSet) {
		try {
			if (resultSet.next()) {
				String characterId = resultSet.getString("CharacterID");
				String characterName = resultSet.getString("CharacterName");
				String playerName = resultSet.getString("PlayerName");
				String characterImageUrl = resultSet.getString("CharacterImageURL");
				String alignment = resultSet.getString("Alignment");
				String race = resultSet.getString("Race");
				String characterSize = resultSet.getString("CharacterSize");
				String gender = resultSet.getString("Gender");
				String age = resultSet.getString("Age");
				String weight = resultSet.getString("Weight");
				String height = resultSet.getString("Height");
				int str = resultSet.getInt("Strength");
				int dex = resultSet.getInt("Dexterity");
				int con = resultSet.getInt("Constitution");
				int intel = resultSet.getInt("Intelligence");
				int wis = resultSet.getInt("Wisdom");
				int cha = resultSet.getInt("Charisma");
				
				PathfinderCharacter character = new PathfinderCharacter(characterId, characterName, characterImageUrl);
				character.setPlayer(playerName);
				character.setRace(race);
				character.setSize(characterSize);
				character.setGender(gender);
				character.setAge(age);
				character.setWeight(weight);
				character.setHeight(height);
				character.setAlignment(alignment);
				character.setAbility("Strength", str);
				character.setAbility("Dexterity", dex);
				character.setAbility("Constitution", con);
				character.setAbility("Intelligence", intel);
				character.setAbility("Wisdom", wis);
				character.setAbility("Charisma", cha);
				return character;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new PathfinderCharacter("-1", "Error: couldn't find character", "");
	}

}
