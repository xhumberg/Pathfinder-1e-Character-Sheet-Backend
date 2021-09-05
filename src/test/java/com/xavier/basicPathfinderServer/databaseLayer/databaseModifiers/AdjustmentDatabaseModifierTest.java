package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import org.junit.jupiter.api.Test;

import com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers.AdjustmentDatabaseModifier;

class AdjustmentDatabaseModifierTest {

	@Test
	void basicAddAndDeleteTest() {
		int newAdjustmentId = AdjustmentDatabaseModifier.addNewAdjustment("Test", "");
		//Test getting the new adjustment
		AdjustmentDatabaseModifier.deleteStandardAdjustment(newAdjustmentId);
		//Ensure new adjustment is no longer there.
	}
	
	@Test
	void allowCharacterNewAdjustmentTest() {
		int newAdjustmentId = AdjustmentDatabaseModifier.addNewAdjustment("Test", "");
		AdjustmentDatabaseModifier.allowCharacterToUseAdjustment(newAdjustmentId, "testtest");
		//Ensure character has allowed adjustment
		AdjustmentDatabaseModifier.deleteStandardAdjustment(newAdjustmentId);
		//Ensure new adjustment is no longer there.
	}
	
	@Test
	void EnableAndDisableCharacterNewAdjustmentTest() {
		int newAdjustmentId = AdjustmentDatabaseModifier.addNewAdjustment("Test", "");
		AdjustmentDatabaseModifier.allowCharacterToUseAdjustment(newAdjustmentId, "testtest");
		AdjustmentDatabaseModifier.enableAdjustment(newAdjustmentId, "testtest");
		//Ensure character has allowed adjustment enabled
		AdjustmentDatabaseModifier.disableAdjustment(newAdjustmentId, "testtest");
		//Ensure character has allowed adjustment disabled
		AdjustmentDatabaseModifier.enableAdjustment(newAdjustmentId, "testtest");
		AdjustmentDatabaseModifier.deleteStandardAdjustment(newAdjustmentId);
		//Ensure new adjustment is no longer there.
	}

}
