package com.xavier.basicPathfinderServer.numericals;

public class MultipliedStat extends Stat {

	private Stat statToMultiply;
	private double multiplier;
	
	public MultipliedStat(StatName statName, Stat stat, double multiplier) {
		super(statName);
		statToMultiply = stat;
		this.multiplier = multiplier;
	}
	
	@Override
	public int getValue() {
		double result = statToMultiply.getValue()*multiplier;
		return (int)Math.floor(result);
	}

}
