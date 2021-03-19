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
		int currentHealth = 0;
		if (ranksFromLevels.size() > 0) {
			currentHealth += ranksFromLevels.get(0);
		}
		if (ranksFromLevels.size() > 1) {
			for (int i = 1; i < ranksFromLevels.size(); i++) {
				currentHealth += ranksFromLevels.get(i)/2+1;
			}
		}
		currentHealth += abilityPerHitDice.getMod()*ranksFromLevels.size();
		currentHealth += favoredClassBonus;
		
		return Math.max(1, currentHealth);
	}
	
	public int getRemainingRanks() {
		return getMaxRanks() - spentRanks;
	}
	
	public void spendRanks(int ranks) {
		spentRanks += ranks;
	}
}
