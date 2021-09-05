package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import org.junit.jupiter.api.Test;

class SkillsDatabaseModifierTest {

	@Test
	void test() {
		SkillsDatabaseModifier.addClassSkill("testtest", "Acrobatics");
		SkillsDatabaseModifier.deleteClassSkill("testtest", "Acrobatics");
		
		SkillsDatabaseModifier.setRanks("testtest", "Acrobatics", 5);
		SkillsDatabaseModifier.setRanks("testtest", "Acrobatics", 6);
		SkillsDatabaseModifier.deleteRanks("testtest", "Acrobatics");
	}

}
