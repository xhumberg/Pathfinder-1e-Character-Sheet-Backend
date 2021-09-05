package com.xavier.basicPathfinderServer.numericals;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.xavier.basicPathfinderServer.PathfinderCharacter;

class AdjustmentStringConverterTest {

	@Test
	void addNamedStatTest() {
		PathfinderCharacter character = new PathfinderCharacter("Dude", "Dude", "");
		character.setAbility(StatName.WISDOM, 14);
		String adjustment = "All Attacks##Wisdom##{Wisdom}";
		Adjustment adj = AdjustmentStringConverter.convert(character, 0, "Test Adjustment", adjustment, false);
		adj.toggleAdjustment();
		assertEquals(2, adj.getValue("Wisdom", StatName.ALL_ATTACKS));
		character.setAbility(StatName.WISDOM, 20);
		assertEquals(5, adj.getValue("Wisdom", StatName.ALL_ATTACKS));
		
	}

}
