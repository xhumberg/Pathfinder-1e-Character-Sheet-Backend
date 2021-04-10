package com.xavier.basicPathfinderServer.ResultSetMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xavier.basicPathfinderServer.Adjustment;
import com.xavier.basicPathfinderServer.AdjustmentStringConverter;
import com.xavier.basicPathfinderServer.RacialTrait;

public class RacialTraitMapper implements ResultSetMapper<Object> {

	@Override
	public List<RacialTrait> map(ResultSet resultSet) {
		List<RacialTrait> racialTraits = new ArrayList<>();
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("TraitID");
				String name = resultSet.getString("TraitName");
				String description = resultSet.getString("TraitDescription");
				Adjustment effect = AdjustmentStringConverter.convert(name, resultSet.getString("TraitEffect"));
				if (effect != null) {
					effect.toggleAdjustment();
				}
				RacialTrait racialTrait = new RacialTrait(id, name, description, effect);
				racialTraits.add(racialTrait);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return racialTraits;
	}

}
