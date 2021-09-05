package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xavier.basicPathfinderServer.PathfinderCharacter;
import com.xavier.basicPathfinderServer.characterOwned.Feat;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.ResultSetMapper;
import com.xavier.basicPathfinderServer.numericals.Adjustment;
import com.xavier.basicPathfinderServer.numericals.AdjustmentStringConverter;

public class FeatMapper implements ResultSetMapper<Object> {

	private PathfinderCharacter character;

	public FeatMapper(PathfinderCharacter character) {
		this.character = character;
	}
	
	@Override
	public List<Feat> map(ResultSet resultSet) {
		List<Feat> feats = new ArrayList<>();
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("FeatID");
				String name = resultSet.getString("FeatName");
				String description = resultSet.getString("FeatDescription");
				Adjustment effect = AdjustmentStringConverter.convert(character, -1, name, resultSet.getString("FeatEffect"), false);
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
