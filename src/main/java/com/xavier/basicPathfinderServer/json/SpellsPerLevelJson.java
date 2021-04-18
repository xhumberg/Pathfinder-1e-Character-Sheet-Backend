package com.xavier.basicPathfinderServer.json;

import java.util.List;

public class SpellsPerLevelJson {
	String levelString;
	int perDay;
	List<SpellJson> spellsPrepped;
	List<SpellJson> spellsCast;
	
	public SpellsPerLevelJson(String levelString, int perDay, List<SpellJson> spellsPrepped, List<SpellJson> spellsCast) {
		this.levelString = levelString;
		this.perDay = perDay;
		this.spellsPrepped = spellsPrepped;
		this.spellsCast = spellsCast;
	}

	public String getLevelString() {
		return levelString;
	}
	
	public int getPerDay() {
		return perDay;
	}

	public List<SpellJson> getSpellsPrepped() {
		return spellsPrepped;
	}

	public List<SpellJson> getSpellsCast() {
		return spellsCast;
	}
}
