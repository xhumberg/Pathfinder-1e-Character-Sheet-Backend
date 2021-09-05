package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import com.xavier.basicPathfinderServer.databaseLayer.DatabaseAccess;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.simpleMappers.SingleIntegerMapper;

public class ClassDatabaseModifier {
	
	private final static String GET_LARGEST_AVAILABLE_CLASS_ID = "SELECT ClassID FROM AvailableClasses ORDER BY ClassID DESC LIMIT 1";
	private final static String ADD_CLASS = "INSERT INTO AvailableClasses VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final static String DELETE_CLASS = "DELETE FROM AvailableClasses WHERE ClassID = ?";
	
	private final static String GIVE_CHARACTER_CLASS = "INSERT INTO CharacterClasses values (?, ?)";
	private final static String TAKE_CHARACTER_CLASS = "DELETE FROM CharacterClasses WHERE ClassID = ? AND CharacterID = ?";
	private final static String DELETE_ALL_INSTANCES_OF_CLASS_FROM_CHARACTERS = "DELETE FROM CharacterClasses WHERE ClassID = ?";

	public static int addNewClassWithoutSpellcasting(int level, int bab, int fort, int ref, int will, int ranksPerLevel, int hitDice, String className) {
		return addNewClass(level, bab, fort, ref, will, ranksPerLevel, hitDice, className, false, null, -1, null, null, null);
	}
	
	public static int addNewClass(int level, int bab, int fort, int ref, int will, int ranksPerLevel, int hitDice, String className, boolean spellcasting, String spellcastingType, int casterLevel, String spellcastingAbility, String spellsPerDay, String spellsKnown) {
		DatabaseAccess<Integer> db = new DatabaseAccess<>();
		int ClassID = db.executeSelectQuery(new SingleIntegerMapper(), GET_LARGEST_AVAILABLE_CLASS_ID) + 1;
		db.executeModifyQuery(ADD_CLASS, ClassID, level, bab, fort, ref, will, ranksPerLevel, hitDice, className, spellcasting, spellcastingType, casterLevel, spellcastingAbility, spellsPerDay, spellsKnown);
		db.close();
		return ClassID;
	}

	public static void deleteClass(int id) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(DELETE_ALL_INSTANCES_OF_CLASS_FROM_CHARACTERS, id);
		db.executeModifyQuery(DELETE_CLASS, id);
		db.close();
	}
	
	public static void giveClassToCharacter(int ClassID, String characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(GIVE_CHARACTER_CLASS, characterId, ClassID);
		db.close();
	}
	
	public static void takeClassFromCharacter(int ClassID, String characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(TAKE_CHARACTER_CLASS, ClassID, characterId);
		db.close();
	}
}
