package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import com.xavier.basicPathfinderServer.databaseLayer.DatabaseAccess;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.simpleMappers.SingleIntegerMapper;

public class FeatDatabaseModifier {
	
	private final static String GET_LARGEST_AVAILABLE_FEAT_ID = "SELECT FeatID FROM AvailableFeats ORDER BY FeatID DESC LIMIT 1";
	private final static String ADD_FEAT = "INSERT INTO AvailableFeats VALUES (?, ?, ?, ?)";
	private final static String DELETE_FEAT = "DELETE FROM AvailableFeats WHERE FeatID = ?";
	
	private final static String GIVE_CHARACTER_FEAT = "INSERT INTO CharacterFeats values (?, ?)";
	private final static String TAKE_CHARACTER_FEAT = "DELETE FROM CharacterFeats WHERE featID = ? AND CharacterID = ?";
	private final static String DELETE_ALL_INSTANCES_OF_FEAT_FROM_CHARACTERS = "DELETE FROM CharacterFeats WHERE featID = ?";

	public static int addNewFeat(String name, String description, String adjustment) {
		DatabaseAccess<Integer> db = new DatabaseAccess<>();
		int featId = db.executeSelectQuery(new SingleIntegerMapper(), GET_LARGEST_AVAILABLE_FEAT_ID) + 1;
		db.executeModifyQuery(ADD_FEAT, featId, name, description, adjustment);
		db.close();
		return featId;
	}

	public static void deleteFeat(int id) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(DELETE_ALL_INSTANCES_OF_FEAT_FROM_CHARACTERS, id);
		db.executeModifyQuery(DELETE_FEAT, id);
		db.close();
	}
	
	public static void giveFeatToCharacter(int featId, String characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(GIVE_CHARACTER_FEAT, characterId, featId);
		db.close();
	}
	
	public static void takefeatFromCharacter(int featId, String characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(TAKE_CHARACTER_FEAT, featId, characterId);
		db.close();
	}
}
