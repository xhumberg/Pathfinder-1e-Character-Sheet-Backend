package com.xavier.basicPathfinderServer.ResultSetMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xavier.basicPathfinderServer.Adjustment;
import com.xavier.basicPathfinderServer.AdjustmentStringConverter;
import com.xavier.basicPathfinderServer.Item;
import com.xavier.basicPathfinderServer.TrackedResource;
import com.xavier.basicPathfinderServer.ResultSetMappers.interimObjects.SpellNameLevelAndClassInterim;

public class ItemMapper implements ResultSetMapper<Object> {

	@Override
	public List<Item> map(ResultSet resultSet) {
		List<Item> items = new ArrayList<>();
		try {
			while (resultSet.next()) {
				String name = resultSet.getString("ItemName");
				int cost = resultSet.getInt("ItemCost");
				String slot = resultSet.getString("ItemSlot");
				String description = resultSet.getString("ItemDescription");
				Adjustment adjustment = AdjustmentStringConverter.convert(name, resultSet.getString("Adjustments"));
				if (adjustment != null) {
					adjustment.toggleAdjustment();
				}
				TrackedResource trackedResource = null;
				String trackedResourceName = resultSet.getString("ResourceName");
				if (trackedResourceName != null) {
					System.out.println(name + " has a tracked resource.");
					String resourceDescription = resultSet.getString("ResourceDescription");
					int remaining = resultSet.getInt("ResourceRemaining");
					int max = resultSet.getInt("ResourceMax");
					trackedResource = new TrackedResource(trackedResourceName, resourceDescription, remaining, max);
				}
				
				Item item = new Item(name, cost, slot, description, adjustment, trackedResource);
				int trueCost = resultSet.getInt("Cost");
				item.setTrueCost(trueCost);
				boolean isEquipped = resultSet.getBoolean("Equipped");
				item.setEquipped(isEquipped);
				
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}

}
