package com.xavier.basicPathfinderServer.databaseLayer;

public class SpellDatabaseModifier {
	private final static String CAST_SPELL = "INSERT INTO SpellsCast VALUES (?, ?, ?, ?);";
	private final static String UNCAST_SPELL = "DELETE FROM SpellsCast WHERE CharacterID=? AND SpellID=? AND SpellLevel=? AND ClassID=?;";
	
	public static void castSpell(int characterId, int spellId, int spellLevel, int classId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(CAST_SPELL, characterId, spellId, spellLevel, classId);
		db.close();
	}
	
	public static void uncastSpell(int characterId, int spellId, int spellLevel, int classId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(UNCAST_SPELL, characterId, spellId, spellLevel, classId);
		db.close();
	}
	
}
