package com.xavier.basicPathfinderServer.json.mappers;

import java.util.LinkedList;
import java.util.List;

import com.xavier.basicPathfinderServer.characterOwned.Item;
import com.xavier.basicPathfinderServer.json.ItemJson;

public class ItemMapper {
	public static List<ItemJson> map(List<Item> items) {
		List<ItemJson> itemJsons = new LinkedList<ItemJson>();
		
		for (Item item : items) {
			if (!item.hasTrackedResource()) {
				ItemJson itemJson = new ItemJson(item.getName(), item.getDescription().replace("\\r\\n", "")); //TODO: make returns actually put in a line break
				itemJsons.add(itemJson);
			}
		}
		
		return itemJsons;
	}
}
