package com.xavier.basicPathfinderServer;

import java.util.ArrayList;
import java.util.List;

import com.xavier.basicPathfinderServer.json.CharacterJson;
import com.xavier.basicPathfinderServer.json.mappers.AbilityListMapper;

public class PathfinderCharacter {
	
	public String name;
	public String imageUrl;
	public List<Ability> abilities;
	
	public PathfinderCharacter(String name, String imageUrl) {
		this.name = name;
		this.imageUrl = imageUrl;
		initAbilities();
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
}
