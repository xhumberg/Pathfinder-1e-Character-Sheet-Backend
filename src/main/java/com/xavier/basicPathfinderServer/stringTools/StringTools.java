package com.xavier.basicPathfinderServer.stringTools;

import java.util.Random;

public class StringTools {
	public static String generateRandomCharacterId() {
		Random rand = new Random();
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			int randomNumber = rand.nextInt(26+26+10);
			if (randomNumber < 26) {
				randomNumber+=65; //'A' is ascii 65.
				result.append((char)randomNumber);
			} else if (randomNumber < 52) {
				randomNumber-=26; //Return to base 0
				randomNumber+=97; //'a' is ascii 97.
				result.append((char)randomNumber);
			} else {
				randomNumber-=52; //Return to base 0
				result.append(randomNumber); //Literal number
			}
		}
		return result.toString();
	}
}
