package com.xavier.basicPathfinderServer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.xavier.basicPathfinderServer.characterOwned.CastingType;
import com.xavier.basicPathfinderServer.characterOwned.Item;
import com.xavier.basicPathfinderServer.characterOwned.Spell;
import com.xavier.basicPathfinderServer.characterOwned.Weapon;
import com.xavier.basicPathfinderServer.characterOwned.Weapon.WeaponType;
import com.xavier.basicPathfinderServer.json.WeaponStats;
import com.xavier.basicPathfinderServer.numericals.Adjustment;
import com.xavier.basicPathfinderServer.numericals.StatName;
import com.xavier.basicPathfinderServer.numericals.TrackedResource;

class PathfinderCharacterTest {

	@Test
	void rageTest() {
		PathfinderCharacter character = new PathfinderCharacter("0", "Grog", null);
		
		buildAndAddAdjustment(character, "Rage", false, "Strength#Morale#4", "Constitution#Morale#4", "Charisma#Penalty#-2");
		
		assertEquals(10, character.getAbilityValue(StatName.STRENGTH));
		assertEquals(10, character.getAbilityValue(StatName.CONSTITUTION));
		assertEquals(10, character.getAbilityValue(StatName.CHARISMA));
		
		character.toggleAdjustment("Rage");
		assertEquals(14, character.getAbilityValue(StatName.STRENGTH));
		assertEquals(14, character.getAbilityValue(StatName.CONSTITUTION));
		assertEquals(8, character.getAbilityValue(StatName.CHARISMA));
	}

	@Test
	void beltAndBullsStrengthTest() {
		PathfinderCharacter character = new PathfinderCharacter("0", "Grog", null);
		
		buildAndAddAdjustment(character, "Belt of Strength +2", true, "Strength#Enhancement#2");
		buildAndAddAdjustment(character, "Bull's Strength", false, "Strength#Enhancement#4");
		assertEquals(12, character.getAbilityValue(StatName.STRENGTH));
		
		character.toggleAdjustment("Bull's Strength");
		assertEquals(14, character.getAbilityValue(StatName.STRENGTH));
		
		character.toggleAdjustment("Bull's Strength");
		assertEquals(12, character.getAbilityValue(StatName.STRENGTH));
	}
	
	@Test
	void giveAManAHammer() {
		PathfinderCharacter bob = new PathfinderCharacter("0", "Bob the Builder", null);
		
		//Just ability
		bob.setAbility(StatName.STRENGTH, 14);
		Weapon warhammer = new Weapon("Warhammer", "d8", "B", "", 0, 0, 20, 3, "HAMMER!", "hammers", "martial", "", 5, "hammers");
		bob.giveWeapon(warhammer, StatName.STRENGTH, StatName.STRENGTH, Weapon.WeaponType.MELEE);
		assertEquals(2, bob.getMeleeAttack("Warhammer"));
		assertEquals(2, bob.getMeleeDamage("Warhammer"));
		
		//Permanent adjustment
		buildAndAddAdjustment(bob, "Fighter 3", true, "All Attacks#BAB#3");
		assertEquals(5, bob.getMeleeAttack("Warhammer"));
		assertEquals(2, bob.getMeleeDamage("Warhammer"));
		
		//Toggle adjustment
		buildAndAddAdjustment(bob, "Heroism", true, "All Attacks#Morale#2");
		assertEquals(7, bob.getMeleeAttack("Warhammer"));
		assertEquals(2, bob.getMeleeDamage("Warhammer"));
		
		bob.toggleAdjustment("Heroism");
		assertEquals(5, bob.getMeleeAttack("Warhammer"));
		assertEquals(2, bob.getMeleeDamage("Warhammer"));
		
		//Melee only adjustment
		buildAndAddAdjustment(bob, "Power Attack", true, "Melee Attacks#Penalty#-1", "Melee Damage#Power Attack#2");
		assertEquals(4, bob.getMeleeAttack("Warhammer"));
		assertEquals(4, bob.getMeleeDamage("Warhammer"));
	}
	
