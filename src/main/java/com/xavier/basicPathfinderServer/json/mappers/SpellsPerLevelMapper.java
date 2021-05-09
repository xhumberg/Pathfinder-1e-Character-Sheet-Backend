package com.xavier.basicPathfinderServer.json.mappers;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.xavier.basicPathfinderServer.Spellcasting;
import com.xavier.basicPathfinderServer.json.SpellJson;
import com.xavier.basicPathfinderServer.json.SpellsPerLevelJson;

public class SpellsPerLevelMapper {

	public static List<SpellsPerLevelJson> map(Spellcasting classSpellcasting) {
		List<SpellsPerLevelJson> spellsPerLevel = new LinkedList<>();
		List<Integer> levels = new LinkedList<>(classSpellcasting.spellsPrepped.keySet());
		Collections.sort(levels, Collections.reverseOrder());
		for (int level : levels) {
			String levelString = getLevelString(level);
			int perDay = classSpellcasting.getSpellsPerDay(level);
			List<SpellJson> spellsPrepped = SpellListMapper.map(level, classSpellcasting.getSpellsPreppedForLevel(level));
			List<SpellJson> spellsCast = SpellListMapper.map(level, classSpellcasting.getSpellsCastForLevel(level));
			SpellsPerLevelJson spellsForLevel = new SpellsPerLevelJson(level, levelString, perDay, spellsPrepped, spellsCast); 
			spellsPerLevel.add(spellsForLevel);
		}
//		List<SpellJson> thirdLevelSpells = null;
//		if (classSpellcasting.getSpellsPerDay(3) > 0) {
//			thirdLevelSpells = SpellListMapper.map(3, classSpellcasting.getSpellsForLevel(3));
//		}
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
