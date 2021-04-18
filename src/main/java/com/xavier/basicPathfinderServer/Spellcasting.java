package com.xavier.basicPathfinderServer;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Spellcasting {

	private int classId;
	private String name;
	private CastingType type;
	private int casterLevel;
	private String castingStat;
	private PathfinderCharacter character;
	private HashMap<Integer, Integer> spellsPerDay;
	private LinkedList<Spell> spellsKnown;
	public HashMap<Integer, List<Spell>> spellsPrepped;
	private HashMap<Integer, List<Spell>> spellsCast;
	
	public Spellcasting(int classId, String name, CastingType type, int casterLevel, String castingStat,
			PathfinderCharacter character) {
		this.classId = classId;
		this.name = name;
		this.type = type;
		this.casterLevel = casterLevel;
		this.castingStat = castingStat;
		this.character = character;
		spellsPerDay = new HashMap<>();
		spellsKnown = new LinkedList<>();
		spellsPrepped = new HashMap<>();
		spellsCast = new HashMap<>();
	}

	public void setSpellsPerDay(int level, int basePerDay) {
		spellsPerDay.put(level, basePerDay);
	}

	public int getSpellsPerDay(int level) {
		int baseSpellsForLevel = spellsPerDay.get(level);
		int castingMod = character.getAbilityMod(castingStat);
		int bonusSpellsPerDay = 0;
		while (level != 0 && castingMod >= level) {
			bonusSpellsPerDay++;
			castingMod -= 4;
		}
		return baseSpellsForLevel + bonusSpellsPerDay;
	}

	public void addSpellKnown(Spell spell) {
		spellsKnown.add(spell);
	}

	public void prepSpell(String spellName, int level) {
		Spell spell = getSpell(spellName);
		if (spell != null) {
			addSpellDCToSpell(level, spell);
			List<Spell> spellsOfLevel = spellsPrepped.get(level);
			if (spellsOfLevel == null) {
				spellsPrepped.put(level, new LinkedList<Spell>());
				spellsOfLevel = spellsPrepped.get(level);
			}
			spellsOfLevel.add(spell);
		}
	}
	
	//Note: The spell MUST be prepped
	public void castSpell(String spellName, int level) {
		Spell targetSpell = null;
		List<Spell> spellsOfLevel = spellsPrepped.get(level);
		for (Spell spell : spellsOfLevel) {
			if (spell.name.equals(spellName)) {
				targetSpell = spell;
				break;
			}
		}
		spellsOfLevel.remove(targetSpell);
		
		List<Spell> castSpellsOfLevel = spellsCast.get(level);
		if (castSpellsOfLevel == null) {
			spellsCast.put(level, new LinkedList<Spell>());
			castSpellsOfLevel = spellsCast.get(level);
		}
		castSpellsOfLevel.add(targetSpell);
	}

	private void addSpellDCToSpell(int level, Spell spell) {
		ensureSchoolSpecificCharacterStat(spell.school);
		Stat spellDC = new Stat("DC");
		if (spell.savingThrow.equals("none")) {
			spellDC.setBaseValue(-1);
		} else {
			spellDC.setBaseValue(10);
			Adjustment spellDCAdjustments = new Adjustment("DC adjustment", true);
			spellDCAdjustments.addEffect("DC", "Casting Stat", character.getAbility(castingStat));
			spellDCAdjustments.addEffect("DC", "Spell Level", level);
			spellDCAdjustments.addEffect("DC", spell.school, character.getStat(spell.school));
			spellDC.addAdjustment(spellDCAdjustments);
		}
		spell.addSpellDC(spellDC);
	}
	
	private void ensureSchoolSpecificCharacterStat(String spellSchool) {
		Stat schoolSpecificDCChange = character.getStat(spellSchool);
		if (schoolSpecificDCChange == null) {
			character.addStat(spellSchool);
		}
	}
	
	public Spell getSpell(String spellName) {
		for (Spell spell : spellsKnown) {
			if (spell.name.equals(spellName)) {
				return spell;
			}
		}
		return null;
	}

	public Integer getSpellDC(String spellName, int level) {
		Spell spell = getSpell(spellName);
		if(spell != null) {
			return spell.getSpellDCValue();
		}
		return -1;
	}

	public String getName() {
		return name;
	}

	public String getTypeString() {
		if (type == CastingType.PREPARED) {
			return "Spells Prepared";
		} else {
			return "Unsupported casting type";
		}
	}

	public int getCasterLevel() {
		return casterLevel;
	}

	public List<Spell> getSpellsForLevel(int level) {
		if (type == CastingType.PREPARED) {
			List<Spell> allSpellsOfLevel = new LinkedList<>();
			if (spellsPrepped.get(level) != null) {
				List<Spell> preppedSpells = spellsPrepped.get(level);
				allSpellsOfLevel.addAll(preppedSpells);
			}
			if (spellsCast.get(level) != null) {
				List<Spell> castSpells = spellsCast.get(level);
				for (Spell castSpell : castSpells) {
					castSpell.setAsCast(); //TODO: since we don't deep copy, this isn't proper logic. Probably return not just a List<Spell>
				}
				allSpellsOfLevel.addAll(castSpells);
			}
			return allSpellsOfLevel;
		} else {
			throw new InvalidParameterException("Spellcasting type not currently supported");
		}
	}

}
