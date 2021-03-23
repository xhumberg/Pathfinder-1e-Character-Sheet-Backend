package com.xavier.basicPathfinderServer.ResultSetMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xavier.basicPathfinderServer.PathfinderCharacter;

public class PathfinderCharacterMapper implements ResultSetMapper<Object> {

	@Override
	public PathfinderCharacter map(ResultSet resultSet) {
		try {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new PathfinderCharacter("Error: couldn't find character", "");
	}

}
