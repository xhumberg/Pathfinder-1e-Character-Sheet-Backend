package com.xavier.basicPathfinderServer.json;

public class RacialTraitJson {
	private final String name;
	private final String description;
	
	public RacialTraitJson(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
