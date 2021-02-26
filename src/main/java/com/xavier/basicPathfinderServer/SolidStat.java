package com.xavier.basicPathfinderServer;

public class SolidStat extends Stat {

	public SolidStat(String name, int baseValue) {
		super(name, baseValue);
	}
	
	@Override
	public void addAdjustment(Adjustment adj) {
		throw new IllegalArgumentException("Solid Stats cannot be adjusted");
	}

}
