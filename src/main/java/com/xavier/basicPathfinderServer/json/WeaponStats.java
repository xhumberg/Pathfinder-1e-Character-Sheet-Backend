package com.xavier.basicPathfinderServer.json;

import com.xavier.basicPathfinderServer.Stat;

public class WeaponStats {
	public Stat attackStat;
	public Stat damageStat;

	public WeaponStats(Stat weaponAttackStat, Stat weaponDamageStat) {
		attackStat = weaponAttackStat;
		damageStat = weaponDamageStat;
	}
}
