package com.xavier.basicPathfinderServer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.xavier.basicPathfinderServer.numericals.Adjustment;
import com.xavier.basicPathfinderServer.numericals.Stat;
import com.xavier.basicPathfinderServer.numericals.StatName;

class StatTest {

	@Test
	void hasAdjustmentTest() {
		Stat skill = new Stat(StatName.ACROBATICS);
		
		Adjustment testAdjustment = new Adjustment(-1, "Test Adjustment", true);
		testAdjustment.addEffect(StatName.ACROBATICS, "All Skills", 4);
		
		skill.addAdjustment(testAdjustment);
		
		assertTrue(skill.hasModifiers());
		assertFalse(skill.hasModifiers("All Skills"));
		
		testAdjustment.toggleAdjustment();
		
		assertFalse(skill.hasModifiers());
	}

}
