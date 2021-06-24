package com.xavier.basicPathfinderServer.json.mappers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.xavier.basicPathfinderServer.characterOwned.Weapon;
import com.xavier.basicPathfinderServer.json.WeaponJson;
import com.xavier.basicPathfinderServer.json.WeaponStats;

public class WeaponsMapper {
	public static List<WeaponJson> map(Map<Weapon, WeaponStats> weapons, int BAB) {
		List<WeaponJson> jsons = new LinkedList<>();
		
		if (weapons == null) {
			return jsons;
		}
		
		List<Weapon> weaponDefinitions = new ArrayList<>(weapons.keySet());
		weaponDefinitions.sort(new Comparator<Weapon>() {
			@Override
			public int compare(Weapon o1, Weapon o2) {
				return o1.getTitle().compareTo(o2.getTitle());
			}
		});
		for (Weapon weaponDefinition : weaponDefinitions) {
			
			String name = weaponDefinition.getTitle();
			String specialTags = weaponDefinition.special;
			
			WeaponStats stats = weapons.get(weaponDefinition);
			int attackMod = stats.attackStat.getValue();
			String attackMods = "+" + attackMod;
			
			int damageMod = stats.damageStat.getValue();
			String damageAddition = "";
			if (damageMod != 0) {
				damageAddition = " + " + damageMod;
			}
			String critRange = "20";
			if (weaponDefinition.critLow < 20) {
				critRange = weaponDefinition.critLow + "-20";
			}
			
			String damage = "( " + weaponDefinition.damageDice + damageAddition + " | " + critRange + "x" + weaponDefinition.critMultiplier + " )";
			
			WeaponJson json = new WeaponJson(name, specialTags, attackMods, damage);
			jsons.add(json);
		}
		
		return jsons;
	}
}
