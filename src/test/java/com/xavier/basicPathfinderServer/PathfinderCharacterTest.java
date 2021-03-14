package com.xavier.basicPathfinderServer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PathfinderCharacterTest {

	@Test
	void rageTest() {
		PathfinderCharacter character = new PathfinderCharacter("Grog", null);
		
		Adjustment rage = new Adjustment("Rage");
		rage.addEffect("Strength", "Morale", 4);
		rage.addEffect("Constitution", "Morale", 4);
		rage.addEffect("Charisma", "Penalty", -2);
		
		character.addAdjustment(rage);
		
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
		
		Adjustment bullsStrength = new Adjustment("Bull's Strength");
		bullsStrength.addEffect("Strength", "Enhancement", 4);
		character.addAdjustment(bullsStrength);
		
		Adjustment beltOfStrength2 = new Adjustment("Belt of Strength +2");
		beltOfStrength2.addEffect("Strength", "Enhancement", 2);
		beltOfStrength2.toggleAdjustment();
		character.addAdjustment(beltOfStrength2);
		
		assertEquals(12, character.getAbilityValue("Strength"));
		
		character.toggleAdjustment("Bull's Strength");
		assertEquals(14, character.getAbilityValue("Strength"));
		
		character.toggleAdjustment("Bull's Strength");
		assertEquals(12, character.getAbilityValue("Strength"));
	}
	
	@Test
	void giveAManAHammer() {
		PathfinderCharacter bob = new PathfinderCharacter("Bob the Builder", null);
		
		/*===Ability first===*/
		bob.setAbility("Strength", 14);
		
		Weapon warhammer = new Weapon("Warhammer", "d8", "B", "", 0, 0, 20, 3, "HAMMER!", "hammers", "martial", "", 5, "hammers");
		bob.giveWeapon(warhammer, "Strength", "Strength", Weapon.WeaponType.MELEE);
		
		assertEquals(2, bob.getMeleeAttack("Warhammer"));
		
		
		/*===Add an all attack permanent adjustment===*/
		Adjustment bab = new Adjustment("Fighter 3", true);
		bab.addEffect("All Attack", "BAB", 3);
		bob.addAdjustment(bab);
		
		assertEquals(5, bob.getMeleeAttack("Warhammer"));
		
		/*===Add an all attack toggle adjustment===*/
		Adjustment heroism = new Adjustment("Heroism");
		heroism.addEffect("All Attack", "Morale", 2);
		bob.addAdjustment(heroism);
		
		bob.toggleAdjustment("Heroism");
		assertEquals(7, bob.getMeleeAttack("Warhammer"));
		
		bob.toggleAdjustment("Heroism");
		assertEquals(5, bob.getMeleeAttack("Warhammer"));
		
		/*===Lastly, a melee only attack adjustment===*/
		Adjustment powerAttack = new Adjustment("Power Attack", true);
		powerAttack.addEffect("Melee Attack", "Penalty", -1);
		bob.addAdjustment(powerAttack);
		
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
		
		Adjustment spellFocusEnchantment = new Adjustment("Spell Focus Enchantment", true);
		spellFocusEnchantment.addEffect("Enchantment", "Spell Focus", 1);
		prosopa.addAdjustment(spellFocusEnchantment);
		
		assertEquals(19, prosopa.getSpellDC("Wizard", "Babble", 3));
	}

}
