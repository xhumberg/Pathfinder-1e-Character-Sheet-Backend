package com.xavier.basicPathfinderServer;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Adjustment {
	public final String name;
	Map<String, Integer> valuesToAdjust;
	private boolean enabled;
	
	public Adjustment(String name) {
		this.name = name;
		valuesToAdjust = new HashMap<>();
	}
	
	public int getValue(String statName) {
		if (!enabled)
			return 0;
		Integer value = valuesToAdjust.get(statName);
		return (value == null) ? 0 : value;
	}

	public void addEffect(String name, int adjustmentValue) {
		valuesToAdjust.put(name, adjustmentValue);
	}

	public Set<String> getAdjustedStats() {
		return valuesToAdjust.keySet();
	}
	
	public void toggleAdjustment() {
		enabled = !enabled;
	}
}
