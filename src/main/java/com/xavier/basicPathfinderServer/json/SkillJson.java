package com.xavier.basicPathfinderServer.json;

public class SkillJson {
	public final String name;
	public final int value;
	public final int ranks;
	public final boolean hasModifiers;
	
	public SkillJson(String name, int value, int ranks, boolean hasModifiers) {
		this.name = name;
		this.value = value;
		this.ranks = ranks;
		this.hasModifiers = hasModifiers;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}
	
	public int getRanks() {
		return ranks;
	}
	
	public boolean hasModifiers() {
		return hasModifiers;
	}
}
