package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers;

import java.sql.ResultSet;

public interface ResultSetMapper<T> {

	public T map(ResultSet resultSet);

}
