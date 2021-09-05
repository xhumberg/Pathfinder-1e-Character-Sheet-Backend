package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import org.junit.jupiter.api.Test;

class WeaponsDatabaseModifierTest {

	@Test
	void test() {
		int weaponId = WeaponsDatabaseModifier.addNewWeapon("Test", "d5", "R", "", 5, 9999, 2, 5, "Test weapon", "God", "God", "Only to be wielded by god", 9999, "God");
		WeaponsDatabaseModifier.giveWeaponToCharacter(weaponId, "testtest", "Dexterity", "Wisdom");
		WeaponsDatabaseModifier.takeWeaponFromCharacter(weaponId, "testtest");
		WeaponsDatabaseModifier.deleteWeapon(weaponId);
	}

}
