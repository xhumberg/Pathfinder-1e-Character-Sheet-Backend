package com.xavier.basicPathfinderServer.json;

public class ItemJson {
	private final String name;
	private final String description;
	
	public ItemJson(String name, String description) {
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
