package com.xavier.basicPathfinderServer;

public class TrackedResource {
	private String name;
	private String description;
	private int remaining;
	private int max;

	public TrackedResource(String name, String description, int remaining, int max) {
		this.name = name;
		this.description = description;
		this.remaining = remaining;
		this.max = max;
		
	}

	public int getRemaining() {
		return remaining;
	}

}
