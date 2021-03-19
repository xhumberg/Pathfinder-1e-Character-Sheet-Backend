package com.xavier.basicPathfinderServer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PathfinderCharacterTest {

	@Test
	void rageTest() {
		PathfinderCharacter character = new PathfinderCharacter("Grog", null);
		
		buildAndAddAdjustment(character, "Rage", false, "Strength#Morale#4", "Constitution#Morale#4", "Charisma#Penalty#-2");
		
		assertEquals(10, character.getAbilityValue("Strength"));
		assertEquals(10, character.getAbilityValue("Constitution"));
		assertEquals(10, character.getAbilityValue("Charisma"));
		
		character.toggleAdjustment("Rage");
		assertEquals(14, character.getAbilityValue("Strength"));
		assertEquals(14, character.getAbilityValue("Constitution"));
		assertEquals(8, character.getAbilityValue("Charisma"));
	}

	@Test
	void beltAndBullsStrengthTest() {
		PathfinderCharacter character = new PathfinderCharacter("Grog", null);
		
		buildAndAddAdjustment(character, "Belt of Strength +2", true, "Strength#Enhancement#2");
		buildAndAddAdjustment(character, "Bull's Strength", false, "Strength#Enhancement#4");
		assertEquals(12, character.getAbilityValue("Strength"));
		
		character.toggleAdjustment("Bull's Strength");
		assertEquals(14, character.getAbilityValue("Strength"));
		
		character.toggleAdjustment("Bull's Strength");
		assertEquals(12, character.getAbilityValue("Strength"));
	}
	
	@Test
	void giveAManAHammer() {
		PathfinderCharacter bob = new PathfinderCharacter("Bob the Builder", null);
		
		//Just ability
		bob.setAbility("Strength", 14);
		Weapon warhammer = new Weapon("Warhammer", "d8", "B", "", 0, 0, 20, 3, "HAMMER!", "hammers", "martial", "", 5, "hammers");
		bob.giveWeapon(warhammer, "Strength", "Strength", Weapon.WeaponType.MELEE);
		assertEquals(2, bob.getMeleeAttack("Warhammer"));
		
		//Permanent adjustment
		buildAndAddAdjustment(bob, "Fighter 3", true, "All Attacks#BAB#3");
		assertEquals(5, bob.getMeleeAttack("Warhammer"));
		
		//Toggle adjustment
		buildAndAddAdjustment(bob, "Heroism", true, "All Attacks#Morale#2");
		assertEquals(7, bob.getMeleeAttack("Warhammer"));
		
		bob.toggleAdjustment("Heroism");
		assertEquals(5, bob.getMeleeAttack("Warhammer"));
		
		//Melee only adjustment
		buildAndAddAdjustment(bob, "Power Attack", true, "Melee Attacks#Penalty#-1");
		assertEquals(4, bob.getMeleeAttack("Warhammer"));
	}
	
	@Test
	void spellsPerDayTest() {
		PathfinderCharacter prosopa = new PathfinderCharacter("Prosopa", null);
		
		prosopa.giveSpellcasting("Wizard", CastingType.PREPARED, "Intelligence");
		prosopa.setSpellsPerDay("Wizard", 1, 2);
		assertEquals(2, prosopa.getSpellsPerDay("Wizard", 1));
		
		prosopa.setAbility("Intelligence", 12);
		assertEquals(3, prosopa.getSpellsPerDay("Wizard", 1));
		
		prosopa.setAbility("Intelligence", 14);
		assertEquals(3, prosopa.getSpellsPerDay("Wizard", 1));
		
		prosopa.setAbility("Intelligence", 18);
		assertEquals(3, prosopa.getSpellsPerDay("Wizard", 1));
		
		prosopa.setAbility("Intelligence", 20);
		assertEquals(4, prosopa.getSpellsPerDay("Wizard", 1));
		
		prosopa.setAbility("Intelligence", 28);
		assertEquals(5, prosopa.getSpellsPerDay("Wizard", 1));
		
		prosopa.setAbility("Intelligence", 36);
		assertEquals(6, prosopa.getSpellsPerDay("Wizard", 1));
		
		prosopa.setAbility("Intelligence", 12);
		prosopa.setSpellsPerDay("Wizard", 2, 1);
		assertEquals(1, prosopa.getSpellsPerDay("Wizard", 2));
		
		prosopa.setAbility("Intelligence", 14);
		assertEquals(2, prosopa.getSpellsPerDay("Wizard", 2));
		
		buildAndAddAdjustment(prosopa, "Super Headband", true, "Intelligence#Enhancement#8");
		
		assertEquals(3, prosopa.getSpellsPerDay("Wizard", 2));
	}
	
	@Test
	void aTaleOfTwoSpells() {
		PathfinderCharacter prosopa = new PathfinderCharacter("Prosopa", null);
		prosopa.setAbility("Intelligence", 20);
		
		Spell babble = new Spell(3, "Babble", "Enchantment", "Compulsion, mind-affecting", "1 Standard Action", "V, S", "close", "One creature; see text", "1 round/level", "Will negates", "yes", "This spell causes the target to break into a fit of bizarre...");
		Spell summonMonster3 = new Spell(3, "Summon Monster III", "Conjuration", "Summoning", "1 round", "V, S, F/DF (a tiny bag and a small candle)", "close", "One summoned creature", "1 round/level", "none", "no", "Summons an extraplanar creature (typically...)");
	
		prosopa.giveSpellcasting("Wizard", CastingType.PREPARED, "Intelligence");
		prosopa.setSpellsPerDay("Wizard", 3, 2);
		prosopa.giveSpellKnown("Wizard", babble);
		prosopa.giveSpellKnown("Wizard", summonMonster3);
		
		prosopa.prepSpell("Wizard", "Babble", 3);
		prosopa.prepSpell("Wizard", "Summon Monster III", 3);
		
		assertEquals(18, prosopa.getSpellDC("Wizard", "Babble", 3));
		assertEquals(-1, prosopa.getSpellDC("Wizard", "Summon Monster III", 3)); 
		
		buildAndAddAdjustment(prosopa, "Spell Focus (Enchantment)", true, "Enchantment#Spell Focus#1");	
		
		assertEquals(19, prosopa.getSpellDC("Wizard", "Babble", 3));
	}
	
	@Test
	void saveYourself() {
		PathfinderCharacter almond = new PathfinderCharacter("Almond", null);
		almond.setAbility("Constitution", 16);
		almond.setAbility("Wisdom", 8);
		almond.setAbility("Dexterity", 12);

		assertEquals(-1, almond.getStatValue("Will"));
		assertEquals(3, almond.getStatValue("Fortitude"));
		assertEquals(1, almond.getStatValue("Reflex"));

		buildAndAddAdjustment(almond, "Heroism", true, "All Saves#Morale#2");
		
		assertEquals(1, almond.getStatValue("Will"));
		assertEquals(5, almond.getStatValue("Fortitude"));
		assertEquals(3, almond.getStatValue("Reflex"));
		
		buildAndAddAdjustment(almond, "Lightning Reflexes", true, "Reflex#Lightning Reflexes#2");
		
		assertEquals(1, almond.getStatValue("Will"));
		assertEquals(5, almond.getStatValue("Fortitude"));
		assertEquals(5, almond.getStatValue("Reflex"));
	}
	
	@Test
	void defendYourself() {
		//NOTE: Abilities must apply to each AC individually, as some things are denied in FF and Touch while others may not be
		PathfinderCharacter almond = new PathfinderCharacter("Almond", null);
		almond.setAbility("Dexterity", 14);
		
		assertEquals(12, almond.getStatValue("AC"));
		assertEquals(10, almond.getStatValue("Flat-Footed"));
		assertEquals(12, almond.getStatValue("Touch"));
		
		buildAndAddAdjustment(almond, "Armor", true, "AC#Armor#4", "Flat-Footed#Armor#4");
		
		assertEquals(16, almond.getStatValue("AC"));
		assertEquals(14, almond.getStatValue("Flat-Footed"));
		assertEquals(12, almond.getStatValue("Touch"));
		
		buildAndAddAdjustment(almond, "Ring of Protection +2", true, "All AC#Deflection#2");
		
		assertEquals(18, almond.getStatValue("AC"));
		assertEquals(16, almond.getStatValue("Flat-Footed"));
		assertEquals(14, almond.getStatValue("Touch"));
	}
	
	@Test
	void skillsTest() {
		PathfinderCharacter prosopa = new PathfinderCharacter("Prosopa", null);
		prosopa.setAbility("Strength", 7);
		prosopa.setAbility("Dexterity", 18);
		prosopa.setAbility("Constitution", 14);
		prosopa.setAbility("Intelligence", 23);
		prosopa.setAbility("Wisdom", 11);
		prosopa.setAbility("Charisma", 5);
		
		assertEquals(4, prosopa.getStatValue("Acrobatics"));
		assertEquals(6, prosopa.getStatValue("Appraise"));
		assertEquals(-3, prosopa.getStatValue("Bluff"));
		assertEquals(-2, prosopa.getStatValue("Climb"));
		assertEquals(6, prosopa.getStatValue("Craft A"));
		assertEquals(6, prosopa.getStatValue("Craft B"));
		assertEquals(-3, prosopa.getStatValue("Diplomacy"));
		assertEquals(4, prosopa.getStatValue("Disable Device"));
		assertEquals(-3, prosopa.getStatValue("Disguise"));
		assertEquals(4, prosopa.getStatValue("Escape Artist"));
		assertEquals(4, prosopa.getStatValue("Fly"));
		assertEquals(-3, prosopa.getStatValue("Handle Animal"));
		assertEquals(0, prosopa.getStatValue("Heal"));
		assertEquals(-3, prosopa.getStatValue("Intimidate"));
		assertEquals(6, prosopa.getStatValue("Knowledge (Arcana)"));
		assertEquals(6, prosopa.getStatValue("Knowledge (Dungeoneering)"));
		assertEquals(6, prosopa.getStatValue("Knowledge (Engineering)"));
		assertEquals(6, prosopa.getStatValue("Knowledge (Geography)"));
		assertEquals(6, prosopa.getStatValue("Knowledge (History)"));
		assertEquals(6, prosopa.getStatValue("Knowledge (Local)"));
		assertEquals(6, prosopa.getStatValue("Knowledge (Nature)"));
		assertEquals(6, prosopa.getStatValue("Knowledge (Nobility)"));
		assertEquals(6, prosopa.getStatValue("Knowledge (Planes)"));
		assertEquals(6, prosopa.getStatValue("Knowledge (Religion)"));
		assertEquals(6, prosopa.getStatValue("Linguistics"));
		assertEquals(0, prosopa.getStatValue("Perception"));
		assertEquals(-3, prosopa.getStatValue("Perform"));
		assertEquals(0, prosopa.getStatValue("Profession"));
		assertEquals(4, prosopa.getStatValue("Ride"));
		assertEquals(0, prosopa.getStatValue("Sense Motive"));
		assertEquals(4, prosopa.getStatValue("Sleight of Hand"));
		assertEquals(6, prosopa.getStatValue("Spellcraft"));
		assertEquals(4, prosopa.getStatValue("Stealth"));
		assertEquals(0, prosopa.getStatValue("Survival"));
		assertEquals(-2, prosopa.getStatValue("Swim"));
		assertEquals(-3, prosopa.getStatValue("UMD"));
		
		prosopa.setSkillRanks(1, "Acrobatics");
		assertEquals(5, prosopa.getStatValue("Acrobatics"));
		
		prosopa.setClassSkill("Spellcraft");
		assertEquals(6, prosopa.getStatValue("Spellcraft"));
		
		prosopa.setSkillRanks(1, "Spellcraft");
		assertEquals(10, prosopa.getStatValue("Spellcraft"));
	}
	
	@Test
	public void healthTest() {
		PathfinderCharacter prosopa = new PathfinderCharacter("Prosopa", null);
		prosopa.setAbility("Constitution", 14);
		prosopa.addHitDice(1, 6);
		assertEquals(8, prosopa.getMaxHealth());
		
		prosopa.addHitDice(1, 6);
		assertEquals(14, prosopa.getMaxHealth());
		
		prosopa.addHitDice(1, 12);
		assertEquals(23, prosopa.getMaxHealth());
		
		prosopa.setFavoredClassBonusHP(3);
		assertEquals(26, prosopa.getMaxHealth());
		
		//Take damage
		assertEquals(26, prosopa.getCurrentHealth());
		
		prosopa.takeDamage(10);
		assertEquals(16, prosopa.getCurrentHealth());
		
		prosopa.heal(7);
		assertEquals(23, prosopa.getCurrentHealth());
		
		prosopa.heal(999);
		assertEquals(26, prosopa.getCurrentHealth());
		
		prosopa.takeDamage(999);
		assertEquals(-973, prosopa.getCurrentHealth());
		
		prosopa.fullHeal();
		assertEquals(26, prosopa.getCurrentHealth());
	}
	
	@Test
	public void skillRanks() {
		PathfinderCharacter prosopa = new PathfinderCharacter("Prosopa", null);
		prosopa.setAbility("Intelligence", 20);
		prosopa.addTotalSkillRanks(2, 2);
		
		assertEquals(14, prosopa.getRemainingSkillRanks());
		assertEquals(14, prosopa.getMaxRanks());
		
		prosopa.setSkillRanks(5, "Acrobatics");
		
		assertEquals(14, prosopa.getMaxRanks());
		assertEquals(9, prosopa.getRemainingSkillRanks());
		
		prosopa.setSkillRanks(6, "Acrobatics");
		assertEquals(8, prosopa.getRemainingSkillRanks());
		
		prosopa.setSkillRanks(0, "Acrobatics");
		assertEquals(14, prosopa.getRemainingSkillRanks());
		
		prosopa.setSkillRanks(2, "Acrobatics");
		prosopa.setSkillRanks(2, "Spellcraft");
		assertEquals(10, prosopa.getRemainingSkillRanks());
	}
	
	private Adjustment buildAndAddAdjustment(PathfinderCharacter character, String adjName, boolean enabled, String... effectStrings) {
		Adjustment adj = new Adjustment(adjName, enabled);
		for (String effectString : effectStrings) {
			String[] strings = effectString.split("#");
			int value = Integer.parseInt(strings[2]);
			adj.addEffect(strings[0], strings[1], value);
		}
		character.addAdjustment(adj);
		return adj;
	}

}
