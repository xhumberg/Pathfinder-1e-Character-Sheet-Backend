package com.xavier.basicPathfinderServer;

import java.util.ArrayList;
import java.util.List;

public class HP {

	private Ability abilityPerHitDice;
	List<Integer> hitDice;
	private int favoredClassBonus;
	private int currentDamage;
	
	public HP(Ability abilityPerHitDice) {
		this.abilityPerHitDice = abilityPerHitDice;
		hitDice = new ArrayList<>();
		currentDamage = 0;
	}

	public void addHitDice(int numberOfDice, int diceType) {
		while (numberOfDice-- > 0) {
			hitDice.add(diceType);
		}
	}

	public int getMaxHealth() {
		int currentHealth = 0;
		if (hitDice.size() > 0) {
			currentHealth += hitDice.get(0);
		}
		if (hitDice.size() > 1) {
			for (int i = 1; i < hitDice.size(); i++) {
				currentHealth += hitDice.get(i)/2+1;
			}
		}
		currentHealth += abilityPerHitDice.getMod()*hitDice.size();
		currentHealth += favoredClassBonus;
		
		return Math.max(1, currentHealth);
	}

	public void setFavoredClassBonusHP(int classBonus) {
		favoredClassBonus = classBonus;
	}

	public int getCurrentHealth() {
		return getMaxHealth() - currentDamage;
	}

	public void takeDamage(int damage) {
		currentDamage += damage;
	}

	public void heal(int health) {
		currentDamage = Math.max(0, currentDamage - health);
	}

	public void fullHeal() {
		currentDamage = 0;
	}
}
