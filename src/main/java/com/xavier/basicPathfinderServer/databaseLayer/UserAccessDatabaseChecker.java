package com.xavier.basicPathfinderServer.databaseLayer;

import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.simpleMappers.DoesAResultExistMapper;

public class UserAccessDatabaseChecker {
	private final static String CAN_EMAIL_ACCESS_CHARACTER_QUERY = "select * from UserIDToEmail inner join UserAccess on UserIDToEmail.UserID = UserAccess.UserID where (UserEmail = ? OR UserAccess.UserID = -1) AND CharacterID = ?";
	
	public static boolean canEmailAccessCharacter(String email, String id) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		Boolean result = (Boolean) db.executeSelectQuery(new DoesAResultExistMapper(), CAN_EMAIL_ACCESS_CHARACTER_QUERY, email, id);
		return result;
	}
	
}
