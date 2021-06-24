package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xavier.basicPathfinderServer.characterOwned.Feat;
import com.xavier.basicPathfinderServer.numericals.Adjustment;
import com.xavier.basicPathfinderServer.numericals.AdjustmentStringConverter;

public class FeatMapper implements ResultSetMapper<Object> {

	@Override
	public List<Feat> map(ResultSet resultSet) {
		List<Feat> feats = new ArrayList<>();
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("FeatID");
				String name = resultSet.getString("FeatName");
				String description = resultSet.getString("FeatDescription");
				Adjustment effect = AdjustmentStringConverter.convert(-1, name, resultSet.getString("FeatEffect"), false);
				if (effect != null) {
					effect.toggleAdjustment();
				}
				Feat feat = new Feat(id, name, description, effect);
				feats.add(feat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return feats;
	}

}
