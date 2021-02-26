package com.xavier.basicPathfinderServer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PathfinderCharacterTest {

	@Test
	void rageTest() {
		PathfinderCharacter character = new PathfinderCharacter("Grog", null);
		
		Adjustment rage = new Adjustment("Rage");
		rage.addEffect("Strength", "Morale", 4);
		rage.addEffect("Constitution", "Morale", 4);
		rage.addEffect("Charisma", "Penalty", -2);
		
		character.addAdjustment(rage);
		
		assertEquals(10, character.getStatValue("Strength"));
		assertEquals(10, character.getStatValue("Constitution"));
		assertEquals(10, character.getStatValue("Charisma"));
		
		character.toggleAdjustment("Rage");
		assertEquals(14, character.getStatValue("Strength"));
		assertEquals(14, character.getStatValue("Constitution"));
		assertEquals(8, character.getStatValue("Charisma"));
	}

	@Test
	void beltAndBullsStrengthTest() {
		PathfinderCharacter character = new PathfinderCharacter("Grog", null);
		
		Adjustment bullsStrength = new Adjustment("Bull's Strength");
		bullsStrength.addEffect("Strength", "Enhancement", 4);
		character.addAdjustment(bullsStrength);
		
		Adjustment beltOfStrength2 = new Adjustment("Belt of Strength +2");
		beltOfStrength2.addEffect("Strength", "Enhancement", 2);
		beltOfStrength2.toggleAdjustment();
		character.addAdjustment(beltOfStrength2);
		
		assertEquals(12, character.getStatValue("Strength"));
		
		character.toggleAdjustment("Bull's Strength");
		assertEquals(14, character.getStatValue("Strength"));
		
		character.toggleAdjustment("Bull's Strength");
		assertEquals(12, character.getStatValue("Strength"));
	}

}
