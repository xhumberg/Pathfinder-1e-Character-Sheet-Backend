package com.xavier.basicPathfinderServer.controllers.jsonObjects;

public class CastUncastSpellJson {
    public final String googleToken;
    public final String classId;
    public final String spellName;
    public final String spellLevel;
    
    public CastUncastSpellJson(String googleToken, String classId, String spellName, String spellLevel) {
    	this.googleToken = googleToken;
    	this.classId = classId;
    	this.spellName = spellName;
    	this.spellLevel = spellLevel;
    }
}
