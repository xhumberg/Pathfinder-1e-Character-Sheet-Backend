package com.xavier.basicPathfinderServer.numericals;

public class TrackedResource {
	private final int id;
	private final String name;
	private final String description;
	private int remaining;
	private final int max;

	public TrackedResource(int id, String name, String description, int remaining, int max) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.remaining = remaining;
		this.max = max;
		System.out.println("Created tracked resource " + name + " with id " + id);
	}

	public int getRemaining() {
		return remaining;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int reduce() {
		remaining -= 1;
		return remaining;
	}

	public int increase() {
		remaining += 1;
		return remaining;
	}

}
