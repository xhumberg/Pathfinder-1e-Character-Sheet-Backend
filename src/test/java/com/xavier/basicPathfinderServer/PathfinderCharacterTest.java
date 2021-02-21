package com.xavier.basicPathfinderServer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PathfinderCharacterTest {

	@Test
	void rageTest() {
		PathfinderCharacter character = new PathfinderCharacter("Grog", null);
		
		Adjustment rage = new Adjustment("Rage");
		rage.addEffect("Strength", 4);
		rage.addEffect("Constitution", 4);
		rage.addEffect("Charisma", -2);
		
		character.addAdjustment(rage);
		
		assertEquals(10, character.getStatValue("Strength"));
		assertEquals(10, character.getStatValue("Constitution"));
		assertEquals(10, character.getStatValue("Charisma"));
		
		character.toggleAdjustment("Rage");
		assertEquals(14, character.getStatValue("Strength"));
		assertEquals(14, character.getStatValue("Constitution"));
		assertEquals(8, character.getStatValue("Charisma"));
	}

}
