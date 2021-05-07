package com.xavier.basicPathfinderServer.json.mappers;

import com.xavier.basicPathfinderServer.PathfinderCharacter;
import com.xavier.basicPathfinderServer.json.GoldJson;

public class GoldMapper {
	public static GoldJson map(PathfinderCharacter character) {
		return new GoldJson(character.getTotalEarnedGold(), character.getTotalSpentGold(), character.getRemainingGold());
	}
}
