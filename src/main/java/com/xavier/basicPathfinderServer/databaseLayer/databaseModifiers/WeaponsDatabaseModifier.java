package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import com.xavier.basicPathfinderServer.databaseLayer.DatabaseAccess;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.simpleMappers.SingleIntegerMapper;

public class WeaponsDatabaseModifier {
	
	private final static String GET_LARGEST_AVAILABLE_WEAPON_ID = "SELECT weaponId FROM WeaponDefinitions ORDER BY weaponId DESC LIMIT 1";
	private final static String ADD_WEAPON = "INSERT INTO WeaponDefinitions VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String DELETE_WEAPON = "DELETE FROM WeaponDefinitions WHERE weaponId = ?";
	
	private final static String GIVE_CHARACTER_WEAPON = "INSERT INTO CharacterWeapons values (?, ?, ?, ?)";
	private final static String TAKE_CHARACTER_WEAPON = "DELETE FROM CharacterWeapons WHERE weaponId = ? AND CharacterID = ?";
	private final static String DELETE_ALL_INSTANCES_OF_WEAPON_FROM_CHARACTERS = "DELETE FROM CharacterWeapons WHERE weaponId = ?";

	public static int addNewWeapon(String name, String damageDice, String damageType, String modifiers, int attackMod, int damageMod, int critLow, int critMultiplier, String description, String category, String proficiency, String special, int weight, String weaponGroups) {
		DatabaseAccess<Integer> db = new DatabaseAccess<>();
		int weaponId = db.executeSelectQuery(new SingleIntegerMapper(), GET_LARGEST_AVAILABLE_WEAPON_ID) + 1;
		db.executeModifyQuery(ADD_WEAPON, weaponId, name, damageDice, damageType, modifiers, attackMod, damageMod, critLow, critMultiplier, description, category, proficiency, special, weight, weaponGroups);
		db.close();
		return weaponId;
	}

	public static void deleteWeapon(int id) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(DELETE_ALL_INSTANCES_OF_WEAPON_FROM_CHARACTERS, id);
		db.executeModifyQuery(DELETE_WEAPON, id);
		db.close();
	}
	
	public static void giveWeaponToCharacter(int weaponId, String characterId, String attackStat, String damageStat) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(GIVE_CHARACTER_WEAPON, characterId, weaponId, attackStat, damageStat);
		db.close();
	}
	
	public static void takeWeaponFromCharacter(int weaponId, String characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(TAKE_CHARACTER_WEAPON, weaponId, characterId);
		db.close();
	}
}
