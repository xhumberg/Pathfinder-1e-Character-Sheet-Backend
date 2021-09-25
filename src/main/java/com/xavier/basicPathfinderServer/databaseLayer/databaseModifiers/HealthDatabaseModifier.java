package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import com.xavier.basicPathfinderServer.databaseLayer.DatabaseAccess;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.simpleMappers.DoesAResultExistMapper;

public class HealthDatabaseModifier {
	private final static String SET_DAMAGE_TAKEN = "UPDATE CharacterHP SET DamageTaken = ? WHERE CharacterID = ?;";
	private final static String SET_NL_DAMAGE_TAKEN = "UPDATE CharacterHP SET NonLethalDamageTaken = ? WHERE CharacterID = ?";
	private final static String SET_CLASS_BONUS_HP = "UPDATE CharacterHP SET FavoredClassBonusHP = ? WHERE CharacterID = ?";
	private final static String SET_TEMP_HP = "UPDATE CharacterHP SET TempHP = ? WHERE CharacterID = ?";
	
	private final static String GET_HP_VALUES = "Select * from CharacterHP WHERE CharacterID = ?";
	private final static String ADD_HP_VALUES = "INSERT INTO CharacterHP VALUES (?, 0, 0, 0, 0)";
	private final static String REMOVE_HP_VALUES = "DELETE FROM CharacterHP WHERE CharacterID = ?";
	
	public static void setDamageTaken(int damageTaken, String characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		ensureCharacterHpValuesExist(db, characterId);
		db.executeModifyQuery(SET_DAMAGE_TAKEN, damageTaken, characterId);
	}

	public static void setNonlethalDamageTaken(int damageTaken, String characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		ensureCharacterHpValuesExist(db, characterId);
		db.executeModifyQuery(SET_NL_DAMAGE_TAKEN, damageTaken, characterId);
	}

	public static void setClassBonusHP(int bonusHp, String characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		ensureCharacterHpValuesExist(db, characterId);
		db.executeModifyQuery(SET_CLASS_BONUS_HP, bonusHp, characterId);
	}

	public static void setTempHp(int tempHp, String characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		ensureCharacterHpValuesExist(db, characterId);
		db.executeModifyQuery(SET_TEMP_HP, tempHp, characterId);
	}
	
	protected static void test_deleteCharacterHp(String characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(REMOVE_HP_VALUES, characterId);
	}

	private static void ensureCharacterHpValuesExist(DatabaseAccess<Object> db, String characterId) {
		Boolean exists = (Boolean) db.executeSelectQuery(new DoesAResultExistMapper(), GET_HP_VALUES, characterId);
		if (!exists) {
			db.executeModifyQuery(ADD_HP_VALUES, characterId);
		}
	}
}
