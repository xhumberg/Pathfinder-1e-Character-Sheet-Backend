package com.xavier.basicPathfinderServer.json.mappers;

import java.util.LinkedList;
import java.util.List;

import com.xavier.basicPathfinderServer.characterOwned.Spell;
import com.xavier.basicPathfinderServer.json.SpellJson;

public class SpellListMapper {

	public static List<SpellJson> map(int level, List<Spell> spellsForLevel) {
		List<SpellJson> spellJsons= new LinkedList<>();
		for (Spell spell : spellsForLevel) {
			SpellJson json = SpellMapper.map(spell, level);
			spellJsons.add(json);
		}
		
		return spellJsons;
	}

}
