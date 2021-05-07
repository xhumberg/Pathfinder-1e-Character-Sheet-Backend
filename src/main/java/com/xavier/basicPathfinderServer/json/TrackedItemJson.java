package com.xavier.basicPathfinderServer.json;

public class TrackedItemJson {
	private final String name;
	private final String description;
	private final int amount;

	public TrackedItemJson(String name, String description, int amount) {
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
