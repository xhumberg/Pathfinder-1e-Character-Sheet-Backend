package com.xavier.basicPathfinderServer;

public class ClassFeature {
	public int id;
	public String name;
	public String description;
	public Adjustment effect;
	public TrackedResource trackedResource;
	
	public ClassFeature(int id, String name, String description, Adjustment effect, TrackedResource trackedResource) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.effect = effect;
		this.trackedResource = trackedResource;
	}

	public int getTrackedResourceId() {
		if (trackedResource != null) {
			return trackedResource.getId();
		} else {
			return -1;
		}
	}

	public int reduceTrackedResource() {
		if (trackedResource != null) {
			return trackedResource.reduce();
		}
		return -1;
	}
	
	public int increaseTrackedResource() {
		if (trackedResource != null) {
			return trackedResource.increase();
		}
		return -1;
	}
}
