package com.xavier.basicPathfinderServer.json.mappers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.xavier.basicPathfinderServer.characterOwned.Spellcasting;
import com.xavier.basicPathfinderServer.json.SpellcastingJson;
import com.xavier.basicPathfinderServer.json.SpellsPerLevelJson;

public class SpellcastingListMapper {

	public static List<SpellcastingJson> map(HashMap<Integer, Spellcasting> spellcastingByClass) {
		List<SpellcastingJson> spellcastingJsons = new LinkedList<SpellcastingJson>();
		for (Integer classID : spellcastingByClass.keySet()) { //TODO: enforce ordering so it's not random when multiple classes exist
			Spellcasting classSpellcasting = spellcastingByClass.get(classID);
			String name = classSpellcasting.getName();
			String type = classSpellcasting.getTypeString();
			int casterLevel = classSpellcasting.getCasterLevel();
			int concentration = 0; //TODO: implement concentration, as that's a character stat.
			List<SpellsPerLevelJson> spellsPerLevel = SpellsPerLevelMapper.map(classSpellcasting);
			
			SpellcastingJson spellcasting = new SpellcastingJson(classID, name, type, casterLevel, concentration, spellsPerLevel);
			spellcastingJsons.add(spellcasting);
		}
		
		return spellcastingJsons;
	}

}
