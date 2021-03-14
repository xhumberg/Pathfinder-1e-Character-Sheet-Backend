package com.xavier.basicPathfinderServer;

import java.util.HashMap;

public class Spellcasting {

	private String className;
	private CastingType type;
	private String castingStat;
	private PathfinderCharacter character;
	private HashMap<Integer, Integer> spellsPerDay;
	
	public Spellcasting(String className, CastingType type, String castingStat,
			PathfinderCharacter character) {
		this.className = className;
		this.type = type;
		this.castingStat = castingStat;
		this.character = character;
		spellsPerDay = new HashMap<>();
	}

	public void setSpellsPerDay(int level, int basePerDay) {
		spellsPerDay.put(level, basePerDay);
	}

	public int getSpellsPerDay(int level) {
		int baseSpellsForLevel = spellsPerDay.get(level);
		int castingMod = character.getAbilityMod("Intelligence");
		int bonusSpellsPerDay = 0;
		while (castingMod >= level) {
			bonusSpellsPerDay++;
			castingMod -= 4;
		}
		return baseSpellsForLevel + bonusSpellsPerDay;
	}

}
