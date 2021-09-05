package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.simpleMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.ResultSetMapper;

public class DoesAResultExistMapper implements ResultSetMapper<Object> {

	@Override
	public Boolean map(ResultSet resultSet) {
		try {
			return resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}

