package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.simpleMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.ResultSetMapper;

public class SingleIntegerMapper implements ResultSetMapper<Integer> {

	@Override
	public Integer map(ResultSet resultSet) {
		try {
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