	@Test
	void spellsPerDayTest() {
		PathfinderCharacter prosopa = new PathfinderCharacter("0", "Prosopa", null);
		
		prosopa.giveSpellcasting(0, "Wizard", CastingType.PREPARED, 7, StatName.INTELLIGENCE);
		prosopa.setSpellsPerDay(0, 1, 2);
		assertEquals(2, prosopa.getSpellsPerDay(0, 1));
		
		prosopa.setAbility(StatName.INTELLIGENCE, 12);
		assertEquals(3, prosopa.getSpellsPerDay(0, 1));
		
		prosopa.setAbility(StatName.INTELLIGENCE, 14);
		assertEquals(3, prosopa.getSpellsPerDay(0, 1));
		
		prosopa.setAbility(StatName.INTELLIGENCE, 18);
		assertEquals(3, prosopa.getSpellsPerDay(0, 1));
		
		prosopa.setAbility(StatName.INTELLIGENCE, 20);
		assertEquals(4, prosopa.getSpellsPerDay(0, 1));
		
		prosopa.setAbility(StatName.INTELLIGENCE, 28);
		assertEquals(5, prosopa.getSpellsPerDay(0, 1));
		
		prosopa.setAbility(StatName.INTELLIGENCE, 36);
		assertEquals(6, prosopa.getSpellsPerDay(0, 1));
		
		prosopa.setAbility(StatName.INTELLIGENCE, 12);
		prosopa.setSpellsPerDay(0, 2, 1);
		assertEquals(1, prosopa.getSpellsPerDay(0, 2));
		
		prosopa.setAbility(StatName.INTELLIGENCE, 14);
		assertEquals(2, prosopa.getSpellsPerDay(0, 2));
		
		buildAndAddAdjustment(prosopa, "Super Headband", true, "Intelligence#Enhancement#8");
		
		assertEquals(3, prosopa.getSpellsPerDay(0, 2));
	}
	
	@Test
	void aTaleOfTwoSpells() {
		PathfinderCharacter prosopa = new PathfinderCharacter("0", "Prosopa", null);
		prosopa.setAbility(StatName.INTELLIGENCE, 20);
		
		Spell babble = new Spell(400, 3, "Babble", "Enchantment", "Compulsion, mind-affecting", "1 Standard Action", "V, S", "close", "One creature; see text", "1 round/level", "Will negates", "yes", "This spell causes the target to break into a fit of bizarre...");
		Spell summonMonster3 = new Spell(401, 3, "Summon Monster III", "Conjuration", "Summoning", "1 round", "V, S, F/DF (a tiny bag and a small candle)", "close", "One summoned creature", "1 round/level", "none", "no", "Summons an extraplanar creature (typically...)");
	
		prosopa.giveSpellcasting(0, "Wizard", CastingType.PREPARED, 7, StatName.INTELLIGENCE);
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
		PathfinderCharacter almond = new PathfinderCharacter("0", "Almond", null);
		almond.setAbility(StatName.CONSTITUTION, 16);
		almond.setAbility(StatName.WISDOM, 8);
		almond.setAbility(StatName.DEXTERITY, 12);

		assertEquals(-1, almond.getStatValue(StatName.WILL));
		assertEquals(3, almond.getStatValue(StatName.FORTITUDE));
		assertEquals(1, almond.getStatValue(StatName.REFLEX));

		buildAndAddAdjustment(almond, "Heroism", true, "All Saves#Morale#2");
		
		assertEquals(1, almond.getStatValue(StatName.WILL));
		assertEquals(5, almond.getStatValue(StatName.FORTITUDE));
		assertEquals(3, almond.getStatValue(StatName.REFLEX));
		
		buildAndAddAdjustment(almond, "Lightning Reflexes", true, "Reflex#Lightning Reflexes#2");
		
		assertEquals(1, almond.getStatValue(StatName.WILL));
		assertEquals(5, almond.getStatValue(StatName.FORTITUDE));
		assertEquals(5, almond.getStatValue(StatName.REFLEX));
	}
	
	@Test
	void defendYourself() {
		//NOTE: Abilities must apply to each AC individually, as some things are denied in FF and Touch while others may not be
		PathfinderCharacter almond = new PathfinderCharacter("0", "Almond", null);
		almond.setAbility(StatName.DEXTERITY, 14);
		
		assertEquals(12, almond.getStatValue(StatName.AC));
		assertEquals(10, almond.getStatValue(StatName.FLAT_FOOTED));
		assertEquals(12, almond.getStatValue(StatName.TOUCH));
		
		buildAndAddAdjustment(almond, "Armor", true, "AC#Armor#4", "Flat-Footed#Armor#4");
		
		assertEquals(16, almond.getStatValue(StatName.AC));
		assertEquals(14, almond.getStatValue(StatName.FLAT_FOOTED));
		assertEquals(12, almond.getStatValue(StatName.TOUCH));
		
		buildAndAddAdjustment(almond, "Ring of Protection +2", true, "All AC#Deflection#2");
		
		assertEquals(18, almond.getStatValue(StatName.AC));
		assertEquals(16, almond.getStatValue(StatName.FLAT_FOOTED));
		assertEquals(14, almond.getStatValue(StatName.TOUCH));
	}
	
