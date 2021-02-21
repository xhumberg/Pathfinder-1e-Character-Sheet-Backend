package com.xavier.basicPathfinderServer;

public class Ability extends Stat {

	public Ability(String name) {
		super(name, 10);
	}
	
	public int getMod() {
		return (int) Math.floor((getValue())/2)-5;
	}

}
