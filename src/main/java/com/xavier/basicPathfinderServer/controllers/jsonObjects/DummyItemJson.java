package com.xavier.basicPathfinderServer.controllers.jsonObjects;

public class DummyItemJson {
	private final String token;
	private final String characterId;
	
	public DummyItemJson(String token, String characterId) {
		super();
		this.token = token;
		this.characterId = characterId;
	}
	public String getToken() {
		return token;
	}
	public String getCharacterId() {
		return characterId;
	}
	
	
}
