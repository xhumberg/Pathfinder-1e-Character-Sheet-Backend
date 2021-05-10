package com.xavier.basicPathfinderServer.json;

public class TrackedItemJson {
	private final int resourceId;
	private final String name;
	private final String description;
	private final int amount;

	public TrackedItemJson(int resourceId, String name, String description, int amount) {
		this.resourceId = resourceId;
		this.name = name;
		this.description = description;
		this.amount = amount;
	}
	
	public int getResourceId() {
		return resourceId;
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
