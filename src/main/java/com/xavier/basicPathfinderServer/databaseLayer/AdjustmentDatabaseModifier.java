package com.xavier.basicPathfinderServer.databaseLayer;

public class AdjustmentDatabaseModifier {
	private final static String DISABLE_ADJUSTMENT_QUERY = "DELETE FROM EnabledAdjustments WHERE AdjustmentID = ? AND CharacterID = ?;";
	private final static String ENABLE_ADJUSTMENT_QUERY = "INSERT INTO EnabledAdjustments VALUES (?, ?);";
	
	public static void disableAdjustment(int adjustmentId, int characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(DISABLE_ADJUSTMENT_QUERY, adjustmentId, characterId);
		db.close();
	}
	
	public static void enableAdjustment(int adjustmentId, int characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(ENABLE_ADJUSTMENT_QUERY, characterId, adjustmentId);
		db.close();
	}
}
