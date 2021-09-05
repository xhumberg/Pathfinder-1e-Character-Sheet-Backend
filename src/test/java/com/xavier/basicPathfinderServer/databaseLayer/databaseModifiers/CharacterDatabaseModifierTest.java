package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import org.junit.jupiter.api.Test;

import com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers.CharacterDatabaseModifier;

class CharacterDatabaseModifierTest {

	@Test
	void test() {
		String newCharacterId = CharacterDatabaseModifier.addNewCharacter("Test", "Test", "", "None", "God", "Medium", "Unknown", "Unknown", "Unknown", "Unknown", 10, 10, 10, 10, 10, 10);
		CharacterDatabaseModifier.deleteCharacter(newCharacterId);
	}

}
