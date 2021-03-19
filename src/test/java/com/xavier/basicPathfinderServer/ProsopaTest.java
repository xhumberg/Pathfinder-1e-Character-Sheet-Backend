package com.xavier.basicPathfinderServer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProsopaTest {

	@Test
	void test() {
		PathfinderCharacter prosopa = Prosopa.get();
		
		assertEquals(49, prosopa.getMaxHealth());
		assertEquals(56, prosopa.getMaxRanks());
	}

}
