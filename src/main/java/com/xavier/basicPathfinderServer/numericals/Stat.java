package com.xavier.basicPathfinderServer.numericals;

import java.util.HashSet;
import java.util.Set;

public class Stat {
	private final String name;
	protected int baseValue;
	private Set<Adjustment> adjustments;
	
	public Stat(String name) {
		this(name, 0);
	}
	
	public Stat(String name, int baseValue) {
		this.name = name;
		this.baseValue = baseValue;
		adjustments = new HashSet<Adjustment>();
	}

	public int getValue() {
		int totalValue = baseValue;
		Set<String> bonusTypes = getAllBonusTypes();
		for (String bonusType : bonusTypes) {
			if (bonusType.equals("Circumstance") || bonusType.equals("Dodge") || bonusType.equals("Penalty")) {
				totalValue = totalValue + getAllBonusesOfType(bonusType);
			} else {
				totalValue = totalValue + getHighestBonusOfType(bonusType);
			}
		}
		return totalValue;
	}
	
	private int getHighestBonusOfType(String bonusType) {
		int highestBonus = Integer.MIN_VALUE;
		for (Adjustment adjustment : adjustments) {
			if (adjustment.hasBonusOfType(bonusType)) {
				highestBonus = Math.max(highestBonus, adjustment.getValue(bonusType, name));
			}
		}
		return highestBonus;
	}
	
	private int getAllBonusesOfType(String bonusType) {
		int allBonus = 0;
		for (Adjustment adjustment : adjustments) {
			if (adjustment.hasBonusOfType(bonusType)) {
				allBonus = allBonus + adjustment.getValue(bonusType, name);
			}
		}
		return allBonus;
	}
	
	@Override
	public String toString() {
		return "[Stat: name=" + name + ", baseValue=" + baseValue + ", hasAdjustments=" + !adjustments.isEmpty() + "]";
	}

	public Set<String> getAllBonusTypes() {
		Set<String> allBonusTypes = new HashSet<>();
		for (Adjustment adjustment : adjustments) {
			allBonusTypes.addAll(adjustment.getBonusTypes());
		}
		return allBonusTypes;
	}
	
	public void setBaseValue(int value) {
		this.baseValue = value;
	}
	
	public String getName() {
		return name;
	}

	public void addAdjustment(Adjustment adjustment) {
		adjustments.add(adjustment);
	}

	public void addStat(Stat stat) {
		Adjustment abilityAdj = new Adjustment(-1, stat.getName(), true);
		abilityAdj.addEffect(name, stat.getName(), stat);
		this.addAdjustment(abilityAdj);
	}

	public int getBase() {
		return baseValue;
	}

	public boolean hasModifiers(String... adjustmentTypesToIgnore) {
		
		if (baseValue != 0) {
			return true;
		}
		
		for (Adjustment adjustment: adjustments) {
			if (adjustment.isEnabled() && adjustment.hasEffectNotOfTypes(adjustmentTypesToIgnore)) {
				return true;
			}
		}
		return false;
	}

}
