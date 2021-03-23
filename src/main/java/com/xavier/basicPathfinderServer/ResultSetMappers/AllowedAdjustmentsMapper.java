package com.xavier.basicPathfinderServer.ResultSetMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xavier.basicPathfinderServer.Adjustment;
import com.xavier.basicPathfinderServer.AdjustmentStringConverter;

public class AllowedAdjustmentsMapper implements ResultSetMapper<Object> {

	@Override
	public List<Adjustment> map(ResultSet resultSet) {
		List<Adjustment> adjustments = new ArrayList<>();
		try {
			while (resultSet.next()) {
				String name = resultSet.getString("AdjustmentName");
				String effect = resultSet.getString("AdjustmentEffect");
				adjustments.add(AdjustmentStringConverter.convert(name, effect));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adjustments;
	}

}
