package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xavier.basicPathfinderServer.PathfinderCharacter;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.ResultSetMapper;
import com.xavier.basicPathfinderServer.numericals.Adjustment;
import com.xavier.basicPathfinderServer.numericals.AdjustmentStringConverter;

public class AllowedAdjustmentsMapper implements ResultSetMapper<Object> {

	private PathfinderCharacter character;

	public AllowedAdjustmentsMapper(PathfinderCharacter character) {
		this.character = character;
	}
	
	@Override
	public List<Adjustment> map(ResultSet resultSet) {
		List<Adjustment> adjustments = new ArrayList<>();
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("AdjustmentID");
				String name = resultSet.getString("AdjustmentName");
				String effect = resultSet.getString("AdjustmentEffect");
				adjustments.add(AdjustmentStringConverter.convert(character, id, name, effect, true));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adjustments;
	}

}
