package com.xavier.basicPathfinderServer.characterOwned;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.xavier.basicPathfinderServer.PathfinderCharacter;
import com.xavier.basicPathfinderServer.numericals.Adjustment;
import com.xavier.basicPathfinderServer.numericals.Stat;

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

	//Also marks spells as "known", defining the level at which they ARE known
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
	public Spell castSpell(String spellName, int level) {
		
		List<Spell> castSpellsOfLevel = spellsCast.get(level);
		if (castSpellsOfLevel == null) {
			spellsCast.put(level, new LinkedList<Spell>());
			castSpellsOfLevel = spellsCast.get(level);
		}
		
		if (type == CastingType.PREPARED) {
			Spell targetSpell = null;
			List<Spell> spellsOfLevel = spellsPrepped.get(level);
			for (Spell spell : spellsOfLevel) {
				if (spell.name.equals(spellName)) {
					targetSpell = spell;
					break;
				}
			}
			spellsOfLevel.remove(targetSpell);
			castSpellsOfLevel.add(targetSpell);
			return targetSpell;
		} else if (type == CastingType.SPONTANEOUS) {
			Spell fillerSpell = Spell.fillerSpell();
			castSpellsOfLevel.add(fillerSpell);
			return fillerSpell;
		} else {
			throw new InvalidParameterException("Spellcasting type not currently supported");
		}
	}
	
	//Note: The spell MUST be prepped (for prepared and arcanist casters) or known (for spontaneous casters)
	public Spell uncastSpell(String spellName, int level) {
		
		List<Spell> castSpellsOfLevel = spellsCast.get(level);
		if (castSpellsOfLevel == null) {
			spellsCast.put(level, new LinkedList<Spell>());
			castSpellsOfLevel = spellsCast.get(level);
		}
		
		if (type == CastingType.PREPARED) {
			Spell targetSpell = null;
			for (Spell spell : castSpellsOfLevel) {
				if (spell.name.equals(spellName)) {
					targetSpell = spell;
					break;
				}
			}
			castSpellsOfLevel.remove(targetSpell);
			
			List<Spell> spellsOfLevel = spellsPrepped.get(level);
			if (spellsOfLevel == null) {
				spellsPrepped.put(level, new LinkedList<Spell>());
				spellsOfLevel = spellsCast.get(level);
			}
			spellsOfLevel.add(targetSpell);
			return targetSpell;
		} else if (type== CastingType.SPONTANEOUS) {
			if (castSpellsOfLevel.size() > 0) {
				Spell filler = castSpellsOfLevel.remove(0);
				return filler;
			} else {
				return null;
			}
		} else {
			throw new InvalidParameterException("Spellcasting type not currently supported");
		}
	}

	private void addSpellDCToSpell(int level, Spell spell) {
		ensureSchoolSpecificCharacterStat(spell.school);
		Stat spellDC = new Stat("DC");
		if (spell.savingThrow.equals("none")) {
			spellDC.setBaseValue(-1);
		} else {
			spellDC.setBaseValue(10);
			Adjustment spellDCAdjustments = new Adjustment(-1, "DC adjustment", true);
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
	
	private Spell getSpell(String spellName) {
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
		return name.replaceAll("\\d", "").replaceAll("\\(Modified.*?\\)", "");
	}

	public String getTypeString() {
		if (type == CastingType.PREPARED) {
			return "Spells Prepared";
		} else if (type == CastingType.SPONTANEOUS) {
			return "Spells Known";
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

	public List<Spell> getSpellsPreppedForLevel(int level) {
		if (type == CastingType.PREPARED || type == CastingType.SPONTANEOUS) {
			List<Spell> preppedSpells = spellsPrepped.get(level);
			if (preppedSpells == null) {
				return Collections.emptyList();
			}
			preppedSpells.sort(new Comparator<Spell>() {

				@Override
				public int compare(Spell o1, Spell o2) {
					return o1.getName().compareTo(o2.getName());
				}
				
			});
			return preppedSpells;
		} else {
			throw new InvalidParameterException("Spellcasting type not currently supported");
		}
	}

	public List<Spell> getSpellsCastForLevel(int level) {
		if (type == CastingType.PREPARED) {
			List<Spell> castSpells = spellsCast.get(level);
			if (castSpells == null) {
				return Collections.emptyList();
			}
			castSpells.sort(new Comparator<Spell>() {

				@Override
				public int compare(Spell o1, Spell o2) {
					return o1.getName().compareTo(o2.getName());
				}
				
			});
			return castSpells;
		} else if (type == CastingType.SPONTANEOUS) {
			List<Spell> castSpells = spellsCast.get(level);
			if (castSpells == null) {
				return Collections.emptyList();
			} else {
				return castSpells; //No need to sort. Only the number matters
			}
		} else {
			throw new InvalidParameterException("Spellcasting type not currently supported");
		}
	}

	public CastingType getType() {
		return type;
	}

	public int getId() {
		return classId;
	}

}
