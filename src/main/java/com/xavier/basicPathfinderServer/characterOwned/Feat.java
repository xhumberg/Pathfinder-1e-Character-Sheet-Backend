package com.xavier.basicPathfinderServer.characterOwned;

import com.xavier.basicPathfinderServer.numericals.Adjustment;

public class Feat {
	public int id;
	public String name;
	public String description;
	public Adjustment effect;
	//TODO: Specials, like +1 Will against enchantments for Harrowed
	
	public Feat(int id, String name, String description, Adjustment effect) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.effect = effect;
	}
	
}
