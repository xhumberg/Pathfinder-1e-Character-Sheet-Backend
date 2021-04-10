package com.xavier.basicPathfinderServer.json;

import java.util.List;

import com.xavier.basicPathfinderServer.PathfinderCharacter;
import com.xavier.basicPathfinderServer.json.mappers.AbilityJson;
import com.xavier.basicPathfinderServer.json.mappers.AbilityListMapper;

public class CharacterJson {
	public final String name;
	public final String imageUrl;
	public final List<AbilityJson> ability;
	
	public CharacterJson(PathfinderCharacter character) {
		this.name = character.name;
		this.imageUrl = character.imageUrl;
		this.ability = AbilityListMapper.map(character.abilities);
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
