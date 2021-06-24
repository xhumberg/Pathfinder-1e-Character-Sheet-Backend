package com.xavier.basicPathfinderServer.numericals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Adjustment {
	public final int id;
	public final String name;
	Map<String, Map<StatName, Stat>> valuesToAdjust;
	private boolean enabled;
	public final List<String> types;
	public final List<String> senses;
	public final List<String> specialDefenses;
	public final List<String> specialOffenses;
	public String speed;
	
	public Adjustment(int id, String name) {
		this(id, name, false);
	}
	
	public Adjustment(int id, String name, boolean initiallyEnabled) {
		this.id = id;
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

	public int getValue(String bonusType, StatName statName) {
		if (!enabled)
			return 0;
		if (valuesToAdjust.get(bonusType) != null && valuesToAdjust.get(bonusType).get(statName) != null)
			return valuesToAdjust.get(bonusType).get(statName).getValue();
		return 0;
	}

	public void addEffect(StatName name, String type, int adjustmentValue) {
		addEffect(name, type, new SolidStat(name, adjustmentValue));
	}

	public void addEffect(StatName name, String type, Stat stat) {
//		valuesToAdjust.put(name, adjustmentValue);
		Map<StatName, Stat> adjustmentsOfType = valuesToAdjust.get(type);
		if (adjustmentsOfType == null) {
			valuesToAdjust.put(type, new HashMap<>());
			adjustmentsOfType = valuesToAdjust.get(type);
		}
		
		adjustmentsOfType.put(name, stat);
	}

	public Set<StatName> getAdjustedStats() {
		Set<StatName> stats = new HashSet<>();
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

	public int getId() {
		return id;
	}

	public boolean hasEffectNotOfTypes(String... adjustmentTypesToIgnore) {
		HashSet<String> filters = new HashSet<String>(Arrays.asList(adjustmentTypesToIgnore));
		for (String type : valuesToAdjust.keySet()) {
			if (filters.contains(type)) {
				continue;
			}
			return true;
		}
		return false;
	}
}
