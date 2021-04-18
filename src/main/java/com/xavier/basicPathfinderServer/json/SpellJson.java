package com.xavier.basicPathfinderServer.json;

public class SpellJson {
	
	String name;
	String description;
	int classId;
	int level;
	boolean isCast;
	public SpellJson(String name, String description, int classId, int level, boolean isCast) {
		this.name = name;
		this.description = description;
		this.classId = classId;
		this.level = level;
		this.isCast = isCast;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public int getClassId() {
		return classId;
	}
	public int getLevel() {
		return level;
	}
	public boolean isCast() {
		return isCast;
	}
	
}
