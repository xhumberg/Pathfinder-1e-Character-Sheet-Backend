package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import com.xavier.basicPathfinderServer.databaseLayer.DatabaseAccess;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.simpleMappers.SingleIntegerMapper;

public class AdjustmentDatabaseModifier {
	private final static String DISABLE_ADJUSTMENT_FOR_CHARACTER_QUERY = "DELETE FROM EnabledAdjustments WHERE AdjustmentID = ? AND CharacterID = ?;";
	private final static String ENABLE_ADJUSTMENT_FOR_CHARACTER_QUERY = "INSERT INTO EnabledAdjustments VALUES (?, ?);";
	private final static String DELETE_ALL_INSTANCES_OF_ADJUSTMENT_FROM_ENABLED = "DELETE FROM EnabledAdjustments WHERE AdjustmentID = ?";
	
	private final static String GET_LARGEST_STANDARD_ADJUSTMENT_ID = "SELECT AdjustmentID FROM StandardAdjustments ORDER BY AdjustmentID DESC LIMIT 1";
	private final static String ADD_STANDARD_ADJUSTMENT = "INSERT INTO StandardAdjustments VALUES (?, ?, ?)";
	private final static String DELETE_STANDARD_ADJUSTMENT = "DELETE FROM StandardAdjustments WHERE AdjustmentId = ?";
	
	private final static String ALLOW_CHARACTER_TO_USE_ADJUSTMENT = "INSERT INTO AllowedAdjustments values (?, ?)";
	private final static String DELETE_ALLOW_CHARACTER_TO_USE_ADJUSTMENT = "DELETE FROM AllowedAdjustments WHERE AdjustmentID = ? AND CharacterID = ?";
	private final static String DELETE_ALL_INSTANCES_OF_ADJUSTMENT_FROM_ALLOWED = "DELETE FROM AllowedAdjustments WHERE AdjustmentID = ?";
	
	public static void disableAdjustment(int adjustmentId, String characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(DISABLE_ADJUSTMENT_FOR_CHARACTER_QUERY, adjustmentId, characterId);
	}
	
	public static void enableAdjustment(int adjustmentId, String characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(ENABLE_ADJUSTMENT_FOR_CHARACTER_QUERY, characterId, adjustmentId);
	}
	
	public static int addNewAdjustment(String name, String effect) {
		DatabaseAccess<Integer> db = new DatabaseAccess<>();
		int adjustmentId = db.executeSelectQuery(new SingleIntegerMapper(), GET_LARGEST_STANDARD_ADJUSTMENT_ID) + 1;
		db.executeModifyQuery(ADD_STANDARD_ADJUSTMENT, adjustmentId, name, effect);
		return adjustmentId;
	}

	public static void deleteStandardAdjustment(int id) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(DELETE_ALL_INSTANCES_OF_ADJUSTMENT_FROM_ALLOWED, id);
		db.executeModifyQuery(DELETE_ALL_INSTANCES_OF_ADJUSTMENT_FROM_ENABLED, id);
		db.executeModifyQuery(DELETE_STANDARD_ADJUSTMENT, id);
	}
	
	public static void allowCharacterToUseAdjustment(int adjustmentId, String characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(ALLOW_CHARACTER_TO_USE_ADJUSTMENT, characterId, adjustmentId);
	}
	
	public static void removeAllowCharacterToUseAdjustment(int adjustmentId, String characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(DELETE_ALLOW_CHARACTER_TO_USE_ADJUSTMENT, adjustmentId, characterId);
	}
}
