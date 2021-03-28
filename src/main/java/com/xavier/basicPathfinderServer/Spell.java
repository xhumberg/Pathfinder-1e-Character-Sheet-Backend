package com.xavier.basicPathfinderServer;

public class Spell {

	int level;
	String levelInformation;
	String name;
	String school;
	String tags;
	String castingTime;
	String components;
	String range;
	String target;
	String duration;
	String savingThrow;
	String spellResistance;
	String description;
	Stat spellDC;
	int classId;
	
	public Spell(String levelInformation, String name, String school, String tags, String castingTime, String components, String range,
			String target, String duration, String savingThrow, String spellResistance, String description) {
		this(-1, name, school, tags, castingTime, components, range, target, duration, savingThrow, spellResistance, description);
		this.levelInformation = levelInformation;
	}
	
	public Spell(int level, String name, String school, String tags, String castingTime, String components, String range,
			String target, String duration, String savingThrow, String spellResistance, String description) {
		this.level = level;
		this.name = name;
		this.school = school;
		this.tags = tags;
		this.castingTime = castingTime;
		this.components = components;
		this.range = range;
		this.target = target;
		this.duration = duration;
		this.savingThrow = savingThrow;
		this.spellResistance = spellResistance;
		this.description = description;
	}

	public void addSpellDC(Stat spellDC) {
		this.spellDC = spellDC;
	}

	public int getSpellDCValue() {
		return spellDC.getValue();
	}

	public void addClassId(int classId) {
		this.classId = classId;
	}
	
	public int getClassId() {
		return classId;
	}
	
}
