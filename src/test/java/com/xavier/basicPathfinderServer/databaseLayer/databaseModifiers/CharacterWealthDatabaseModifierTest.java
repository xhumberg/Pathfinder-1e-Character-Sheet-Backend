package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import org.junit.jupiter.api.Test;

class CharacterWealthDatabaseModifierTest {

	@Test
	void test() {
		CharacterWealthDatabaseModifier.setWealth("testtestte", 1234, 10);
		CharacterWealthDatabaseModifier.setWealth("testtestte", 1234, 10);
		CharacterWealthDatabaseModifier.test_deleteWealth("testtestte");
	}

}