	@Test
	void skillsTest() {
		PathfinderCharacter prosopa = new PathfinderCharacter("0", "Prosopa", null);
		prosopa.setAbility(StatName.STRENGTH, 7);
		prosopa.setAbility(StatName.DEXTERITY, 18);
		prosopa.setAbility(StatName.CONSTITUTION, 14);
		prosopa.setAbility(StatName.INTELLIGENCE, 23);
		prosopa.setAbility(StatName.WISDOM, 11);
		prosopa.setAbility(StatName.CHARISMA, 5);
		
		assertEquals(4, prosopa.getStatValue(StatName.ACROBATICS));
		assertEquals(6, prosopa.getStatValue(StatName.APPRAISE));
		assertEquals(-3, prosopa.getStatValue(StatName.BLUFF));
		assertEquals(-2, prosopa.getStatValue(StatName.CLIMB));
		assertEquals(6, prosopa.getStatValue(StatName.CRAFT_A));
		assertEquals(6, prosopa.getStatValue(StatName.CRAFT_B));
		assertEquals(-3, prosopa.getStatValue(StatName.DIPLOMACY));
		assertEquals(4, prosopa.getStatValue(StatName.DISABLE_DEVICE));
		assertEquals(-3, prosopa.getStatValue(StatName.DISGUISE));
		assertEquals(4, prosopa.getStatValue(StatName.ESCAPE_ARTIST));
		assertEquals(4, prosopa.getStatValue(StatName.FLY));
		assertEquals(-3, prosopa.getStatValue(StatName.HANDLE_ANIMAL));
		assertEquals(0, prosopa.getStatValue(StatName.HEAL));
		assertEquals(-3, prosopa.getStatValue(StatName.INTIMIDATE));
		assertEquals(6, prosopa.getStatValue(StatName.KNOWLEDGE_ARCANA));
		assertEquals(6, prosopa.getStatValue(StatName.KNOWLEDGE_DUNGEONEERING));
		assertEquals(6, prosopa.getStatValue(StatName.KNOWLEDGE_ENGINEERING));
		assertEquals(6, prosopa.getStatValue(StatName.KNOWLEDGE_GEOGRAPHY));
		assertEquals(6, prosopa.getStatValue(StatName.KNOWLEDGE_HISTORY));
		assertEquals(6, prosopa.getStatValue(StatName.KNOWLEDGE_LOCAL));
		assertEquals(6, prosopa.getStatValue(StatName.KNOWLEDGE_NATURE));
		assertEquals(6, prosopa.getStatValue(StatName.KNOWLEDGE_NOBILITY));
		assertEquals(6, prosopa.getStatValue(StatName.KNOWLEDGE_PLANES));
		assertEquals(6, prosopa.getStatValue(StatName.KNOWLEDGE_RELIGION));
		assertEquals(6, prosopa.getStatValue(StatName.LINGUISTICS));
		assertEquals(0, prosopa.getStatValue(StatName.PERCEPTION));
		assertEquals(-3, prosopa.getStatValue(StatName.PERFORM));
		assertEquals(0, prosopa.getStatValue(StatName.PROFESSION));
		assertEquals(4, prosopa.getStatValue(StatName.RIDE));
		assertEquals(0, prosopa.getStatValue(StatName.SENSE_MOTIVE));
		assertEquals(4, prosopa.getStatValue(StatName.SLEIGHT_OF_HAND));
		assertEquals(6, prosopa.getStatValue(StatName.SPELLCRAFT));
		assertEquals(4, prosopa.getStatValue(StatName.STEALTH));
		assertEquals(0, prosopa.getStatValue(StatName.SURVIVAL));
		assertEquals(-2, prosopa.getStatValue(StatName.SWIM));
		assertEquals(-3, prosopa.getStatValue(StatName.UMD));
		
		prosopa.setSkillRanks(1, StatName.ACROBATICS);
		assertEquals(5, prosopa.getStatValue(StatName.ACROBATICS));
		
		prosopa.setClassSkill(StatName.SPELLCRAFT);
		assertEquals(6, prosopa.getStatValue(StatName.SPELLCRAFT));
		
		prosopa.setSkillRanks(1, StatName.SPELLCRAFT);
		assertEquals(10, prosopa.getStatValue(StatName.SPELLCRAFT));
	}
	
