package com.xavier.basicPathfinderServer.controllers;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.xavier.basicPathfinderServer.json.ItemJson;

class ItemControllerTest {

	@Test
	void test() {
		ItemController items = new ItemController();
		List<ItemJson> itemsFromDb = items.getAllItemsFromDatabase();
		System.out.println(itemsFromDb);
	}

}
