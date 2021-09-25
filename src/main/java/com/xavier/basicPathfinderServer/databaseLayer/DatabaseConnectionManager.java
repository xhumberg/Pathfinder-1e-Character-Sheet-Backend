package com.xavier.basicPathfinderServer.databaseLayer;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class DatabaseConnectionManager {
	private static final long ThirtyMinutesInMilliseconds = 30L*60L*1000L;
	
	private static Connection databaseConnection;
	private static Timer databaseCloseTimer;
	private static TimerTask databaseCloseTimerTask;
	
	public static Connection getDatabaseConnection() {
		if (databaseConnection == null) {
			try {
				databaseConnection = establishConnectionToDatabase();
			} catch (ClassNotFoundException | URISyntaxException | SQLException e) {
				System.out.println("Could not connect to database.");
				e.printStackTrace();
			}
		}
		
		if (databaseCloseTimerTask != null) {
			databaseCloseTimerTask.cancel();
		}
		databaseCloseTimerTask = generateCloseDatabaseConnectionTask();
		
		if (databaseCloseTimer == null) {
			databaseCloseTimer = new Timer("Database Connection Close Timer");
		}
		
		databaseCloseTimer.schedule(databaseCloseTimerTask, ThirtyMinutesInMilliseconds);
		
		return databaseConnection;
		
	}

	private static TimerTask generateCloseDatabaseConnectionTask() {
		return new TimerTask() {
			
			@Override
			public void run() {
				try {
					databaseConnection.close();
					databaseConnection = null;
					System.out.println("Closed database connection.");
				} catch (SQLException e) {
					System.out.println("Could not close connection to database.");
					e.printStackTrace();
				}
			}
		};
	}
	
	private static Connection establishConnectionToDatabase() throws URISyntaxException, ClassNotFoundException, SQLException {
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
	
}
