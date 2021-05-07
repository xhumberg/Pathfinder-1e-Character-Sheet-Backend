package com.xavier.basicPathfinderServer.json;

public class AbilityJson {
	public final String name;
	public final int value;
	public final int mod;
	
	public AbilityJson(String name, int value, int mod) {
		this.name = name;
		this.value = value;
		this.mod = mod;
	}

	public String getName() {
		return name;
	}

	public int getValue() {
		return value;
	}

	public int getMod() {
		return mod;
	}
}
