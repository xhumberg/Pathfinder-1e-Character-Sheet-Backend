package com.xavier.basicPathfinderServer;

public class Prosopa {
	public static PathfinderCharacter get() {
		PathfinderCharacter prosopa = new PathfinderCharacter("Prosopa", "https://media.discordapp.net/attachments/526680690218106891/731649744937418792/107571564_607483240193629_5533577863028070138_n.png");
		prosopa.setAlignment("CG");
		prosopa.setPlayer("Xavier");
//		Race tiefling = new Race("Tiefling"); //Make sure all of this information passes through to where it's needed
//		tiefling.setType("Outsider");
//		tiefling.setSubtype("Native");
//		tiefling.setSize("Medium");
//		tiefling.setSpeed("30 feet");
//		tiefling.addLanguage("Common");
//		tiefling.addLanguage("Abyssal");
//		tiefling.addResistance("Cold 5");
//		tiefling.addResistance("Electricity 5");
//		tiefling.addResistance("Fire 5");
//		Adjustment skilled = new Adjustment("Skilled", true);
//		skilled.addEffect("Bluff", "Racial", 2);
//		skilled.addEffect("Stealth", "Racial", 2);
//		tiefling.addAdjustment(skilled);
//		Spell darkness = new Spell(2, "Darkness", "Evocation", "Darkness", "1 standard action", "V, M/DF (bat fur and a piece of coal)", "touch", "object touched", "1 min./level (D)", "none", "no", 
//				"This spell causes an object to radiate darkness out to a 20-foot radius. This darkness causes the illumination level in the area to drop one step, from bright light to normal light, from normal light to dim light, or from dim light to darkness. This spell has no effect in an area that is already dark. Creatures with light vulnerability or sensitivity take no penalties in normal light. All creatures gain concealment (20% miss chance) in dim light. All creatures gain total concealment (50% miss chance) in darkness. Creatures with darkvision can see in an area of dim light or darkness without penalty. Nonmagical sources of light, such as torches and lanterns, do not increase the light level in an area of darkness. Magical light sources only increase the light level in an area if they are of a higher spell level than darkness.\r\n" + 
//				"\r\n" + 
//				"If darkness is cast on a small object that is then placed inside or under a lightproof covering, the spell’s effect is blocked until the covering is removed.\r\n" + 
//				"\r\n" + 
//				"This spell does not stack with itself. Darkness can be used to counter or dispel any light spell of equal or lower spell level.");
//		tiefling.addSpellLikeAbility(darkness, "Charisma", "1/day");
//		tiefling.addSpecial("Prehensile Tail", "Many tieflings have tails, but some have long, flexible tails that can be used to carry items. While they cannot wield weapons with their tails, they can use them to retrieve small, stowed objects carried on their persons as a swift action.");
//		prosopa.setRace(tiefling);
		
//		prosopa.setGender("Female");
//		prosopa.setAge("29");
//		prosopa.setHeight("5' 10\"");
//		prosopa.setWeight("120 lbs");
		
		prosopa.setAbility("Strength", 7);
		prosopa.setAbility("Dexterity", 16);
		prosopa.setAbility("Constitution", 14);
		prosopa.setAbility("Intelligence", 21);
		prosopa.setAbility("Wisdom", 11);
		prosopa.setAbility("Charisma", 5);
		
		prosopa.setSkillRanks(7, "Diplomacy");
		prosopa.setSkillRanks(7, "Knowledge (Arcana)");
		prosopa.setSkillRanks(2, "Knowledge (Dungeoneering)");
		prosopa.setSkillRanks(2, "Knowledge (Engineering)");
		prosopa.setSkillRanks(2, "Knowledge (Geography)");
		prosopa.setSkillRanks(2, "Knowledge (History)");
		prosopa.setSkillRanks(7, "Knowledge (Local)");
		prosopa.setSkillRanks(2, "Knowledge (Nature)");
		prosopa.setSkillRanks(2, "Knowledge (Nobility)");
		prosopa.setSkillRanks(4, "Knowledge (Planes)");
		prosopa.setSkillRanks(7, "Knowledge (Religion)");
		prosopa.setSkillRanks(5, "Perform");
		prosopa.setSkillRanks(7, "Spellcraft");
		
//		Feat spellFocusConjuration = new Feat("Spell Focus (Conjuration)", "Choose a school of magic. Any spells you cast of that school are more difficult to resist.\r\n" + 
//				"\r\n" + 
//				"Benefit: Add +1 to the Difficulty Class for all saving throws against spells from the school of magic you select.");
//		Adjustment spellFocusConjurationAdj = new Adjustment("Spell Focus (Conjuration)");
//		spellFocusConjurationAdj.addEffect("Conjuration", "Spell Focus", 1);
//		spellFocusConjuration.addAdjustment(spellFocusConjurationAdj);
//		prosopa.addFeat(spellFocusConjuration);
		
//		Feat harrowed = new Feat("Harrowed", "You get a +1 bonus on all Will saves made to resist enchantment effects. Once per day, you may draw a card from a Harrow deck you own. At any one time for the rest of that day, you may apply a +2 bonus on any d20 roll modified by the card’s suit. For example, if you drew a card from the suit of Dexterity, you could apply this +2 bonus on an Initiative check, a Reflex save, a Dexterity-based skill check, or a ranged attack roll. You may assign this +2 bonus after you make the roll, but you must do so before you know whether the roll was a success or not.");
//		harrowed.addTrackedResource(new SolidStat("Harrowed", 1), "Harrowed", "Once per day, you may draw a card from a Harrow deck you own. At any one time for the rest of that day, you may apply a +2 bonus on any d20 roll modified by the card’s suit. For example, if you drew a card from the suit of Dexterity, you could apply this +2 bonus on an Initiative check, a Reflex save, a Dexterity-based skill check, or a ranged attack roll. You may assign this +2 bonus after you make the roll, but you must do so before you know whether the roll was a success or not.");
//		prosopa.addFeat(harrowed);
		
//		Feat spellFocusEvocation = new Feat("Spell Focus (Evocation)", "Choose a school of magic. Any spells you cast of that school are more difficult to resist.\r\n" + 
//		"\r\n" + 
//		"Benefit: Add +1 to the Difficulty Class for all saving throws against spells from the school of magic you select.");
//		Adjustment spellFocusEvocationAdj = new Adjustment("Spell Focus (Evocation)");
//		spellFocusEvocationAdj.addEffect("Evocation", "Spell Focus", 1);
//		spellFocusEvocation.addAdjustment(spellFocusEvocationAdj);
//		prosopa.addFeat(spellFocusEvocation);
		
//		Feat extraArcaneReservoir = new Feat("Extra Arcane Reservoir", "You gain three more points in your arcane reservoir, and the maximum number of points in your arcane reservoir increases by that amount.");
//		//This effect is applied in the Arcane Reservoir class feature instead.
//		prosopa.addFEat(extraArcaneReservoir);
		
//		Trait studentOfPhilosophy = new Trait("Student of Philosophy", "You can use your Intelligence modifier in place of your Charisma modifier on Diplomacy checks to persuade others and on Bluff checks to convince others that a lie is true. (This trait does not affect Diplomacy checks to gather information or Bluff checks to feint in combat).");
//		studentOfPhilosophy.addSpecialSkill("Dipomacy: to persuade others", "Intelligence");
//		studentOfPhilosophy.addSpecialSkill("Bluff: to lie", "Intelligence");
		
//		Trait reactionary = new Trait("Reactionary", "You gain a +2 trait bonus on initiative checks");
//		Adjustment reactionaryAdj = new Adjustment("Reactionary");
//		reactionaryAdj.addEffect("Initiative", "Trait", 2);
		
//		Weapon heavyCrossbow = new Weapon("Heavy Crossbow", "1d10", "P", "", 0, 0, 19, 2, "Heavy Crossbow Description", "Ranged", "Simple", "Range 120 ft.", 8, "Crossbows");
//		prosopa.giveWeapon(heavyCrossbow, "Dexterity", "none", Weapon.WeaponType.RANGED);
		
//		Class wizardHarrower = new Class("Exploiter Wizard 5/Harrower 2");
//		wizardHarrower.setLevel(7);
//		wizardHarrower.setBAB(3);
//		wizardHarrower.setFort(2);
//		wizardHarrower.setRef(2);
//		wizardHarrower.setWill(5);
//		wizardHarrower.giveSpellcasting(CastingType.PREPARED, "Intelligence");
//		wizardHarrower.setSpellsPerDay(0, 4);
//		wizardHarrower.setSpellsPerDay(1, 4);
//		wizardHarrower.setSpellsPerDay(2, 3);
//		wizardHarrower.setSpellsPerDay(3, 2);
//		wizardHarrower.setSpellsPerDay(4, 1);
//		wizardHarrower.giveAbility("Arcane Reservoir", "The exploiter wizard gains the arcanist's arcane reservoir class feature. The exploiter wizard can use his wizard level as his arcanist level for determining how many arcanist reservoir points he gains at each level.");
//		wizardHarrower.giveTrackedResource(new SolidStat("Arcane Reservoir", 8), "Arcane Reservoir", "Using an exploit may use this");
		return prosopa;
	}
}
