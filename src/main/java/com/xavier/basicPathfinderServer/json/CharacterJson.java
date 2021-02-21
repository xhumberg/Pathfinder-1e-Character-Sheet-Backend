package com.xavier.basicPathfinderServer.json;

import java.util.List;

public class CharacterJson {
	public final String name;
	public final String imageUrl;
	public final List<String> ability;
	
	public CharacterJson(String name, String imageUrl, List<String> abilities) {
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
	
	public List<String> getAbility() {
		return ability;
	}
}
