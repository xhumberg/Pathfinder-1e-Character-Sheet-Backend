package com.xavier.basicPathfinderServer.ResultSetMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xavier.basicPathfinderServer.Adjustment;
import com.xavier.basicPathfinderServer.AdjustmentStringConverter;
import com.xavier.basicPathfinderServer.ClassFeature;
import com.xavier.basicPathfinderServer.TrackedResource;

public class ClassFeatureMapper implements ResultSetMapper<Object> {

	@Override
	public List<ClassFeature> map(ResultSet resultSet) {
		List<ClassFeature> features = new ArrayList<>();
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("FeatureID");
				String name = resultSet.getString("FeatureName");
				String description = resultSet.getString("FeatureDescription");
				Adjustment featureEffect = AdjustmentStringConverter.convert(-1, name, resultSet.getString("FeatureEffect"));
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
