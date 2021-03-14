package com.xavier.basicPathfinderServer;

public class Spell {

	int level;
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
	
}
