package com.xavier.basicPathfinderServer;

public class LoadCharacterJson {

	private final String characterName;
	private final int characterID;
	
	public LoadCharacterJson(String characterName, int characterID) {
		this.characterName = characterName;
		this.characterID = characterID;
	}

	public String getCharacterName() {
		return characterName;
	}

	public int getCharacterID() {
		return characterID;
	}

}
