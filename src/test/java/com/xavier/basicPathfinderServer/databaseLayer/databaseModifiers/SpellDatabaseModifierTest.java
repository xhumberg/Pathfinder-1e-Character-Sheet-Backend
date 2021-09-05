package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import org.junit.jupiter.api.Test;

class SpellDatabaseModifierTest {

	@Test
	void test() {
		SpellDatabaseModifier.addSpellKnown("testtest", 9999, 9999);
		SpellDatabaseModifier.addSpellPrepped("testtest", 9999, 9999, 9);
		SpellDatabaseModifier.addSpellPrepped("testtest", 9999, 9999, 9);
		SpellDatabaseModifier.removeSpellPrepped("testtest", 9999, 9999, 9);
		SpellDatabaseModifier.removeSpellPrepped("testtest", 9999, 9999, 9);
		SpellDatabaseModifier.removeSpellKnown("testtest", 9999, 9999);
	}

}
