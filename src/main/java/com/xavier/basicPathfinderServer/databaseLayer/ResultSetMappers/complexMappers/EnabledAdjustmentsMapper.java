package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.ResultSetMapper;

public class EnabledAdjustmentsMapper implements ResultSetMapper<Object> {

	@Override
	public List<String> map(ResultSet resultSet) {
		List<String> adjustmentNames = new ArrayList<>();
		try {
			while (resultSet.next()) {
				String name = resultSet.getString("AdjustmentName");
				adjustmentNames.add(name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adjustmentNames;
	}

}
