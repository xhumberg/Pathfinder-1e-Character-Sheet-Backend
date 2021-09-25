package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import com.xavier.basicPathfinderServer.databaseLayer.DatabaseAccess;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.simpleMappers.SingleIntegerMapper;

public class ClassFeatureDatabaseModifier {
	
	private final static String GET_LARGEST_AVAILABLE_FEATURE_ID = "SELECT FeatureID FROM AvailableClassFeatures ORDER BY FeatureID DESC LIMIT 1";
	private final static String ADD_FEATURE = "INSERT INTO AvailableClassFeatures VALUES (?, ?, ?, ?)";
	private final static String DELETE_FEATURE = "DELETE FROM AvailableClassFeatures WHERE FeatureID = ?";
	
	private final static String GIVE_CHARACTER_FEATURE = "INSERT INTO CharacterClassFeatures values (?, ?, ?)";
	private final static String TAKE_CHARACTER_FEATURE = "DELETE FROM CharacterClassFeatures WHERE FeatureID = ? AND CharacterID = ?";
	private final static String DELETE_ALL_INSTANCES_OF_FEATURE_FROM_CHARACTERS = "DELETE FROM CharacterClassFeatures WHERE FeatureID = ?";

	public static int addNewFeature(String name, String description, String adjustment) {
		DatabaseAccess<Integer> db = new DatabaseAccess<>();
		int FeatureID = db.executeSelectQuery(new SingleIntegerMapper(), GET_LARGEST_AVAILABLE_FEATURE_ID) + 1;
		db.executeModifyQuery(ADD_FEATURE, FeatureID, name, description, adjustment);
		return FeatureID;
	}

	public static void deleteFeature(int id) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(DELETE_ALL_INSTANCES_OF_FEATURE_FROM_CHARACTERS, id);
		db.executeModifyQuery(DELETE_FEATURE, id);
	}
	
	public static void giveFeatureToCharacter(int FeatureID, String characterId, int trackedResourceId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(GIVE_CHARACTER_FEATURE, characterId, FeatureID, trackedResourceId);
	}
	
	public static void takeFeatureFromCharacter(int FeatureID, String characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(TAKE_CHARACTER_FEATURE, FeatureID, characterId);
	}
}
