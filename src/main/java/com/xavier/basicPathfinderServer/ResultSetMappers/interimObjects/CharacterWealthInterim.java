package com.xavier.basicPathfinderServer.ResultSetMappers.interimObjects;

public class CharacterWealthInterim {

	int earnedGold;
	int spentGold;
	
	public CharacterWealthInterim(int earnedGold, int spentGold) {
		this.earnedGold = earnedGold;
		this.spentGold = spentGold;
	}

	public int getEarnedGold() {
		return earnedGold;
	}

	public int getSpentGold() {
		return spentGold;
	}
	
}
