package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import org.junit.jupiter.api.Test;

class ClassDatabaseModifierTest {

	@Test
	void test() {
		int classId = ClassDatabaseModifier.addNewClassWithoutSpellcasting(5, 5, 1, 2, 3, 4, 10, "Test Class");
		ClassDatabaseModifier.giveClassToCharacter(classId, "testtest");
		ClassDatabaseModifier.takeClassFromCharacter(classId, "testtest");
		ClassDatabaseModifier.deleteClass(classId);
	}

}
