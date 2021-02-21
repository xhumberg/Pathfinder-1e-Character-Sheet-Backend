package com.xavier.basicPathfinderServer;

import java.util.HashSet;
import java.util.Set;

public class Stat {
	public final String name;
	public int baseValue;
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
		for (Adjustment adjustment : adjustments) {
			baseValue += adjustment.getValue(name);
		}
		return totalValue;
	}
	
	public void setBaseValue(int value) {
		this.baseValue = value;
	}
	
	public String getName() {
		return name;
	}

}
