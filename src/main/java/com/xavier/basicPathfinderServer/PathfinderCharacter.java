package com.xavier.basicPathfinderServer;

public class PathfinderCharacter {
	
	public String name;
	public String imageUrl;
	
	public PathfinderCharacter(String name, String imageUrl) {
		this.name = name;
		this.imageUrl = imageUrl;
	}
	
	public CharacterJson convertToJson() {
		return new CharacterJson(name, imageUrl);
	}
}
