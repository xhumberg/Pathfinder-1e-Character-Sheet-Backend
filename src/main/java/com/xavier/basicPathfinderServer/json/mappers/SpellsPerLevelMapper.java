package com.xavier.basicPathfinderServer.json.mappers;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.xavier.basicPathfinderServer.characterOwned.CastingType;
import com.xavier.basicPathfinderServer.characterOwned.Spellcasting;
import com.xavier.basicPathfinderServer.json.SpellJson;
import com.xavier.basicPathfinderServer.json.SpellsPerLevelJson;

public class SpellsPerLevelMapper {

	public static List<SpellsPerLevelJson> map(Spellcasting classSpellcasting) {
		List<SpellsPerLevelJson> spellsPerLevel = new LinkedList<>();
		List<Integer> levels = new LinkedList<>(classSpellcasting.spellsPrepped.keySet());
		Collections.sort(levels, Collections.reverseOrder());
		for (int level : levels) {
			int spellsPerDayForLevel = classSpellcasting.getSpellsPerDay(level);
			int perDay = classSpellcasting.getSpellsPerDay(level);
			List<SpellJson> spellsPrepped = SpellListMapper.map(level, classSpellcasting.getSpellsPreppedForLevel(level));
			List<SpellJson> spellsCast = SpellListMapper.map(level, classSpellcasting.getSpellsCastForLevel(level));
			

			String levelString = "Error";
			if (classSpellcasting.getType() == CastingType.PREPARED) {
				levelString = getLevelString(level) + " (" + spellsPerDayForLevel + "/day)";
				
				SpellsPerLevelJson spellsForLevel = new SpellsPerLevelJson(level, levelString, perDay, spellsPrepped, spellsCast); 
				spellsPerLevel.add(spellsForLevel);
			}
			else if (classSpellcasting.getType() == CastingType.SPONTANEOUS) {
				levelString = getLevelString(level) + " (" + (spellsPerDayForLevel-spellsCast.size()) + "/" + spellsPerDayForLevel + " per day)";
				
				spellsCast = Collections.singletonList(new SpellJson("Uncast", "Click uncast to the left to regain a slot for level " + level, classSpellcasting.getId(), level, true));
				
				SpellsPerLevelJson spellsForLevel = new SpellsPerLevelJson(level, levelString, perDay, spellsPrepped, spellsCast); 
				spellsPerLevel.add(spellsForLevel);
			}
		}
		return spellsPerLevel;
	}
	
	private static String getLevelString(int level) {
		switch (level) {
		case 9:
			return "9th Level Spells";
		case 8:
			return "8th Level Spells";
		case 7:
			return "7th Level Spells";
		case 6:
			return "6th Level Spells";
		case 5:
			return "5th Level Spells";
		case 4:
			return "4th Level Spells";
		case 3:
			return "3rd Level Spells";
		case 2:
			return "2nd Level Spells";
		case 1:
			return "1st Level Spells";
		case 0:
			return "Cantrips";
		default:
			return "Error occured";
		}
	}

}
