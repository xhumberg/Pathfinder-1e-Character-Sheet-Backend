package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import com.xavier.basicPathfinderServer.databaseLayer.DatabaseAccess;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.simpleMappers.DoesAResultExistMapper;

public class SpellDatabaseModifier {
	//TODO: Add ability to add and remove custom spells
	
	private final static String ADD_SPELL_KNOWN = "INSERT INTO SpellsKnown VALUES (?, ?, ?)";
	private final static String REMOVE_SPELL_KNOWN = "DELETE FROM SpellsKnown WHERE CharacterID = ? AND SpellID = ? AND ClassID = ?";
	private final static String IS_SPELL_KNOWN = "Select * FROM SpellsKnown WHERE CharacterID = ? AND SpellID = ? AND ClassID = ?";
	
	private final static String ADD_SPELL_PREPPED = "INSERT INTO SpellsPrepped VALUES (?, ?, ?, ?)";
	private final static String REMOVE_SPELL_PREPPED = "DELETE FROM SpellsPrepped WHERE ctid IN (SELECT ctid FROM SpellsPrepped WHERE CharacterID=? AND SpellID=? AND SpellLevel=? AND ClassID=? LIMIT 1)";
	
	private final static String CAST_SPELL = "INSERT INTO SpellsCast VALUES (?, ?, ?, ?);";
	private final static String UNCAST_SPELL = "DELETE FROM SpellsCast WHERE ctid IN (SELECT ctid FROM SpellsCast WHERE CharacterID=? AND SpellID=? AND SpellLevel=? AND ClassID=? LIMIT 1)";
	
	public static void addSpellKnown(String characterId, int spellId, int classId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(ADD_SPELL_KNOWN, characterId, spellId, classId);
		db.close();
	}
	
	public static void removeSpellKnown(String characterId, int spellId, int classId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(REMOVE_SPELL_KNOWN, characterId, spellId, classId);
		db.close();
	}
	
	public static void addSpellPrepped(String characterId, int spellId, int classId, int spellLevel) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		Boolean exists = (Boolean)db.executeSelectQuery(new DoesAResultExistMapper(), IS_SPELL_KNOWN, characterId, spellId, classId);
		if (!exists) {
			db.executeModifyQuery(ADD_SPELL_KNOWN, characterId, spellId, classId);
		}
		db.executeModifyQuery(ADD_SPELL_PREPPED, characterId, spellId, spellLevel, classId);
		db.close();
	}
	
	public static void removeSpellPrepped(String characterId, int spellId, int classId, int spellLevel) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(REMOVE_SPELL_PREPPED, characterId, spellId, spellLevel, classId);
		db.close();
	}
	
	public static void castSpell(String characterId, int spellId, int spellLevel, int classId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(CAST_SPELL, characterId, spellId, spellLevel, classId);
		db.close();
	}
	
	public static void uncastSpell(String id, int spellId, int spellLevel, int classId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(UNCAST_SPELL, id, spellId, spellLevel, classId);
		db.close();
	}
	
}
