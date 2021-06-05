package com.xavier.basicPathfinderServer.databaseLayer;

public class HealthDatabaseModifier {
	private final static String SET_DAMAGE_TAKEN_QUERY = "UPDATE CharacterHP SET DamageTaken = ? WHERE CharacterID = ?;";
	
	public static void setDamageTaken(int damageTaken, String id) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(SET_DAMAGE_TAKEN_QUERY, damageTaken, id);
		db.close();
	}
	
}
