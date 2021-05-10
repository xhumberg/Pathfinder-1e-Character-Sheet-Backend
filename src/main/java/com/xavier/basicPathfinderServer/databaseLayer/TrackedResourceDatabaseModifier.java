package com.xavier.basicPathfinderServer.databaseLayer;

public class TrackedResourceDatabaseModifier {
	private final static String SET_RESOURCES_REMAINING_QUERY = "UPDATE TrackedResources SET ResourceRemaining = ? WHERE ResourceID = ?;";
	
	public static void setResourcesRemaining(int resourcesRemaining, int resourceId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(SET_RESOURCES_REMAINING_QUERY, resourcesRemaining, resourceId);
		db.close();
	}
	
}
