package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import org.junit.jupiter.api.Test;

class HealthDatabaseModifierTest {

	@Test
	void test() {
		HealthDatabaseModifier.setDamageTaken(10, "testtest");
		HealthDatabaseModifier.test_deleteCharacterHp("testtest");
		
		HealthDatabaseModifier.setClassBonusHP(10, "testtest");
		HealthDatabaseModifier.setNonlethalDamageTaken(14, "testtest");
		HealthDatabaseModifier.setTempHp(4, "testtest");
		HealthDatabaseModifier.test_deleteCharacterHp("testtest");
	}

}
