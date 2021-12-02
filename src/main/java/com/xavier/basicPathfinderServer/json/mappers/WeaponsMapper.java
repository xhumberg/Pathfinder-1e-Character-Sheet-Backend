package com.xavier.basicPathfinderServer.json.mappers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.xavier.basicPathfinderServer.PathfinderCharacter;
import com.xavier.basicPathfinderServer.characterOwned.Weapon;
import com.xavier.basicPathfinderServer.json.WeaponJson;
import com.xavier.basicPathfinderServer.json.WeaponStats;
import com.xavier.basicPathfinderServer.numericals.StatName;

public class WeaponsMapper {
	public static List<WeaponJson> map(PathfinderCharacter character) {
		Map<Weapon, WeaponStats> weapons = character.getWeapons();
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
			WeaponStats stats = weapons.get(weaponDefinition);
			WeaponJson json = generateJsonForWeapon(weaponDefinition, stats, character);
			jsons.add(json);
		}
		
		return jsons;
	}

	private static WeaponJson generateJsonForWeapon(Weapon weaponDefinition, WeaponStats stats, PathfinderCharacter character) {
		String name = weaponDefinition.getTitle();
		String specialTags = weaponDefinition.special;
		
		String attackMods = generateAttackModsString(stats, character);
		
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
		return json;
	}

	private static String generateAttackModsString(WeaponStats stats, PathfinderCharacter character) {
		int attackMod = stats.attackStat.getValue();
		int penaltyToAllBut1stAttack = character.getStatValue(StatName.PENALTY_TO_ALL_BUT_1ST_ATTACK);
		int BAB = character.getStatValue(StatName.BAB);
		int additionalAttacksAtFullBAB = character.getStatValue(StatName.ADDITIONAL_ATTACKS_AT_FULL_BAB);
		int additionalFirstIteratives = character.getStatValue(StatName.ADDITIONAL_FIRST_ITERATIVES);
		int additionalSecondIteratives = character.getStatValue(StatName.ADDITIONAL_SECOND_ITERATIVES);
		int additionalThirdIteratives = character.getStatValue(StatName.ADDITIONAL_THIRD_ITERATIVES);
		
		String attackMods = "+" + attackMod;
		for (int i = 0; i < additionalAttacksAtFullBAB; i++) {
			attackMods += "/+" + (attackMod-penaltyToAllBut1stAttack);
		}
		
		if (BAB > 5) {
			for (int i = 0; i <= additionalFirstIteratives; i++)
				attackMods += "/+" + (attackMod-5-penaltyToAllBut1stAttack);
		}
		if (BAB > 10) {
			for (int i = 0; i <= additionalSecondIteratives; i++)
				attackMods += "/+" + (attackMod-10-penaltyToAllBut1stAttack);
		}
		if (BAB > 15) {
			for (int i = 0; i <= additionalThirdIteratives; i++)
				attackMods += "/+" + (attackMod-15-penaltyToAllBut1stAttack);
		}
		return attackMods;
	}
}
