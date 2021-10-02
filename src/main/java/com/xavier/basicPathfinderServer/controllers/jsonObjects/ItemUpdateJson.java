package com.xavier.basicPathfinderServer.controllers.jsonObjects;

public class ItemUpdateJson {
	public final String token;
	public final int cost;
	public final int paid;
	public final String name;
	public final String description;
	public final String characterId;
	public final String adjustmentString;
	
	public ItemUpdateJson(String token, int cost, int paid, String name, String description, String characterId, String adjustmentString) {
		this.token = token;
		this.cost = cost;
		this.paid = paid;
		this.name = name;
		this.description = description;
		this.characterId = characterId;
		this.adjustmentString = adjustmentString;
	}
	
	public String getToken() {
		return token;
	}
	public int getCost() {
		return cost;
	}
	public int getPaid() {
		return paid;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getCharacterId() {
		return characterId;
	}
	public String getAdjustmentString() {
		return adjustmentString;
	}
}
