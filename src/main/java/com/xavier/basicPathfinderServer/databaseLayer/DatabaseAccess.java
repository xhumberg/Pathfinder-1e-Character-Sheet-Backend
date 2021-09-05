package com.xavier.basicPathfinderServer.databaseLayer;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.ResultSetMapper;

public class DatabaseAccess<T> {
	
	Connection databaseConnection; //TODO: Make this static and have it stay open for a certain amount of time after last call (i.e. change close to close later and have it schedule a close using a CountDownTimer???)
	
	public DatabaseAccess() {
		try {
			this.databaseConnection = establishConnectionToDatabase();
		} catch (ClassNotFoundException | URISyntaxException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	private Connection establishConnectionToDatabase() throws URISyntaxException, ClassNotFoundException, SQLException {
		URI dbUri = new URI(System.getenv("DATABASE_URL"));
        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(dbUrl, username, password);
        if (connection.isValid(1000)) {
        	System.out.println("Successfully connected to database");
        } else {
        	System.out.println("Did not connect to database. Check credentials.");
        }
        return connection;
	}
	
	public T executeSelectQuery(ResultSetMapper<T> mapper, String query, Object ...queryParams) {
		try {
			ResultSet resultSet = queryDb(true, databaseConnection, query, queryParams);
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
			queryDb(false, databaseConnection, query, queryParams);
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

	public void close() {
		System.out.println("Connection to database closed");
		try {
			if (databaseConnection != null && !databaseConnection.isClosed()) {
				databaseConnection.close();
			}
		} catch (Exception e) {}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
