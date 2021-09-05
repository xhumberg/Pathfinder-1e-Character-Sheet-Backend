package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RacialTraitDatabaseModifierTest {

	@Test
	void test() {
		int traitId = RacialTraitDatabaseModifier.addNewRacialTrait("Test", "Test Trait", "");
		RacialTraitDatabaseModifier.giveRacialTraitToCharacter(traitId, "testtest");
		RacialTraitDatabaseModifier.takeRacialTraitFromCharacter(traitId, "testtest");
		RacialTraitDatabaseModifier.deleteRacialTrait(traitId);
	}

}
