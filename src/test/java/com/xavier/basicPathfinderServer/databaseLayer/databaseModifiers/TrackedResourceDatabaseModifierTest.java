package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import org.junit.jupiter.api.Test;

class TrackedResourceDatabaseModifierTest {

	@Test
	void test() {
		int resourceID = TrackedResourceDatabaseModifier.addNewTrackedResource("Test", "Test resource", 5, 99);
		TrackedResourceDatabaseModifier.giveTrackedResourceToCharacter(resourceID, "testtest");
		TrackedResourceDatabaseModifier.takeTrackedResourceFromCharacter(resourceID, "testtest");
		TrackedResourceDatabaseModifier.deleteTrackedResource(resourceID);
	}

}
