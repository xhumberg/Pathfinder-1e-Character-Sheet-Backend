package com.xavier.basicPathfinderServer;

public class CharacterJson {
	public final String name;
	public final String imageUrl;
	
	CharacterJson(String name, String imageUrl) {
		this.name = name;
		this.imageUrl = imageUrl;
	}
	
	public String getName() {
		return name;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
}
