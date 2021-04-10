package com.xavier.basicPathfinderServer;

public class AdjustmentStringConverter {
	public static Adjustment convert(String name, String adjustmentString) {
		adjustmentString = adjustmentString.replaceAll("\\[T:.*\\]", "");
		adjustmentString = adjustmentString.replaceAll("\\[Special Save:.*\\]", "");
		adjustmentString = adjustmentString.replaceAll("\\[Type:.*\\]", "");
		adjustmentString = adjustmentString.replaceAll("\\[Special Defense:.*\\]", "");
		adjustmentString = adjustmentString.replaceAll("\\[Size:.*\\]", "");
		adjustmentString = adjustmentString.replaceAll("\\[Speed:.*\\]", "");
		adjustmentString = adjustmentString.replaceAll("\\[Sense:.*\\]", "");
		if (adjustmentString.trim().equals("")) {
			return null;
		}
		String[] adjustmentStrings = adjustmentString.split(";");
		Adjustment newAdjustment = new Adjustment(name);
		for (String currentAdjustmentString : adjustmentStrings) {
			currentAdjustmentString = currentAdjustmentString.trim();
			String[] adjustmentDefinition = currentAdjustmentString.split("##");
			if (adjustmentDefinition.length < 3) {
				System.out.println("Improperly formatted adjustment: '" + currentAdjustmentString + "'");
			}
			newAdjustment.addEffect(adjustmentDefinition[0], adjustmentDefinition[1], Integer.parseInt(adjustmentDefinition[2]));
		}
		return newAdjustment;
	}
}
