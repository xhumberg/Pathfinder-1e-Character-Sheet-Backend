package com.xavier.basicPathfinderServer.json;

public class SkillJson {
	public final String name;
	public final int value;
	public final boolean hasModifiers;
	
	public SkillJson(String name, int value, boolean hasModifiers) {
		this.name = name;
		this.value = value;
		this.hasModifiers = hasModifiers;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}
	
	public boolean hasModifiers() {
		return hasModifiers;
	}
}
