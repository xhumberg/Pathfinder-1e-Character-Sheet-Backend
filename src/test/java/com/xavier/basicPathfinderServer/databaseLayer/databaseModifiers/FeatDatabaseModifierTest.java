package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FeatDatabaseModifierTest {

	@Test
	void test() {
		int featId = FeatDatabaseModifier.addNewFeat("Test", "This is a test feat", "");
		FeatDatabaseModifier.giveFeatToCharacter(featId, "testtest");
		FeatDatabaseModifier.deleteFeat(featId);
	}

}
