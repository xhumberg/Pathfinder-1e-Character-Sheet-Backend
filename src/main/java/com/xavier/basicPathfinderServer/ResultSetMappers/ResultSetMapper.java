package com.xavier.basicPathfinderServer.ResultSetMappers;

import java.sql.ResultSet;

public interface ResultSetMapper<T> {

	public T map(ResultSet resultSet);

}
