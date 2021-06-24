package com.xavier.basicPathfinderServer.numericals;

public class SolidStat extends Stat {

	public SolidStat(String name, int baseValue) {
		super(name, baseValue);
	}
	
	@Override
	public void addAdjustment(Adjustment adj) {
		throw new IllegalArgumentException("Solid Stats cannot be adjusted");
	}

}
