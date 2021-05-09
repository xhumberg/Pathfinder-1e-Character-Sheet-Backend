package com.xavier.basicPathfinderServer.json;

import java.util.List;

public class SpellsPerLevelJson {
	int level;
	String levelString;
	int perDay;
	List<SpellJson> spellsPrepped;
	List<SpellJson> spellsCast;
	
	public SpellsPerLevelJson(int level, String levelString, int perDay, List<SpellJson> spellsPrepped, List<SpellJson> spellsCast) {
		this.level = level;
		this.levelString = levelString;
		this.perDay = perDay;
		this.spellsPrepped = spellsPrepped;
		this.spellsCast = spellsCast;
	}
	
	public int getLevel() {
		return level;
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
