package com.xavier.basicPathfinderServer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DatabaseAccessTest {

	@Test
	void test() {
		PathfinderCharacter prosopa = CharacterController.loadCharacter("0");
		assertEquals("Prosopa Dramatis", prosopa.getName());
	}

}
