package com.xavier.basicPathfinderServer.json.mappers;

import java.util.LinkedList;
import java.util.List;

import com.xavier.basicPathfinderServer.characterOwned.Item;
import com.xavier.basicPathfinderServer.json.TrackedItemJson;

public class TrackedItemMapper {
	public static List<TrackedItemJson> map(List<Item> items) {
		List<TrackedItemJson> itemJsons = new LinkedList<>();
		
		for (Item item : items) {
			if (item.hasTrackedResource()) {
				TrackedItemJson json = new TrackedItemJson(item.getItemId(), item.getTrackedResourceId(), item.getName(), item.getDescription(), item.getTrackedResourceRemaining(), item.getCost(), item.getTrueCost());
				itemJsons.add(json);
			}
		}
		
		return itemJsons;
	}
}
