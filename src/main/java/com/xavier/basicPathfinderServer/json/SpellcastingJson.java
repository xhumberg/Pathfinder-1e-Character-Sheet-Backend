package com.xavier.basicPathfinderServer.json;

import java.util.List;

public class SpellcastingJson {
	int id;
	String name;
	String type;
	int casterLevel;
	int concentration;
	List<SpellsPerLevelJson> spellsPerLevel;
	public SpellcastingJson(int id, String name, String type, int casterLevel, int concentration,
			List<SpellsPerLevelJson> spellsPerLevel) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.casterLevel = casterLevel;
		this.concentration = concentration;
		this.spellsPerLevel = spellsPerLevel;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public int getCasterLevel() {
		return casterLevel;
	}
	public int getConcentration() {
		return concentration;
	}
	public List<SpellsPerLevelJson> getSpellsPerLevel() {
		return spellsPerLevel;
	}
	
}
