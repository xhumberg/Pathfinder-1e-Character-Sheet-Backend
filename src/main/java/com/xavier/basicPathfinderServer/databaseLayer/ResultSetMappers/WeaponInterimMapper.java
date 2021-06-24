package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xavier.basicPathfinderServer.characterOwned.Weapon;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.interimObjects.WeaponInterim;

public class WeaponInterimMapper implements ResultSetMapper<Object> {

	@Override
	public List<WeaponInterim> map(ResultSet resultSet) {
		List<WeaponInterim> weapons = new ArrayList<>();
		try {
			while (resultSet.next()) {
				String name = resultSet.getString("WeaponName");
				String damageDice = resultSet.getString("DamageDice");
				String damageType = resultSet.getString("DamageType");
				String modifiers = resultSet.getString("WeaponModifiers");
				int attackMod = resultSet.getInt("AttackMod");
				int damageMod = resultSet.getInt("DamageMod");
				int critLow = resultSet.getInt("CritLow");
				int critMultiplier = resultSet.getInt("CritMultiplier");
				String description = resultSet.getString("WeaponDescription");
				String category = resultSet.getString("WeaponCategory");
				String proficiency = resultSet.getString("WeaponProficiency");
				String special = resultSet.getString("WeaponSpecialTags");
				int weight = resultSet.getInt("Weight");
				String weaponGroups = resultSet.getString("WeaponGroups");
				
				Weapon weapon = new Weapon(name, damageDice, damageType, modifiers, attackMod, damageMod, critLow, critMultiplier, description, category, proficiency, special, weight, weaponGroups);
				
				String attackStat = resultSet.getString("WeaponAttackStat");
				String damageStat = resultSet.getString("WeaponDamageStat");
				
				WeaponInterim weaponInterim = new WeaponInterim(weapon, attackStat, damageStat);
				
				weapons.add(weaponInterim);
				
				System.out.println("Found weapon: " + name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return weapons;
	}

}
