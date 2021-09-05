package com.xavier.basicPathfinderServer.numericals;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.xavier.basicPathfinderServer.PathfinderCharacter;

public class AdjustmentStringConverter {
	public static Adjustment convert(PathfinderCharacter character, int id, String name, String adjustmentString, boolean allowEmpty) {
		Adjustment newAdjustment = new Adjustment(id, name);
		adjustmentString = adjustmentString.replaceAll("\\[T:.*?\\]", ""); //Only used in creating new resources.
		adjustmentString = getTypesIfPresent(adjustmentString, newAdjustment);
		adjustmentString = getSpecialDefensesIfPresent(adjustmentString, newAdjustment);
		adjustmentString = getSpecialOffensesIfPresent(adjustmentString, newAdjustment);
		adjustmentString = adjustmentString.replaceAll("\\[Size:.*?\\]", ""); //TODO: Size effects must not override but COVER base size. 
		adjustmentString = getSpeedIfPresent(adjustmentString, newAdjustment);
		adjustmentString = getSensesIfPresent(adjustmentString, newAdjustment);
		if (adjustmentString.trim().equals("")) { //All effects of this adjustment are special or the adjustment is empty
			if (newAdjustment.isEmpty() && !allowEmpty) {
				return null;
			} else {
				return newAdjustment;
			}
		}
		String[] adjustmentStrings = adjustmentString.split(";");
		for (String currentAdjustmentString : adjustmentStrings) {
			currentAdjustmentString = currentAdjustmentString.trim();
			String[] adjustmentDefinition = currentAdjustmentString.split("##");
			if (adjustmentDefinition.length < 3) {
				System.out.println("Improperly formatted adjustment: '" + currentAdjustmentString + "'");
			}
			StatName statName = StatName.decode(adjustmentDefinition[0]);
			newAdjustment.addEffect(statName, adjustmentDefinition[1], getStatFromDefinition(character, statName, adjustmentDefinition[2]));
		}
		return newAdjustment;
	}

	private static Stat getStatFromDefinition(PathfinderCharacter character, StatName statName, String adjustmentDefinition) {
		if (adjustmentDefinition.contains("{")) {
			//Currently only supports JUST a stat.
			String innerStatName = adjustmentDefinition.replace("{", "").replace("}", "");
			return character.getStat(StatName.decode(innerStatName));
		}
		return new SolidStat(statName, Integer.parseInt(adjustmentDefinition));
	}

	private static String getTypesIfPresent(String adjustmentString, Adjustment newAdjustment) {
		List<String> types = ripStringsForPattern("\\[Type:.*?\\]", adjustmentString);
		if (types.size() > 0) {
			newAdjustment.addTypes(types);
		}
		
		return adjustmentString.replaceAll("\\[Type:.*?\\]", "");
	}
	
	private static String getSpecialDefensesIfPresent(String adjustmentString, Adjustment newAdjustment) {
		List<String> defenses = ripStringsForPattern("\\[Special Defense:.*?\\]", adjustmentString);
		if (defenses.size() > 0) {
			newAdjustment.addSpecialDefenses(defenses);
		}
		
		return adjustmentString.replaceAll("\\[Special Defense:.*?\\]", "");
	}
	
	private static String getSpecialOffensesIfPresent(String adjustmentString, Adjustment newAdjustment) {
		List<String> offenses = ripStringsForPattern("\\[Special Offense:.*?\\]", adjustmentString);
		if (offenses.size() > 0) {
			newAdjustment.addSpecialOffenses(offenses);
		}
		
		return adjustmentString.replaceAll("\\[Special Offense:.*?\\]", "");
	}
	
	private static String getSensesIfPresent(String adjustmentString, Adjustment newAdjustment) {
		List<String> senses = ripStringsForPattern("\\[Sense:.*?\\]", adjustmentString);
		if (senses.size() > 0) {
			newAdjustment.addSenses(senses);
		}
		
		return adjustmentString.replaceAll("\\[Sense:.*?\\]", "");
	}
	
	private static String getSpeedIfPresent(String adjustmentString, Adjustment newAdjustment) {
		List<String> speed = ripStringsForPattern("\\[Speed:.*?\\]", adjustmentString);
		if (speed.size() > 1) {
			throw new IllegalArgumentException("One adjustment is not allowed to have more than one Speed tag");
		} else if (speed.size() == 1) {
			newAdjustment.addSpeed(speed.get(0));
		}
		
		return adjustmentString.replaceAll("\\[Speed:.*?\\]", "");
	}
	
	private static List<String> ripStringsForPattern(String patternString, String adjustmentString) {
		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(adjustmentString);
		
		List<String> matches = new ArrayList<>();
		
		while (matcher.find()) {
			String match = matcher.group();
			int firstColon = match.indexOf(": ");
			System.out.println("XAH: " + match);
			System.out.println("Adjustment gives character " + match.substring(0, firstColon).replace("[", "") + " of " + match.substring(firstColon+2).replace("]", ""));
			match = match.substring(firstColon+2).replace("]", "");
			matches.add(match);
		}
		
		return matches;
	}
}