	@Test
	public void healthTest() {
		PathfinderCharacter prosopa = new PathfinderCharacter("0", "Prosopa", null);
		prosopa.setAbility(StatName.CONSTITUTION, 14);
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
		PathfinderCharacter prosopa = new PathfinderCharacter("0", "Prosopa", null);
		prosopa.setAbility(StatName.INTELLIGENCE, 20);
		prosopa.addTotalSkillRanks(2, 2);
		
		assertEquals(14, prosopa.getRemainingSkillRanks());
		assertEquals(14, prosopa.getMaxRanks());
		
		prosopa.setSkillRanks(5, StatName.ACROBATICS);
		
		assertEquals(14, prosopa.getMaxRanks());
		assertEquals(9, prosopa.getRemainingSkillRanks());
		
		prosopa.setSkillRanks(6, StatName.ACROBATICS);
		assertEquals(8, prosopa.getRemainingSkillRanks());
		
		prosopa.setSkillRanks(0, StatName.ACROBATICS);
		assertEquals(14, prosopa.getRemainingSkillRanks());
		
		prosopa.setSkillRanks(2, StatName.ACROBATICS);
		prosopa.setSkillRanks(2, StatName.SPELLCRAFT);
		assertEquals(10, prosopa.getRemainingSkillRanks());
	}
	
	@Test
	public void headbandsAndRods() {
		PathfinderCharacter prosopa = new PathfinderCharacter("0", "Prosopa", null);
		prosopa.setAbility(StatName.INTELLIGENCE, 20);
		
		Adjustment headbandAdjustment = new Adjustment(-1, "Headband of Vast Intelligence +2", true);
		headbandAdjustment.addEffect(StatName.INTELLIGENCE, "Enhancement", 2);
		Item headbandOfInt = new Item("Headband of Vast Intelligence +2", 4000, "Headband", 
				"This intricate gold headband is decorated with several small blue and deep purple gemstones.\r\nThe headband grants the wearer an enhancement bonus to Intelligence of +2. Treat this as a temporary ability bonus for the first 24 hours the headband is worn. A headband of vast intelligence +2 has one skill associated with it. After being worn for 24 hours, the headband grants a number of skill ranks in that skill equal to the wearer’s total Hit Dice. These ranks do not stack with the ranks a creature already possesses. This skill is chosen when the headband is created. If no skill is listed, the headband is assumed to grant skill ranks in a randomly determined Knowledge skill.", 
				headbandAdjustment, null);
		
		prosopa.equip(headbandOfInt);
		
		assertEquals(22, prosopa.getAbilityValue(StatName.INTELLIGENCE));
		assertTrue(prosopa.getItems().contains(headbandOfInt));
		
		Adjustment beltAdjustment = new Adjustment(-1, "Belt of Overpowered Constitution", true);
		beltAdjustment.addEffect(StatName.CONSTITUTION, "Invincible", 9999);
		Item unequippedOverpoweredBelt = new Item("Belt of Overpowered Constitution", 999999999, "Belt",
				"Nobody playtested this item and the munchkins found out how to get it.",
				beltAdjustment, null);
		
		prosopa.giveItem(unequippedOverpoweredBelt);
		assertEquals(10, prosopa.getAbilityValue(StatName.CONSTITUTION));
		assertTrue(prosopa.getItems().contains(unequippedOverpoweredBelt));
		
		TrackedResource persistentRodTracking = new TrackedResource(1, "Lesser Persistent Metamagic Rod", "3 times per day, you may cast a 3rd or lower level spell as if it had the Persistent Spell metamagic feat", 3, 3);
		Item lesserPersistentRod = new Item("Persistent Metamagic Rod, Lesser", 9000, "Rod", "The wielder can cast up to three spells per day as though using the Persistent Spell feat.", null, persistentRodTracking);
		prosopa.equip(lesserPersistentRod);
		
		assertTrue(prosopa.getItems().contains(lesserPersistentRod));
		
		List<Item> rods = prosopa.getAllItemsWithName("Persistent Metamagic Rod, Lesser");
		assertEquals(1, rods.size());
		assertEquals(3, rods.get(0).getTrackedResourceRemaining());
		
		TrackedResource slightlyUsedRod = new TrackedResource(2, "Lesser Persistent Metamagic Rod", "3 times per day, you may cast a 3rd or lower level spell as if it had the Persistent Spell metamagic feat", 2, 3);
		Item usedLesserPersistentRod = new Item("Persistent Metamagic Rod, Lesser", 9000, "Rod", "The wielder can cast up to three spells per day as though using the Persistent Spell feat.", null, slightlyUsedRod);
		prosopa.equip(usedLesserPersistentRod);
		
		for (int i = 0; i < 1000; i++) { //Make sure this never comes in a random order so it's consistent for the player too.
			rods = prosopa.getAllItemsWithName("Persistent Metamagic Rod, Lesser");
			assertEquals(2, rods.size());
			assertEquals(3, rods.get(0).getTrackedResourceRemaining());
			assertEquals(2, rods.get(1).getTrackedResourceRemaining());
		}
	}
	
