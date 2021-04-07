package com.xavier.basicPathfinderServer;

import java.util.Map;

public class CharacterClass {

	private int id;
	private int level;
	private int bab;
	private int fort;
	private int ref;
	private int will;
	private int baseSkillsPerLevel;
	private int hitDice;
	private String name;
	private boolean hasSpellcasting;
	private CastingType spellcastingType;
	private String spellcastingAbility;
	private Map<Integer, Integer> baseSpellsPerDay;

	public CharacterClass(int id, int level, int bab, int fort, int ref, int will, int baseSkillsPerLevel, int hitDice, String name, boolean hasSpellcasting,
			CastingType spellcastingType, String spellcastingAbility, Map<Integer, Integer> baseSpellsPerDay) {
		this.id = id;
		this.level = level;
		this.bab = bab;
		this.fort = fort;
		this.ref = ref;
		this.will = will;
		this.baseSkillsPerLevel = baseSkillsPerLevel;
		this.hitDice = hitDice;
		this.name = name;
		this.hasSpellcasting = hasSpellcasting;
		this.spellcastingType = spellcastingType;
		this.spellcastingAbility = spellcastingAbility;
		this.baseSpellsPerDay = baseSpellsPerDay;
	}

	public int getLevel() {
		return level;
	}

	public int getBab() {
		return bab;
	}

	public int getFort() {
		return fort;
	}

	public int getRef() {
		return ref;
	}

	public int getWill() {
		return will;
	}
	
	public int getBaseSkillsPerLevel() {
		return baseSkillsPerLevel;
	}
	
	public int getHitDice() {
		return hitDice;
	}

	public String getName() {
		return name;
	}

	public boolean hasSpellcasting() {
		return hasSpellcasting;
	}

	public CastingType getSpellcastingType() {
		return spellcastingType;
	}

	public String getSpellcastingAbility() {
		return spellcastingAbility;
	}

	public Map<Integer, Integer> getBaseSpellsPerDay() {
		return baseSpellsPerDay;
	}

	public int getId() {
		return id;
	}

}
