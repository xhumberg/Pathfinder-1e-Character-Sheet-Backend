package com.xavier.basicPathfinderServer.characterOwned;

import com.xavier.basicPathfinderServer.numericals.Adjustment;
import com.xavier.basicPathfinderServer.numericals.TrackedResource;

public class Item {

	private int itemId;
	private String name;
	private int cost;
	private int trueCost;
	private String slot;
	private String description;
	private Adjustment adjustment;
	private String adjustmentString;
	private TrackedResource trackedResource;
	private boolean equipped;
	
	public Item(int itemId, String name, int cost, String slot, String description, Adjustment adjustment, String adjustmentString, TrackedResource trackedResource) {
		this.itemId = itemId;
		this.name = name;
		this.cost = cost;
		this.slot = slot;
		this.description = description;
		this.adjustment = adjustment;
		this.adjustmentString = adjustmentString;
		this.trackedResource = trackedResource;
		
		equipped = false;
	}

	public boolean hasAdjustment() {
		return adjustment != null;
	}
	
	public Adjustment getAdjustment() {
		return adjustment;
	}
	
	public void setTrueCost(int trueCost) {
		this.trueCost = trueCost;
	}

	public void setEquipped(boolean b) {
		equipped = b;
	}

	public boolean isEquipped() {
		return equipped;
	}

	public String getName() {
		return name;
	}

	public Integer getTrackedResourceRemaining() {
		if (trackedResource == null) {
			return -1;
		}
		return trackedResource.getRemaining();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adjustment == null) ? 0 : adjustment.hashCode());
		result = prime * result + cost;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (equipped ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((slot == null) ? 0 : slot.hashCode());
		result = prime * result + ((trackedResource == null) ? 0 : trackedResource.hashCode());
		result = prime * result + trueCost;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (adjustment == null) {
			if (other.adjustment != null)
				return false;
		} else if (!adjustment.equals(other.adjustment))
			return false;
		if (cost != other.cost)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (equipped != other.equipped)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (slot == null) {
			if (other.slot != null)
				return false;
		} else if (!slot.equals(other.slot))
			return false;
		if (trackedResource == null) {
			if (other.trackedResource != null)
				return false;
		} else if (!trackedResource.equals(other.trackedResource))
			return false;
		if (trueCost != other.trueCost)
			return false;
		return true;
	}

	public String getDescription() {
		return description;
	}

	public boolean hasTrackedResource() {
		return trackedResource != null;
	}
	
	public int getCost() {
		return cost;
	}

	public int getTrueCost() {
		return trueCost;
	}

	public int getTrackedResourceId() {
		if (hasTrackedResource()) {
			return trackedResource.getId();
		} else {
			return -1;
		}
	}

	public int reduceTrackedResource() {
		if (hasTrackedResource()) {
			return trackedResource.reduce();
		}
		return -1;
	}

	public int increaseTrackedResource() {
		if (hasTrackedResource()) {
			return trackedResource.increase();
		}
		return -1;
	}

	public int getItemId() {
		return itemId;
	}
	
	public String getAdjustmentString() {
		return adjustmentString;
	}
	
	

}
