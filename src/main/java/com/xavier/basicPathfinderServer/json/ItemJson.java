package com.xavier.basicPathfinderServer.json;

import com.google.gson.Gson;

public class ItemJson {
	private final int itemId;
	private final String name;
	private final String description;
	private final int trueCost;
	private final int actuallyPaid;
	private final String adjustmentString;
	
	public ItemJson(int itemId, String name, String description, int trueCost, int actuallyPaid, String adjustmentString) {
		this.itemId = itemId;
		this.name = name;
		this.description = description;
		this.trueCost = trueCost;
		this.actuallyPaid = actuallyPaid;
		this.adjustmentString = adjustmentString;
	}

	public int getItemId() {
		return itemId;
	}
	
	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public int getTrueCost() {
		return trueCost;
	}

	public int getActuallyPaid() {
		return actuallyPaid;
	}
	
	public String getAdjustmentString() {
		return adjustmentString;
	}
	
	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
}
