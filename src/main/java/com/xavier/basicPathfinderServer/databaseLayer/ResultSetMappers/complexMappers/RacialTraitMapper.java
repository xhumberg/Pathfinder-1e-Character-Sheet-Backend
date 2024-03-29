package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xavier.basicPathfinderServer.PathfinderCharacter;
import com.xavier.basicPathfinderServer.characterOwned.RacialTrait;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.ResultSetMapper;
import com.xavier.basicPathfinderServer.numericals.Adjustment;
import com.xavier.basicPathfinderServer.numericals.AdjustmentStringConverter;

public class RacialTraitMapper implements ResultSetMapper<Object> {

	private PathfinderCharacter character;

	public RacialTraitMapper(PathfinderCharacter character) {
		this.character = character;
	}
	
	@Override
	public List<RacialTrait> map(ResultSet resultSet) {
		List<RacialTrait> racialTraits = new ArrayList<>();
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("TraitID");
				String name = resultSet.getString("TraitName");
				String description = resultSet.getString("TraitDescription");
				Adjustment effect = AdjustmentStringConverter.convert(character, -1, name, resultSet.getString("TraitEffect"), false);
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
