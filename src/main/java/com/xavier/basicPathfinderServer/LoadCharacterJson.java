package com.xavier.basicPathfinderServer;

public class LoadCharacterJson {

	private final String characterName;
	private final String characterID;
	
	public LoadCharacterJson(String characterName, String characterID) {
		this.characterName = characterName;
		this.characterID = characterID;
	}

	public String getCharacterName() {
		return characterName;
	}

	public String getCharacterID() {
		return characterID;
	}

}
