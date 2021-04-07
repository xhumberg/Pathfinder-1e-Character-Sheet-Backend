package com.xavier.basicPathfinderServer.ResultSetMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xavier.basicPathfinderServer.TrackedResource;

public class MiscResourceMapper implements ResultSetMapper<Object> {

	@Override
	public List<TrackedResource> map(ResultSet resultSet) {
		List<TrackedResource> resources = new ArrayList<>();
		try {
			while (resultSet.next()) {
				String trackedResourceName = resultSet.getString("ResourceName");
				String resourceDescription = resultSet.getString("ResourceDescription");
				int remaining = resultSet.getInt("ResourceRemaining");
				int max = resultSet.getInt("ResourceMax");
				TrackedResource trackedResource = new TrackedResource(trackedResourceName, resourceDescription, remaining, max);
				resources.add(trackedResource);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resources;
	}

}
