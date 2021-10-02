package com.xavier.basicPathfinderServer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.xavier.basicPathfinderServer.controllers.jsonObjects.DummyItemJson;
import com.xavier.basicPathfinderServer.controllers.jsonObjects.ItemUpdateJson;
import com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers.ItemDatabaseModifier;

@RestController
public class ItemController {

	private Gson gson;
	
	@Autowired
	public ItemController() {
		gson = new Gson();
	}
	
	@PutMapping("/item/{id}/update")
	public void updateItem(@PathVariable String id, @RequestBody String body) {
		ItemUpdateJson itemUpdateJson = gson.fromJson(body, ItemUpdateJson.class);
		int itemId = Integer.parseInt(id);
		ItemDatabaseModifier.updateItem(itemId, itemUpdateJson.getName(), itemUpdateJson.getCost(), itemUpdateJson.getDescription(), itemUpdateJson.getAdjustmentString()); //TODO add adjustment info and slot
		ItemDatabaseModifier.updateCostOfItem(itemId, itemUpdateJson.getCharacterId(), itemUpdateJson.getPaid());
	}
	
	@PutMapping("/item/createDummyItem")
	public void createDummyItem(@RequestBody String body) {
		DummyItemJson dummyItemJson = gson.fromJson(body, DummyItemJson.class);
		int newItem = ItemDatabaseModifier.addNewItem("Dummy", 0, "Dummy", "Dummy", "");
		ItemDatabaseModifier.giveItemToCharacter(newItem, dummyItemJson.getCharacterId(), -1, 0, true);
	}
}
