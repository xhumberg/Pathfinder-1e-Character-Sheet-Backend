package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xavier.basicPathfinderServer.numericals.Adjustment;
import com.xavier.basicPathfinderServer.numericals.AdjustmentStringConverter;

public class AllowedAdjustmentsMapper implements ResultSetMapper<Object> {

	@Override
	public List<Adjustment> map(ResultSet resultSet) {
		List<Adjustment> adjustments = new ArrayList<>();
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("AdjustmentID");
				String name = resultSet.getString("AdjustmentName");
				String effect = resultSet.getString("AdjustmentEffect");
				adjustments.add(AdjustmentStringConverter.convert(id, name, effect, true));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adjustments;
	}

}
