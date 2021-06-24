package com.xavier.basicPathfinderServer.numericals;

public class Ability extends Stat {

	public Ability(StatName name) {
		super(name, 10);
	}
	
	public int getMod() {
		return (int) Math.floor((getFullValue())/2)-5;
	}
	
	@Override
	public int getValue() {
		return getMod();
	}

	public int getFullValue() {
		return super.getValue();
	}

}
