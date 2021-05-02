package com.xavier.basicPathfinderServer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Adjustment {
	public final String name;
	Map<String, Map<String, Stat>> valuesToAdjust;
	private boolean enabled;
	public final List<String> types;
	public final List<String> senses;
	public final List<String> specialDefenses;
	public final List<String> specialOffenses;
	public String speed;
	
	public Adjustment(String name) {
		this(name, false);
	}
	
	public Adjustment(String name, boolean initiallyEnabled) {
		this.name = name;
		valuesToAdjust = new HashMap<>();
		enabled=initiallyEnabled;
		types = new ArrayList<>();
		senses = new ArrayList<>();
		specialDefenses = new ArrayList<>();
		specialOffenses = new ArrayList<>();
	}
	
	public boolean isEnabled() {
		return enabled;
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

	public boolean isEmpty() {
		return (valuesToAdjust.isEmpty() && types.isEmpty() && senses.isEmpty() && specialDefenses.isEmpty() && specialOffenses.isEmpty() && (speed == null));
	}

	public void addSenses(List<String> senses) {
		this.senses.addAll(senses);
	}
	
	public void addTypes(List<String> types) {
		this.types.addAll(types);
	}

	public void addSpecialDefenses(List<String> defenses) {
		this.specialDefenses.addAll(defenses);
	}

	public void addSpecialOffenses(List<String> offenses) {
		this.specialOffenses.addAll(offenses);
	}

	public void addSpeed(String speed) {
		this.speed = speed;
	}
}
