package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import com.xavier.basicPathfinderServer.databaseLayer.DatabaseAccess;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.simpleMappers.DoesAResultExistMapper;

public class CharacterWealthDatabaseModifier {
	
	private static final String GET_WEALTH = "SELECT * FROM CharacterWealth WHERE CharacterID = ?";
	private static final String ADD_WEALTH = "INSERT INTO CharacterWealth VALUES (?, ?, ?)";
	private static final String SET_WEALTH = "UPDATE CharacterWealth SET EarnedGold = ?, SpentGold = ? WHERE CharacterID = ?";
	
	public static void setWealth(String characterid, int totalEarned, int totalSpent) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		Boolean exists = (Boolean) db.executeSelectQuery(new DoesAResultExistMapper(), GET_WEALTH, characterid);
		if (exists) {
			db.executeModifyQuery(SET_WEALTH, totalEarned, totalSpent, characterid);
		} else {
			db.executeModifyQuery(ADD_WEALTH, characterid, totalEarned, totalSpent);
		}
		db.close();
	}
	
	protected static void test_deleteWealth(String characterid) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery("DELETE FROM CharacterWealth WHERE characterId = ?", characterid);
		db.close();
	}
}
