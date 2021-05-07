package com.xavier.basicPathfinderServer.json;

public class GoldJson {
	private final int totalEarned;
	private final int totalSpent;
	private final int totalRemaining;
	
	public GoldJson(int totalEarned, int totalSpent, int totalRemaining) {
		this.totalEarned = totalEarned;
		this.totalSpent = totalSpent;
		this.totalRemaining = totalRemaining;
	}
	
	public int getTotalEarned() {
		return totalEarned;
	}
	public int getTotalSpent() {
		return totalSpent;
	}
	public int getTotalRemaining() {
		return totalRemaining;
	}
}
