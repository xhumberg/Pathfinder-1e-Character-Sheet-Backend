package com.xavier.basicPathfinderServer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class PathfinderCharacterTest {

	@Test
	void rageTest() {
		PathfinderCharacter character = new PathfinderCharacter(0, "Grog", null);
		
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
		PathfinderCharacter character = new PathfinderCharacter(0, "Grog", null);
		
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
		PathfinderCharacter bob = new PathfinderCharacter(0, "Bob the Builder", null);
		
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
		PathfinderCharacter prosopa = new PathfinderCharacter(0, "Prosopa", null);
		
		prosopa.giveSpellcasting(0, "Wizard", CastingType.PREPARED, 7, "Intelligence");
		prosopa.setSpellsPerDay(0, 1, 2);
		assertEquals(2, prosopa.getSpellsPerDay(0, 1));
		
		prosopa.setAbility("Intelligence", 12);
		assertEquals(3, prosopa.getSpellsPerDay(0, 1));
		
		prosopa.setAbility("Intelligence", 14);
		assertEquals(3, prosopa.getSpellsPerDay(0, 1));
		
		prosopa.setAbility("Intelligence", 18);
		assertEquals(3, prosopa.getSpellsPerDay(0, 1));
		
		prosopa.setAbility("Intelligence", 20);
		assertEquals(4, prosopa.getSpellsPerDay(0, 1));
		
		prosopa.setAbility("Intelligence", 28);
		assertEquals(5, prosopa.getSpellsPerDay(0, 1));
		
		prosopa.setAbility("Intelligence", 36);
		assertEquals(6, prosopa.getSpellsPerDay(0, 1));
		
		prosopa.setAbility("Intelligence", 12);
		prosopa.setSpellsPerDay(0, 2, 1);
		assertEquals(1, prosopa.getSpellsPerDay(0, 2));
		
		prosopa.setAbility("Intelligence", 14);
		assertEquals(2, prosopa.getSpellsPerDay(0, 2));
		
		buildAndAddAdjustment(prosopa, "Super Headband", true, "Intelligence#Enhancement#8");
		
		assertEquals(3, prosopa.getSpellsPerDay(0, 2));
	}
	
	@Test
	void aTaleOfTwoSpells() {
		PathfinderCharacter prosopa = new PathfinderCharacter(0, "Prosopa", null);
		prosopa.setAbility("Intelligence", 20);
		
		Spell babble = new Spell(3, "Babble", "Enchantment", "Compulsion, mind-affecting", "1 Standard Action", "V, S", "close", "One creature; see text", "1 round/level", "Will negates", "yes", "This spell causes the target to break into a fit of bizarre...");
		Spell summonMonster3 = new Spell(3, "Summon Monster III", "Conjuration", "Summoning", "1 round", "V, S, F/DF (a tiny bag and a small candle)", "close", "One summoned creature", "1 round/level", "none", "no", "Summons an extraplanar creature (typically...)");
	
		prosopa.giveSpellcasting(0, "Wizard", CastingType.PREPARED, 7, "Intelligence");
		prosopa.setSpellsPerDay(0, 3, 2);
		prosopa.giveSpellKnown(0, babble);
		prosopa.giveSpellKnown(0, summonMonster3);
		
		prosopa.prepSpell(0, "Babble", 3);
		prosopa.prepSpell(0, "Summon Monster III", 3);
		
		assertEquals(18, prosopa.getSpellDC(0, "Babble", 3));
		assertEquals(-1, prosopa.getSpellDC(0, "Summon Monster III", 3)); 
		
		buildAndAddAdjustment(prosopa, "Spell Focus (Enchantment)", true, "Enchantment#Spell Focus#1");	
		
		assertEquals(19, prosopa.getSpellDC(0, "Babble", 3));
	}
	
	@Test
	void saveYourself() {
		PathfinderCharacter almond = new PathfinderCharacter(0, "Almond", null);
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
		PathfinderCharacter almond = new PathfinderCharacter(0, "Almond", null);
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
		PathfinderCharacter prosopa = new PathfinderCharacter(0, "Prosopa", null);
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
		PathfinderCharacter prosopa = new PathfinderCharacter(0, "Prosopa", null);
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
		PathfinderCharacter prosopa = new PathfinderCharacter(0, "Prosopa", null);
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
	
	@Test
	public void headbandsAndRods() {
		PathfinderCharacter prosopa = new PathfinderCharacter(0, "Prosopa", null);
		prosopa.setAbility("Intelligence", 20);
		
		Adjustment headbandAdjustment = new Adjustment("Headband of Vast Intelligence +2", true);
		headbandAdjustment.addEffect("Intelligence", "Enhancement", 2);
		Item headbandOfInt = new Item("Headband of Vast Intelligence +2", 4000, "Headband", 
				"This intricate gold headband is decorated with several small blue and deep purple gemstones.\r\nThe headband grants the wearer an enhancement bonus to Intelligence of +2. Treat this as a temporary ability bonus for the first 24 hours the headband is worn. A headband of vast intelligence +2 has one skill associated with it. After being worn for 24 hours, the headband grants a number of skill ranks in that skill equal to the wearer’s total Hit Dice. These ranks do not stack with the ranks a creature already possesses. This skill is chosen when the headband is created. If no skill is listed, the headband is assumed to grant skill ranks in a randomly determined Knowledge skill.", 
				headbandAdjustment, null);
		
		prosopa.equip(headbandOfInt);
		
		assertEquals(22, prosopa.getAbilityValue("Intelligence"));
		assertTrue(prosopa.getItems().contains(headbandOfInt));
		
		Adjustment beltAdjustment = new Adjustment("Belt of Overpowered Constitution", true);
		beltAdjustment.addEffect("Constitution", "Invincible", 9999);
		Item unequippedOverpoweredBelt = new Item("Belt of Overpowered Constitution", 999999999, "Belt",
				"Nobody playtested this item and the munchkins found out how to get it.",
				beltAdjustment, null);
		
		prosopa.giveItem(unequippedOverpoweredBelt);
		assertEquals(10, prosopa.getAbilityValue("Constitution"));
		assertTrue(prosopa.getItems().contains(unequippedOverpoweredBelt));
		
		TrackedResource persistentRodTracking = new TrackedResource("Lesser Persistent Metamagic Rod", "3 times per day, you may cast a 3rd or lower level spell as if it had the Persistent Spell metamagic feat", 3, 3);
		Item lesserPersistentRod = new Item("Persistent Metamagic Rod, Lesser", 9000, "Rod", "The wielder can cast up to three spells per day as though using the Persistent Spell feat.", null, persistentRodTracking);
		prosopa.equip(lesserPersistentRod);
		
		assertTrue(prosopa.getItems().contains(lesserPersistentRod));
		
		List<Item> rods = prosopa.getAllItemsWithName("Persistent Metamagic Rod, Lesser");
		assertEquals(1, rods.size());
		assertEquals(3, rods.get(0).getTrackedResourceRemaining());
		
		TrackedResource slightlyUsedRod = new TrackedResource("Lesser Persistent Metamagic Rod", "3 times per day, you may cast a 3rd or lower level spell as if it had the Persistent Spell metamagic feat", 2, 3);
		Item usedLesserPersistentRod = new Item("Persistent Metamagic Rod, Lesser", 9000, "Rod", "The wielder can cast up to three spells per day as though using the Persistent Spell feat.", null, slightlyUsedRod);
		prosopa.equip(usedLesserPersistentRod);
		
		for (int i = 0; i < 1000; i++) { //Make sure this never comes in a random order so it's consistent for the player too.
			rods = prosopa.getAllItemsWithName("Persistent Metamagic Rod, Lesser");
			assertEquals(2, rods.size());
			assertEquals(3, rods.get(0).getTrackedResourceRemaining());
			assertEquals(2, rods.get(1).getTrackedResourceRemaining());
		}
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
