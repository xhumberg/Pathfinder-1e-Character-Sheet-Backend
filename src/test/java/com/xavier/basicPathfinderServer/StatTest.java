package com.xavier.basicPathfinderServer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StatTest {

	@Test
	void test() {
		Stat strength = new Stat("Strength", 12);
		Adjustment bullsStrength = new Adjustment("Bull's Strength");
		bullsStrength.addEffect("Strength", 4);
		strength.addAdjustment(bullsStrength);
		assertEquals(12, strength.getValue());
		
		bullsStrength.toggleAdjustment();
		assertEquals(16, strength.getValue());
		
		bullsStrength.toggleAdjustment();
		assertEquals(12, strength.getValue());
	}

}
