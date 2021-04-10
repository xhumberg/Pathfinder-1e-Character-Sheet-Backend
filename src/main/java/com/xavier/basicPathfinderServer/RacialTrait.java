package com.xavier.basicPathfinderServer;

public class RacialTrait {
	public int id;
	public String name;
	public String description;
	public Adjustment effect;
	//TODO: Specials, like senses
	
	public RacialTrait(int id, String name, String description, Adjustment effect) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.effect = effect;
	}
	
}
