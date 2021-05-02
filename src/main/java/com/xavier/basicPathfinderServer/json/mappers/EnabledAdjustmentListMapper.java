package com.xavier.basicPathfinderServer.json.mappers;

import java.util.LinkedList;
import java.util.List;

import com.xavier.basicPathfinderServer.Adjustment;

public class EnabledAdjustmentListMapper {
	public static List<String> map(List<Adjustment> allowedAdjustments) {
		List<String> adjustments = new LinkedList<>();
		for (Adjustment adj : allowedAdjustments) {
			if (adj.isEnabled()) {
				adjustments.add(adj.getName());
			}
		}
		
		return adjustments;
	}
}
