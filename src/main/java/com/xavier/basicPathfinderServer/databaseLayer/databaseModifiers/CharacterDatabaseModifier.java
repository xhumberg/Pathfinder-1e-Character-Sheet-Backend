package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import com.xavier.basicPathfinderServer.databaseLayer.DatabaseAccess;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.simpleMappers.DoesAResultExistMapper;
import com.xavier.basicPathfinderServer.stringTools.StringTools;

public class CharacterDatabaseModifier {
	private final static String ADD_NEW_CHARACTER = "INSERT INTO PathfinderCharacter VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String ASSERT_NO_CHARACTER_WITH_STRING_EXISTS = "SELECT * FROM PathfinderCharacter WHERE CharacterID = ?";
	private final static String DELETE_CHARACTER = "DELETE FROM PathfinderCharacter WHERE CharacterID = ?";
	
	public static String addNewCharacter(String name, String player, String image, String alignment, String race, String size, String gender, String age, String weight, String height, int baseStr, int baseDex, int baseCon, int baseInt, int baseWis, int baseCha) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		String randomString;
		do {
			randomString = StringTools.generateRandomCharacterId();
		} while (((Boolean)db.executeSelectQuery(new DoesAResultExistMapper(), ASSERT_NO_CHARACTER_WITH_STRING_EXISTS, randomString))); //TODO: Add a failout state
		db.executeModifyQuery(ADD_NEW_CHARACTER, randomString, name, player, image, alignment, race, size, gender, age, weight, height, baseStr, baseDex, baseCon, baseInt, baseWis, baseCha);
		return randomString;
	}
	
	public static void deleteCharacter(String characterId) {
		DatabaseAccess<Boolean> db = new DatabaseAccess<>();
		db.executeModifyQuery(DELETE_CHARACTER, characterId);
	}
}
