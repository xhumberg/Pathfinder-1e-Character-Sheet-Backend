package com.xavier.basicPathfinderServer.json;

public class WeaponJson {
	private final String name;
	private final String specialTags;
	private final String attackMods;
	private final String damage;
	public WeaponJson(String name, String specialTags, String attackMods, String damage) {
		this.name = name;
		this.specialTags = specialTags;
		this.attackMods = attackMods;
		this.damage = damage;
	}
	public String getName() {
		return name;
	}
	public String getSpecialTags() {
		return specialTags;
	}
	public String getAttackMods() {
		return attackMods;
	}
	public String getDamage() {
		return damage;
	}
	
}
