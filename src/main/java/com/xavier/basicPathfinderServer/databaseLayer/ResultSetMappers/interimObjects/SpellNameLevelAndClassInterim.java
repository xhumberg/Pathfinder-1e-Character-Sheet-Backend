package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.interimObjects;

public class SpellNameLevelAndClassInterim {

	int classId;
	int level;
	String spellName;
	
	public SpellNameLevelAndClassInterim(int classId, int level, String spellName) {
		this.classId = classId;
		this.level = level;
		this.spellName = spellName;
	}

	public int getClassId() {
		return classId;
	}

	public int getLevel() {
		return level;
	}

	public String getSpellName() {
		return spellName;
	}
	
}
