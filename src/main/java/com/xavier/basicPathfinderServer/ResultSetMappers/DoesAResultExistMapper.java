package com.xavier.basicPathfinderServer.ResultSetMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

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

