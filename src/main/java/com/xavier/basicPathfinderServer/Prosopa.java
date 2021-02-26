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
		
		Adjustment bullsStrength = new Adjustment("Bull's Strength");
		bullsStrength.addEffect("Strength", "Enhancement", 4);
		
		prosopa.addAdjustment(bullsStrength);
		
		return prosopa;
	}
}
