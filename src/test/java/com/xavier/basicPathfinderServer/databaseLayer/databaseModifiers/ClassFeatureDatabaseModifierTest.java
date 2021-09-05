package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import org.junit.jupiter.api.Test;

class ClassFeatureDatabaseModifierTest {

	@Test
	void test() {
		int featureID = ClassFeatureDatabaseModifier.addNewFeature("Test", "Test feature", "");
		ClassFeatureDatabaseModifier.giveFeatureToCharacter(featureID, "testtest", -1);
		ClassFeatureDatabaseModifier.takeFeatureFromCharacter(featureID, "testtest");
		ClassFeatureDatabaseModifier.deleteFeature(featureID);
	}

}
