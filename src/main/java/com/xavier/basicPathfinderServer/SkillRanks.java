package com.xavier.basicPathfinderServer;

import java.util.ArrayList;
import java.util.List;

public class SkillRanks {
	private Ability abilityPerHitDice;
	List<Integer> ranksFromLevels;
	private int favoredClassBonus;
	private int spentRanks;
	
	public SkillRanks(Ability abilityPerHitDice) {
		this.abilityPerHitDice = abilityPerHitDice;
		ranksFromLevels = new ArrayList<>();
		spentRanks = 0;
	}

	public void addRanks(int levelsInClass, int numberOfRanks) {
		while (levelsInClass-- > 0) {
			ranksFromLevels.add(numberOfRanks);
		}
	}

	public int getMaxRanks() {
		int maxRanks = 0;
		for (Integer ranks : ranksFromLevels) {
			maxRanks += Math.max(1, ranks + abilityPerHitDice.getMod()); //Minimum 1!
		}
		maxRanks += favoredClassBonus;
		
		return maxRanks;
	}
	
	public int getRemainingRanks() {
		return getMaxRanks() - spentRanks;
	}
	
	public void spendRanks(int ranks) {
		spentRanks += ranks;
	}
}
