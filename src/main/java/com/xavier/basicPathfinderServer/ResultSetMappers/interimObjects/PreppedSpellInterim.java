package com.xavier.basicPathfinderServer.ResultSetMappers.interimObjects;

public class PreppedSpellInterim {

	int classId;
	int level;
	String spellName;
	
	public PreppedSpellInterim(int classId, int level, String spellName) {
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
