package com.xavier.basicPathfinderServer;

import java.util.Map;

public class Adjustment {
	public final String name;
	Map<String, Integer> valuesToAdjust;
	
	public Adjustment(String name) {
		this.name = name;
	}
	
	public int getValue(String statName) {
		Integer value = valuesToAdjust.get(statName);
		return (value == null) ? 0 : value;
	}
}
