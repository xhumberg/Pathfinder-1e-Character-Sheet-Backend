package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ItemDatabaseModifierTest {

	@Test
	void test() {
		int newItemId = ItemDatabaseModifier.addNewItem("Test", 99999, "none", "This is a test item", "");
		ItemDatabaseModifier.giveItemToCharacter(newItemId, "testtest", -1, 99999, true);
		ItemDatabaseModifier.deleteItem(newItemId);
	}

}
