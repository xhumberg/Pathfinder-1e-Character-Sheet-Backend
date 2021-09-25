package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import com.xavier.basicPathfinderServer.databaseLayer.DatabaseAccess;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.simpleMappers.DoesAResultExistMapper;

public class SkillsDatabaseModifier {
	
	private static final String GET_RANKS = "SELECT * FROM SkillRanks WHERE CharacterID = ? AND SkillName = ?";
	private static final String ADD_RANKS = "INSERT INTO SkillRanks VALUES (?, ?, ?)";
	private static final String SET_RANKS = "UPDATE SkillRanks SET SkillRanks = ? WHERE CharacterID = ? AND SkillName = ?";
	private static final String DELETE_RANKS = "DELETE FROM SKillRanks WHERE CharacterID = ? AND SkillName = ?";
	
	private static final String GET_IS_CLASS_SKILL = "SELECT * FROM ClassSkills WHERE CharacterID = ? AND SkillName = ?";
	private static final String ADD_CLASS_SKILL = "INSERT INTO ClassSkills VALUES (?, ?)";
	private static final String REMOVE_CLASS_SKILL = "DELETE FROM ClassSkills WHERE CharacterID = ? AND SkillName = ?";
	
	public static void setRanks(String characterid, String skillName, int totalRanks) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		Boolean exists = (Boolean) db.executeSelectQuery(new DoesAResultExistMapper(), GET_RANKS, characterid, skillName);
		if (exists) {
			db.executeModifyQuery(SET_RANKS, totalRanks, characterid, skillName);
		} else {
			db.executeModifyQuery(ADD_RANKS, characterid, skillName, totalRanks);
		}
	}
	
	public static void deleteRanks(String characterid, String skillName) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(DELETE_RANKS, characterid, skillName);
	}
	
	public static void addClassSkill(String characterid, String skillName) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		Boolean exists = (Boolean) db.executeSelectQuery(new DoesAResultExistMapper(), GET_IS_CLASS_SKILL, characterid, skillName);
		if (!exists) {
			db.executeModifyQuery(ADD_CLASS_SKILL, characterid, skillName);
		}
	}
	
	public static void deleteClassSkill(String characterid, String skillName) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(REMOVE_CLASS_SKILL, characterid, skillName);
	}
}
