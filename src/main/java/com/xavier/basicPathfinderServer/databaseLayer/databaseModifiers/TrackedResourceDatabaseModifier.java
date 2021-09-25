package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import com.xavier.basicPathfinderServer.databaseLayer.DatabaseAccess;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.simpleMappers.SingleIntegerMapper;

public class TrackedResourceDatabaseModifier {

	private final static String GET_LARGEST_AVAILABLE_TRACKED_RESOURCE_ID = "SELECT ResourceID FROM TrackedResources ORDER BY ResourceID DESC LIMIT 1";
	private final static String ADD_TRACKED_RESOURCE = "INSERT INTO TrackedResources VALUES (?, ?, ?, ?, ?)";
	private final static String DELETE_TRACKED_RESOURCE = "DELETE FROM TrackedResources WHERE ResourceID = ?";
	private final static String SET_RESOURCES_REMAINING = "UPDATE TrackedResources SET ResourceRemaining = ? WHERE ResourceID = ?;";
	
	private final static String GIVE_CHARACTER_TRACKED_RESOURCE = "INSERT INTO CharactersTrackedResources values (?, ?)";
	private final static String TAKE_CHARACTER_TRACKED_RESOURCE = "DELETE FROM CharactersTrackedResources WHERE TrackedResourceID = ? AND CharacterID = ?";
	private final static String DELETE_ALL_INSTANCES_OF_TRACKED_RESOURCE_FROM_CHARACTERS = "DELETE FROM CharactersTrackedResources WHERE TrackedResourceID = ?";
	
	public static void setResourcesRemaining(int resourcesRemaining, int resourceId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(SET_RESOURCES_REMAINING, resourcesRemaining, resourceId);
	}
	
	public static int addNewTrackedResource(String name, String description, int remaining, int max) {
		DatabaseAccess<Integer> db = new DatabaseAccess<>();
		int resourceID = db.executeSelectQuery(new SingleIntegerMapper(), GET_LARGEST_AVAILABLE_TRACKED_RESOURCE_ID) + 1;
		db.executeModifyQuery(ADD_TRACKED_RESOURCE, resourceID, name, description, remaining, max);
		return resourceID;
	}

	public static void deleteTrackedResource(int id) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(DELETE_ALL_INSTANCES_OF_TRACKED_RESOURCE_FROM_CHARACTERS, id);
		db.executeModifyQuery(DELETE_TRACKED_RESOURCE, id);
	}
	
	public static void giveTrackedResourceToCharacter(int resourceID, String characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(GIVE_CHARACTER_TRACKED_RESOURCE, characterId, resourceID);
	}
	
	public static void takeTrackedResourceFromCharacter(int resourceID, String characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(TAKE_CHARACTER_TRACKED_RESOURCE, resourceID, characterId);
	}
	
}
