package com.xavier.basicPathfinderServer.stringTools;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

class StringToolsTest {

	@Test
	void test() {
		for (int i = 0; i < 1_000_000; i++) {
			String randomString = StringTools.generateRandomCharacterId();
			assertTrue(Pattern.matches("[\\da-zA-Z]{10}", randomString));
		}
	}

}
