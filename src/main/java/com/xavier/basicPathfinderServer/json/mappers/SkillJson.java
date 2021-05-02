package com.xavier.basicPathfinderServer.json.mappers;

public class SkillJson {
	public final String name;
	public final int value;
	
	public SkillJson(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}
}
