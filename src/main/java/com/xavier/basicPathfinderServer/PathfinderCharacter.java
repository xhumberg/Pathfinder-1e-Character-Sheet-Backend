package com.xavier.basicPathfinderServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.xavier.basicPathfinderServer.json.CharacterJson;
import com.xavier.basicPathfinderServer.json.mappers.AbilityListMapper;

public class PathfinderCharacter {
	
	public String name;
	public String imageUrl;
	public List<Ability> abilities;
	public final HashMap<String, Adjustment> adjustments;
	public final HashMap<String, Stat> allStats;
	
	public PathfinderCharacter(String name, String imageUrl) {
		this.name = name;
		this.imageUrl = imageUrl;
		allStats = new HashMap<>();
		initAbilities();
		addAbilitiesToStats();
		adjustments = new HashMap<>();
	}

	private void initAbilities() {
		abilities = new ArrayList<Ability>(6);
		abilities.add(new Ability("Strength"));
		abilities.add(new Ability("Dexterity"));
		abilities.add(new Ability("Constitution"));
		abilities.add(new Ability("Intelligence"));
		abilities.add(new Ability("Wisdom"));
		abilities.add(new Ability("Charisma"));
	}
	
	private void addAbilitiesToStats() {
		for(Ability ability : abilities) {
			allStats.put(ability.name, ability);
		}
	}
	
	public void setAbility(String abilityName, int baseValue) {
		for (Ability ability : abilities) {
			if (ability.getName().equals(abilityName)) {
				ability.setBaseValue(baseValue);
			}
		}
	}
	
	public CharacterJson convertToJson() {
		return new CharacterJson(name, imageUrl, AbilityListMapper.map(abilities));
	}

	public void toggleAdjustment(String adjustmentName) {
		adjustments.get(adjustmentName).toggleAdjustment();
	}

	public void addAdjustment(Adjustment adjustment) {
		adjustments.put(adjustment.name, adjustment);
		for (String adjustedStatName : adjustment.getAdjustedStats()) {
			Stat adjustedStat = allStats.get(adjustedStatName);
			adjustedStat.addAdjustment(adjustment);
		}
	}

	public int getStatValue(String statName) {
		return allStats.get(statName).getValue();
	}
}
