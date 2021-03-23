package com.xavier.basicPathfinderServer;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Adjustment {
	public final String name;
	Map<String, Map<String, Stat>> valuesToAdjust;
	private boolean enabled;
	
	public Adjustment(String name) {
		this(name, false);
	}
	
	public Adjustment(String name, boolean initiallyEnabled) {
		this.name = name;
		valuesToAdjust = new HashMap<>();
		enabled=initiallyEnabled;
	}

	public int getValue(String bonusType, String statName) {
		if (!enabled)
			return 0;
		if (valuesToAdjust.get(bonusType) != null && valuesToAdjust.get(bonusType).get(statName) != null)
			return valuesToAdjust.get(bonusType).get(statName).getValue();
		return 0;
	}

	public void addEffect(String name, String type, int adjustmentValue) {
		addEffect(name, type, new SolidStat(name, adjustmentValue));
	}

	public void addEffect(String name, String type, Stat stat) {
//		valuesToAdjust.put(name, adjustmentValue);
		Map<String, Stat> adjustmentsOfType = valuesToAdjust.get(type);
		if (adjustmentsOfType == null) {
			valuesToAdjust.put(type, new HashMap<>());
			adjustmentsOfType = valuesToAdjust.get(type);
		}
		
		adjustmentsOfType.put(name, stat);
	}

	public Set<String> getAdjustedStats() {
		Set<String> stats = new HashSet<>();
		for (String type : valuesToAdjust.keySet()) {
			stats.addAll(valuesToAdjust.get(type).keySet());
		}
		return stats;
	}
	
	public void toggleAdjustment() {
		enabled = !enabled;
	}

	public Collection<String> getBonusTypes() {
		return valuesToAdjust.keySet();
	}

	public boolean hasBonusOfType(String bonusType) {
		return valuesToAdjust.keySet().contains(bonusType);
	}

	@Override
	public String toString() {
		return "Adjustment [name=" + name + ", enabled=" + enabled + ", valuesToAdjust=" + valuesToAdjust + "]";
	}
	
	public String getName() {
		return name;
	}
}
