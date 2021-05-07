package com.xavier.basicPathfinderServer.json;

public class FeatJson {
	private final String name;
	private final String description;
	
	public FeatJson(String name, String description) {
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
