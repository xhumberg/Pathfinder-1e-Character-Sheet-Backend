package com.xavier.basicPathfinderServer;

import java.util.Map;

public class CharacterClass {

	private int level;
	private int bab;
	private int fort;
	private int ref;
	private int will;
	private String name;
	private boolean hasSpellcasting;
	private CastingType spellcastingType;
	private String spellcastingAbility;
	private Map<Integer, Integer> baseSpellsPerDay;

	public CharacterClass(int level, int bab, int fort, int ref, int will, String name, boolean hasSpellcasting,
			CastingType spellcastingType, String spellcastingAbility, Map<Integer, Integer> baseSpellsPerDay) {
		this.level = level;
		this.bab = bab;
		this.fort = fort;
		this.ref = ref;
		this.will = will;
		this.name = name;
		this.hasSpellcasting = hasSpellcasting;
		this.spellcastingType = spellcastingType;
		this.spellcastingAbility = spellcastingAbility;
		this.baseSpellsPerDay = baseSpellsPerDay;
	}

}
