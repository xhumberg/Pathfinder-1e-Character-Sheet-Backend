package com.xavier.basicPathfinderServer.json;

import java.util.List;

import com.xavier.basicPathfinderServer.json.mappers.AbilityJson;

public class CharacterJson {
	public final String name;
	public final String imageUrl;
	public final List<AbilityJson> ability;
	
	public CharacterJson(String name, String imageUrl, List<AbilityJson> abilities) {
		this.name = name;
		this.imageUrl = imageUrl;
		ability = abilities;
	}
	
	public String getName() {
		return name;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public List<AbilityJson> getAbility() {
		return ability;
	}
}
