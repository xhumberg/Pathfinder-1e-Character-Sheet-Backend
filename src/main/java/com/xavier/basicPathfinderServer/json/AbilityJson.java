package com.xavier.basicPathfinderServer.json;

public class AbilityJson {
	public final String name;
	public final int value;
	public final int mod;
	public final int base;
	
	public AbilityJson(String name, int value, int mod, int base) {
		this.name = name;
		this.value = value;
		this.mod = mod;
		this.base = base;
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
	
	public int getBase() {
		return base;
	}
}
