package com.xavier.basicPathfinderServer.databaseLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.ResultSetMapper;

public class DatabaseAccess<T> {
	
	public T executeSelectQuery(ResultSetMapper<T> mapper, String query, Object ...queryParams) {
		try {
			ResultSet resultSet = queryDb(true, DatabaseConnectionManager.getDatabaseConnection(), query, queryParams);
			T queryResult = mapper.map(resultSet);
			resultSet.close();
			
			return queryResult;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void executeModifyQuery(String query, Object ...queryParams) {
		try {
			queryDb(false, DatabaseConnectionManager.getDatabaseConnection(), query, queryParams);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private ResultSet queryDb(boolean expectResult, Connection conn, String query, Object ...queryParams) throws SQLException {
		conn.setAutoCommit(false);
		PreparedStatement statement = conn.prepareStatement(query);
		for (int i = 0; i < queryParams.length; i++) {
			if (queryParams[i] instanceof String) {
				statement.setString(i+1, (String)queryParams[i]);
			} else if (queryParams[i] instanceof Integer) {
				statement.setInt(i+1, (Integer)queryParams[i]);
			} else if (queryParams[i] instanceof Boolean) {
				statement.setBoolean(i+1, (Boolean)queryParams[i]);
			} else if (queryParams[i] == null) {
				statement.setString(i+1, null);
			} else {
				System.out.println("ERROR: statement tried to set unsupported type " + queryParams[i].getClass());
			}
		}
		System.out.println("Executing statement: " + statement.toString());
		if (expectResult) {
			ResultSet result = statement.executeQuery();
			return result;
		} else {
			statement.execute();
			conn.commit();
			return null;
		}
	}
}
