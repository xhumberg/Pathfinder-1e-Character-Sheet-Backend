package com.xavier.basicPathfinderServer.controllers.jsonObjects;

public class SkillRankUpdateJson {
    public final String googleToken;
    public final String skillName;
    public final String skillRanks;
    
    
	public SkillRankUpdateJson(String googleToken, String skillName, String skillRanks) {
		this.googleToken = googleToken;
		this.skillName = skillName;
		this.skillRanks = skillRanks;
	}
}
