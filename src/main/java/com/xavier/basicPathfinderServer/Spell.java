package com.xavier.basicPathfinderServer;

public class Spell {

	int id;
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
	boolean hasBeenCast;
	
	public Spell(int id, String levelInformation, String name, String school, String tags, String castingTime, String components, String range,
			String target, String duration, String savingThrow, String spellResistance, String description) {
		this(id, -1, name, school, tags, castingTime, components, range, target, duration, savingThrow, spellResistance, description);
		this.levelInformation = levelInformation;
	}
	
	public Spell(int id, int level, String name, String school, String tags, String castingTime, String components, String range,
			String target, String duration, String savingThrow, String spellResistance, String description) {
		this.id = id;
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
	
	public static Spell fillerSpell() {
		return new Spell(0, 0, "Filler", "", "", "", "", "", "", "", "", "", "");
	}

	public void addSpellDC(Stat spellDC) {
		this.spellDC = spellDC;
	}

	public int getSpellDCValue() {
		if (spellDC == null) {
			return -1;
		}
		return spellDC.getValue();
	}

	public void addClassId(int classId) {
		this.classId = classId;
	}
	
	public int getClassId() {
		return classId;
	}
	
	public void setAsCast() {
		this.hasBeenCast = true;
	}
	
	public boolean hasBeenCast() {
		return hasBeenCast;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getSchool() {
		return school;
	}

	public String getTags() {
		return tags;
	}

	public String getLevelInformation() {
		return levelInformation;
	}

	public String getCastingTime() {
		return castingTime;
	}

	public int getLevel() {
		return level;
	}

	public String getComponents() {
		return components;
	}

	public String getRange() {
		return range;
	}

	public String getTarget() {
		return target;
	}

	public String getDuration() {
		return duration;
	}

	public String getSavingThrow() {
		return savingThrow;
	}

	public String getSpellResistance() {
		return spellResistance;
	}

	public Stat getSpellDC() {
		return spellDC;
	}

	public boolean isHasBeenCast() {
		return hasBeenCast;
	}
	
	public int getId() {
		return id;
	}
}
