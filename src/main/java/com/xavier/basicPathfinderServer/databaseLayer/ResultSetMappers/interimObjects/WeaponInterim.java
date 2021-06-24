package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.interimObjects;

import com.xavier.basicPathfinderServer.characterOwned.Weapon;

public class WeaponInterim {
	
	public Weapon weapon;
	public String attackStat;
	public String damageStat;
	
	public WeaponInterim(Weapon weapon, String attackStat, String damageStat) {
		this.weapon = weapon;
		this.attackStat = attackStat;
		this.damageStat = damageStat;
	}
}
