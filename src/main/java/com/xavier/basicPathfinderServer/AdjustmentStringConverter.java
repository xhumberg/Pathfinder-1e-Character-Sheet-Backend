package com.xavier.basicPathfinderServer;

public class AdjustmentStringConverter {
	public static Adjustment convert(String name, String adjustmentString) {
		String[] adjustmentStrings = adjustmentString.split(";");
		Adjustment newAdjustment = new Adjustment(name);
		for (String currentAdjustmentString : adjustmentStrings) {
			currentAdjustmentString = currentAdjustmentString.trim();
			String[] adjustmentDefinition = currentAdjustmentString.split("##");
			newAdjustment.addEffect(adjustmentDefinition[0], adjustmentDefinition[1], Integer.parseInt(adjustmentDefinition[2]));
		}
		return newAdjustment;
	}
}
