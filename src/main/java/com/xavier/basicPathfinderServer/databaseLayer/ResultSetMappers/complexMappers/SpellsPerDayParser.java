package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers;

import java.util.HashMap;
import java.util.Map;

public class SpellsPerDayParser {

	public static Map<Integer, Integer> parse(String spellsPerDayString) {
		HashMap<Integer, Integer> spellsPerDay = new HashMap<>();
		
		String[] byLevel = spellsPerDayString.split(" ");
		for (String level : byLevel) {
			String[] info = level.split("-");
			spellsPerDay.put(Integer.parseInt(info[0]), Integer.parseInt(info[1]));
		}
		
		return spellsPerDay;
	}

}