	@Test
	public void resourceManagement() {
		PathfinderCharacter prosopa = new PathfinderCharacter("5", "Prosopa", null);
		
		//First, item without a resource.
		Item headband = new Item("Headband of Cool", 4000, "Head", "Looks cool and does stuff", null, null);
		TrackedResource wandOfCoolCharges = new TrackedResource(2, "Wand of Cool", "Super cool wand!", 40, 50);
		Item wandOfCool = new Item("Wand of Cool", 99999, "Wand", "Looks cool and does stuff", null, wandOfCoolCharges);
		Item boots = new Item("Boots", 2, "Feet", "Literally just boots", null, null);
		
		prosopa.giveItem(headband);
		prosopa.giveItem(wandOfCool);
		prosopa.giveItem(boots);
		
		assertEquals(40, wandOfCool.getTrackedResourceRemaining());
		
		prosopa.reduceUsesForItem(2);
		prosopa.reduceUsesForItem(2);
		prosopa.reduceUsesForItem(2);
		prosopa.reduceUsesForItem(2);
		prosopa.reduceUsesForItem(2);
		
		assertEquals(35, wandOfCool.getTrackedResourceRemaining());
		
		prosopa.increaseUsesForItem(2);
		prosopa.increaseUsesForItem(2);
		prosopa.increaseUsesForItem(2);
		prosopa.increaseUsesForItem(2);
		prosopa.increaseUsesForItem(2);
		prosopa.increaseUsesForItem(2);
		prosopa.increaseUsesForItem(2);
		prosopa.increaseUsesForItem(2);
		prosopa.increaseUsesForItem(2);
		prosopa.increaseUsesForItem(2);
		
		assertEquals(45, wandOfCool.getTrackedResourceRemaining());
	}
	
	@Test
	public void divineFavorIncreasesDamageTest() {
		PathfinderCharacter manu = new PathfinderCharacter("asdf", "Manu", null);
		manu.setAbility(StatName.STRENGTH, 18);
		
		Weapon sword = new Weapon("Masterwork Bastard Sword", "1d10", "S", "", 1, 4, 19, 2, "Sword", "Melee", "Exotic", "", 6, "swords");
		
		manu.giveWeapon(sword, StatName.STRENGTH, StatName.STRENGTH, WeaponType.MELEE);
		
		WeaponStats swordStats = manu.getWeapons().get(sword);
		
		assertEquals(5, swordStats.attackStat.getValue());
		assertEquals(8, swordStats.damageStat.getValue());
		
		Adjustment divineFavor = new Adjustment(100, "Divine Favor 2", true);
		divineFavor.addEffect(StatName.ALL_ATTACKS, "Luck", 2);
		divineFavor.addEffect(StatName.ALL_DAMAGE, "Luck", 2);
		manu.addAdjustment(divineFavor);
		
		assertEquals(7, swordStats.attackStat.getValue());
		assertEquals(10, swordStats.damageStat.getValue());
	}
	
	private Adjustment buildAndAddAdjustment(PathfinderCharacter character, String adjName, boolean enabled, String... effectStrings) {
		Adjustment adj = new Adjustment(-1, adjName, enabled);
		for (String effectString : effectStrings) {
			String[] strings = effectString.split("#");
			int value = Integer.parseInt(strings[2]);
			adj.addEffect(StatName.decode(strings[0]), strings[1], value);
		}
		character.addAdjustment(adj);
		return adj;
	}

}
