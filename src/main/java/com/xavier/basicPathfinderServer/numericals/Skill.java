package com.xavier.basicPathfinderServer.numericals;

public class Skill extends Stat {

	private boolean classSkill = false;
	
	public Skill(StatName name) {
		super(name);
	}

	public void setRanks(int ranks) {
		this.setBaseValue(ranks);
	}

	public void setClassSkill() {
		this.classSkill = true;
	}
	
	@Override
	public int getValue() {
		int value = super.getValue();
		if (classSkill && this.baseValue > 0) {
			value += 3;
		}
		return value;
	}

	public int getSkillRanks() {
		return this.baseValue;
	}

}
