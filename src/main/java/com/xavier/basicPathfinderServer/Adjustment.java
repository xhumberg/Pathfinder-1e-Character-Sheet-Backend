package com.xavier.basicPathfinderServer;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Adjustment {
	public final String name;
	Map<String, Map<String, Integer>> valuesToAdjust;
	private boolean enabled;
	
	public Adjustment(String name) {
		this.name = name;
		valuesToAdjust = new HashMap<>();
	}
	
	public int getValue(String bonusType, String statName) {
		if (!enabled)
			return 0;
		if (valuesToAdjust.get(bonusType) != null && valuesToAdjust.get(bonusType).get(statName) != null)
			return valuesToAdjust.get(bonusType).get(statName);
		return 0;
	}

	public void addEffect(String name, String type, int adjustmentValue) {
//		valuesToAdjust.put(name, adjustmentValue);
		Map<String, Integer> adjustmentsOfType = valuesToAdjust.get(type);
		if (adjustmentsOfType == null) {
			valuesToAdjust.put(type, new HashMap<>());
			adjustmentsOfType = valuesToAdjust.get(type);
		}
		
		adjustmentsOfType.put(name, adjustmentValue);
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
}
