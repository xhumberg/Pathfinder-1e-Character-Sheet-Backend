package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xavier.basicPathfinderServer.PathfinderCharacter;
import com.xavier.basicPathfinderServer.characterOwned.Item;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.ResultSetMapper;
import com.xavier.basicPathfinderServer.numericals.Adjustment;
import com.xavier.basicPathfinderServer.numericals.AdjustmentStringConverter;
import com.xavier.basicPathfinderServer.numericals.TrackedResource;

public class ItemMapper implements ResultSetMapper<Object> {

	private PathfinderCharacter character;

	public ItemMapper(PathfinderCharacter character) {
		this.character = character;
	}
	
	@Override
	public List<Item> map(ResultSet resultSet) {
		List<Item> items = new ArrayList<>();
		try {
			while (resultSet.next()) {
				String name = resultSet.getString("ItemName");
				int cost = resultSet.getInt("ItemCost");
				String slot = resultSet.getString("ItemSlot");
				String description = resultSet.getString("ItemDescription");
				Adjustment adjustment = AdjustmentStringConverter.convert(character, -1, name, resultSet.getString("Adjustments"), false);
				if (adjustment != null) {
					adjustment.toggleAdjustment();
				}
				TrackedResource trackedResource = null;
				String trackedResourceName = resultSet.getString("ResourceName");
				if (trackedResourceName != null) {
					int resourceId = resultSet.getInt("TrackedResourceID");
					System.out.println(name + " has a tracked resource.");
					String resourceDescription = resultSet.getString("ResourceDescription");
					int remaining = resultSet.getInt("ResourceRemaining");
					int max = resultSet.getInt("ResourceMax");
					trackedResource = new TrackedResource(resourceId, trackedResourceName, resourceDescription, remaining, max);
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
