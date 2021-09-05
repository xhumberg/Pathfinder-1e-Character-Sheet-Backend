package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xavier.basicPathfinderServer.PathfinderCharacter;
import com.xavier.basicPathfinderServer.characterOwned.ClassFeature;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.ResultSetMapper;
import com.xavier.basicPathfinderServer.numericals.Adjustment;
import com.xavier.basicPathfinderServer.numericals.AdjustmentStringConverter;
import com.xavier.basicPathfinderServer.numericals.TrackedResource;

public class ClassFeatureMapper implements ResultSetMapper<Object> {

	private PathfinderCharacter character;

	public ClassFeatureMapper(PathfinderCharacter character) {
		this.character = character;
	}
	
	@Override
	public List<ClassFeature> map(ResultSet resultSet) {
		List<ClassFeature> features = new ArrayList<>();
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("FeatureID");
				String name = resultSet.getString("FeatureName");
				String description = resultSet.getString("FeatureDescription");
				Adjustment featureEffect = AdjustmentStringConverter.convert(character, -1, name, resultSet.getString("FeatureEffect"), false);
				if (featureEffect != null) {
					featureEffect.toggleAdjustment();
				}
				TrackedResource trackedResource = null;
				String trackedResourceName = resultSet.getString("ResourceName");
				if (trackedResourceName != null) {
					int resourceId = resultSet.getInt("TrackedResourceId");
					String resourceDescription = resultSet.getString("ResourceDescription");
					int remaining = resultSet.getInt("ResourceRemaining");
					int max = resultSet.getInt("ResourceMax");
					trackedResource = new TrackedResource(resourceId, trackedResourceName, resourceDescription, remaining, max);
				}
				
				ClassFeature feature = new ClassFeature(id, name, description, featureEffect, trackedResource);
				
				features.add(feature);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return features;
	}

}
