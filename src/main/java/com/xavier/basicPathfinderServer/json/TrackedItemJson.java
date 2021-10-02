package com.xavier.basicPathfinderServer.json;

public class TrackedItemJson {
	private final int itemId;
	private final int resourceId;
	private final String name;
	private final String description;
	private final int amount;
	private final int trueCost;
	private final int actuallyPaid;

	public TrackedItemJson(int itemId, int resourceId, String name, String description, int amount, int trueCost, int actuallyPaid) {
		this.itemId = itemId;
		this.resourceId = resourceId;
		this.name = name;
		this.description = description;
		this.amount = amount;
		this.trueCost = trueCost;
		this.actuallyPaid = actuallyPaid;
	}
	
	public int getItemId() {
		return itemId;
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
	
	public int getTrueCost() {
		return trueCost;
	}

	public int getActuallyPaid() {
		return actuallyPaid;
	}
}
