package com.xavier.basicPathfinderServer.json;

public class ClassTrackedFeatureJson {
	private final int id;
	private final String name;
	private final String description;
	private final int amount;

	public ClassTrackedFeatureJson(int id, String name, String description, int amount) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.amount = amount;
	}
	
	public int getId() {
		return id;
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
