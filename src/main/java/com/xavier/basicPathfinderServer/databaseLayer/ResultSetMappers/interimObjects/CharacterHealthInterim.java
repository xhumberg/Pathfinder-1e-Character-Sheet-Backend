package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.interimObjects;

public class CharacterHealthInterim {

	int favoredClassBonusHp;
	int damageTaken;
	int nonLethalDamageTaken; //TODO: implement
	int tempHP; //TODO: implement
	
	public CharacterHealthInterim(int favoredClassBonusHp, int damageTaken) {
		this.favoredClassBonusHp = favoredClassBonusHp;
		this.damageTaken = damageTaken;
	}

	public int getFavoredClassBonusHp() {
		return favoredClassBonusHp;
	}

	public int getDamageTaken() {
		return damageTaken;
	}
	

	
}
