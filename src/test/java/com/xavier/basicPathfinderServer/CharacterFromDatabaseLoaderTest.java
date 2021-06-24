package com.xavier.basicPathfinderServer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.xavier.basicPathfinderServer.characterOwned.Item;
import com.xavier.basicPathfinderServer.databaseLayer.CharacterFromDatabaseLoader;
import com.xavier.basicPathfinderServer.numericals.Adjustment;

class CharacterFromDatabaseLoaderTest {

	@Test
	void test() {
		PathfinderCharacter prosopa = CharacterFromDatabaseLoader.loadCharacter("0");
		assertEquals("Prosopa Dramatis", prosopa.getName());
		assertTrue(adjustmentsContainsHeroism(prosopa));
		
		assertEquals(24, prosopa.getAbilityValue("Intelligence"));
		
		assertEquals(8, prosopa.getStatValue("Level"));
		assertEquals(3, prosopa.getStatValue("BAB"));
		assertEquals(7, prosopa.getStatValue("Fortitude")); //Heroism is on
		assertEquals(8, prosopa.getStatValue("Reflex"));
		assertEquals(9, prosopa.getStatValue("Will"));

		assertEquals(4, prosopa.getSpellsPerDay(0, 0));
		assertEquals(6, prosopa.getSpellsPerDay(0, 1));
		assertEquals(5, prosopa.getSpellsPerDay(0, 2));
		assertEquals(4, prosopa.getSpellsPerDay(0, 3));
		assertEquals(2, prosopa.getSpellsPerDay(0, 4));
		
		Item magicMissile = prosopa.getAllItemsWithName("Wand of Magic Missile").get(0);
		assertEquals(48, magicMissile.getTrackedResourceRemaining());
		
		assertEquals(22, prosopa.getSpellDC(0, "Acid Pit", 4));
		assertEquals(20, prosopa.getSpellDC(0, "Charitable Impulse", 3)); //No spell focus, so 2 lower
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
