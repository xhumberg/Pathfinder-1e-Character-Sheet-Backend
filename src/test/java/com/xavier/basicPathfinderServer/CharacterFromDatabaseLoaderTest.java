package com.xavier.basicPathfinderServer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.xavier.basicPathfinderServer.databaseLayer.CharacterFromDatabaseLoader;

class CharacterFromDatabaseLoaderTest {

	@Test
	void test() {
		PathfinderCharacter prosopa = CharacterFromDatabaseLoader.loadCharacter("0");
		assertEquals("Prosopa Dramatis", prosopa.getName());
		assertTrue(adjustmentsContainsHeroism(prosopa));
		assertEquals(4, prosopa.getStatValue("Fortitude"));
		prosopa.toggleAdjustment("Heroism");
		assertEquals(2, prosopa.getStatValue("Fortitude"));
	}

	private boolean adjustmentsContainsHeroism(PathfinderCharacter prosopa) {
		List<Adjustment> adjustments = prosopa.getAllowedAdjustments();
		for (Adjustment adjustment : adjustments) {
			if (adjustment.getName().equals("Heroism"))
				return true;
		}
		return false;
	}

}
