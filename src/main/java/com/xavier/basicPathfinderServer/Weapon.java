package com.xavier.basicPathfinderServer;

public class Weapon {
	public String name;
	public String damageDice;
	public String damageType;
	public String modifiers;
	public int attackMod;
	public int damageMod;
	
	public int critLow;
	public int critMultiplier;
	
	public String description;
	public String category;
	public String proficiency;
	public String special;
	public int weight;
	public String weaponGroups;
	
	public Weapon(String name, String damageDice, String damageType, String modifiers, int attackMod, int damageMod,
			int critLow, int critMultiplier, String description, String category, String proficiency, String special,
			int weight, String weaponGroups) {
		super();
		this.name = name;
		this.damageDice = damageDice;
		this.damageType = damageType;
		this.modifiers = modifiers;
		this.attackMod = attackMod;
		this.damageMod = damageMod;
		this.critLow = critLow;
		this.critMultiplier = critMultiplier;
		this.description = description;
		this.category = category;
		this.proficiency = proficiency;
		this.special = special;
		this.weight = weight;
		this.weaponGroups = weaponGroups;
	}

	public static Weapon getSevenBranchedSword() {
		return new Weapon("Seven Branched Sword", "d10", "S", "+1 Furious", 1, 1, 20, 3, "This unusual sword has a straight 2-foot-long blade with six shorter L-shaped blades protruding from it, three on each side in a staggered pattern. The shorter blades can be used to snag opponents’ clothing or armor, or can target weapons in order to disarm them. To snag armor or clothing, the attacker makes a trip attempt. If successful, the victim doesn’t fall prone, but instead is snagged and stumbles forward, leaving the victim flat-footed for the remainder of the round.",
				"Two-Handed", "Exotic", "disarm, monk", 7, "Blades, Heavy; Monk");
	}
}
