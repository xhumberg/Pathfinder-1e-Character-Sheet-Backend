package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import com.xavier.basicPathfinderServer.databaseLayer.DatabaseAccess;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.simpleMappers.SingleIntegerMapper;

public class ItemDatabaseModifier {
	
	private final static String GET_LARGEST_AVAILABLE_ITEM_ID = "SELECT ItemID FROM AvailableItems ORDER BY ItemID DESC LIMIT 1";
	private final static String ADD_ITEM = "INSERT INTO AvailableItems VALUES (?, ?, ?, ?, ?, ?)";
	private final static String DELETE_ITEM = "DELETE FROM AvailableItems WHERE ItemId = ?";
	
	private final static String GIVE_CHARACTER_ITEM = "INSERT INTO CharacterEquipment values (?, ?, ?, ?, ?)";
	private final static String TAKE_CHARACTER_ITEM = "DELETE FROM CharacterEquipment WHERE ItemID = ? AND CharacterID = ?";
	private final static String DELETE_ALL_INSTANCES_OF_ITEM_FROM_EQUIPPED = "DELETE FROM CharacterEquipment WHERE ItemID = ?";

	//TODO: Equip and unequip
	
	public static int addNewItem(String name, int cost, String slot, String description, String adjustment) {
		DatabaseAccess<Integer> db = new DatabaseAccess<>();
		int itemId = db.executeSelectQuery(new SingleIntegerMapper(), GET_LARGEST_AVAILABLE_ITEM_ID) + 1;
		db.executeModifyQuery(ADD_ITEM, itemId, name, cost, slot, description, adjustment);
		return itemId;
	}

	public static void deleteItem(int id) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(DELETE_ALL_INSTANCES_OF_ITEM_FROM_EQUIPPED, id);
		db.executeModifyQuery(DELETE_ITEM, id);
	}
	
	public static void giveItemToCharacter(int itemId, String characterId, int trackedResourceId, int trueCost, boolean equipped) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(GIVE_CHARACTER_ITEM, characterId, itemId, trackedResourceId, trueCost, equipped);
	}
	
	public static void takeItemFromCharacter(int itemId, String characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(TAKE_CHARACTER_ITEM, itemId, characterId);
	}
}
