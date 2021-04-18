package com.xavier.basicPathfinderServer.json;

import java.util.List;

import com.xavier.basicPathfinderServer.PathfinderCharacter;
import com.xavier.basicPathfinderServer.json.mappers.AbilityJson;
import com.xavier.basicPathfinderServer.json.mappers.AbilityListMapper;
import com.xavier.basicPathfinderServer.json.mappers.SpellcastingListMapper;

public class CharacterJson {
	public final String name;
	public final String imageUrl;
	public final List<AbilityJson> ability;
	public final List<SpellcastingJson> spellcasting;
	
	public CharacterJson(PathfinderCharacter character) {
		this.name = character.name;
		this.imageUrl = character.imageUrl;
		this.ability = AbilityListMapper.map(character.abilities);
		this.spellcasting = SpellcastingListMapper.map(character.spellcastingByClass);
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
