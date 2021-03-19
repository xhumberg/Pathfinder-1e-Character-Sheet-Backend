package com.xavier.basicPathfinderServer;

public class Prosopa {
	public static PathfinderCharacter get() {
		PathfinderCharacter prosopa = new PathfinderCharacter("Prosopa", "https://media.discordapp.net/attachments/526680690218106891/731649744937418792/107571564_607483240193629_5533577863028070138_n.png");
		prosopa.setAbility("Strength", 7);
		prosopa.setAbility("Dexterity", 16);
		prosopa.setAbility("Constitution", 14);
		prosopa.setAbility("Intelligence", 21);
		prosopa.setAbility("Wisdom", 11);
		prosopa.setAbility("Charisma", 5);
		
		addHeroism(prosopa);
		prosopa.addHitDice(7, 6);
		prosopa.setFavoredClassBonusHP(5);
		prosopa.addTotalSkillRanks(7, 2);
		
		addHeadbandOfInt(prosopa);
		
		return prosopa;
	}

	private static void addHeroism(PathfinderCharacter prosopa) {
		Adjustment heroism = new Adjustment("Heroism");
		heroism.addEffect("All Saves", "Morale", 2);
		heroism.addEffect("All Skills", "Morale", 2);
		heroism.addEffect("All Attacks", "Morale", 2);
		prosopa.addAdjustment(heroism);
	}
	
	private static void addHeadbandOfInt(PathfinderCharacter prosopa) {
		Adjustment headbandOfInt = new Adjustment("Headband of Vast Ingelligence +2", true);
		headbandOfInt.addEffect("Intelligence", "Enhancement", 2);
		prosopa.addAdjustment(headbandOfInt);
	}
}
