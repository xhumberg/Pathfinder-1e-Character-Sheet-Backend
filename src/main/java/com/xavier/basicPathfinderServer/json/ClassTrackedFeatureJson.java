package com.xavier.basicPathfinderServer.json;

public class ClassTrackedFeatureJson {
	private final String name;
	private final String description;
	private final int amount;

	public ClassTrackedFeatureJson(String name, String description, int amount) {
		this.name = name;
		this.description = description;
		this.amount = amount;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getAmount() {
		return amount;
	}
}
