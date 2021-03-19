package com.xavier.basicPathfinderServer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StatAndAdjustmentTest {

	@Test
	void singleTypeTest() {
		Stat strength = new Stat("Strength", 12);
		Adjustment bullsStrength = new Adjustment("Bull's Strength");
		bullsStrength.addEffect("Strength", "Enhancement", 4);
		strength.addAdjustment(bullsStrength);
		assertEquals(12, strength.getValue());
		
		bullsStrength.toggleAdjustment();
		assertEquals(16, strength.getValue());
		
		bullsStrength.toggleAdjustment();
		assertEquals(12, strength.getValue());
	}
	
	@Test
	void dualTypesStackTest() {
		Stat strength = new Stat("Strength", 12);
		Adjustment bullsStrength = new Adjustment("Bull's Strength");
		bullsStrength.addEffect("Strength", "Enhancement", 4);
		Adjustment alterSelf = new Adjustment("Alter Self");
		alterSelf.addEffect("Strength", "Size", 2);
		strength.addAdjustment(bullsStrength);
		strength.addAdjustment(alterSelf);
		
		assertEquals(12, strength.getValue());
		
		bullsStrength.toggleAdjustment();
		alterSelf.toggleAdjustment();
		assertEquals(18, strength.getValue());
		
		bullsStrength.toggleAdjustment();
		alterSelf.toggleAdjustment();
		assertEquals(12, strength.getValue());
	}
	
	@Test
	void stackingTypesStackTest() {
		Stat AC = new Stat("AC", 12);
		Adjustment haste = new Adjustment("Haste");
		haste.addEffect("AC", "Dodge", 1);
		Adjustment dodgeFeat = new Adjustment("Dodge");
		dodgeFeat.addEffect("AC", "Dodge", 1);
		
		Adjustment cover = new Adjustment("Cover");
		cover.addEffect("AC", "Circumstance", 2);
		Adjustment aided = new Adjustment("Aided");
		aided.addEffect("AC", "Circumstance", 2);
		
		Adjustment shaken = new Adjustment("Shaken");
		shaken.addEffect("AC", "Penalty", -2);
		Adjustment slow = new Adjustment("Slow");
		slow.addEffect("AC", "Penalty", -1);
		
		AC.addAdjustment(haste);
		AC.addAdjustment(dodgeFeat);
		AC.addAdjustment(cover);
		AC.addAdjustment(aided);
		AC.addAdjustment(shaken);
		AC.addAdjustment(slow);
		
		haste.toggleAdjustment();
		dodgeFeat.toggleAdjustment();
		assertEquals(14, AC.getValue());
		
		cover.toggleAdjustment();
		aided.toggleAdjustment();
		assertEquals(18, AC.getValue());
		
		shaken.toggleAdjustment();
		slow.toggleAdjustment();
		assertEquals(15, AC.getValue());
	}
	
	@Test
	public void unstackableTypesDontStackTest() {
		Stat con = new Stat("Constitution", 14);
		Adjustment belt = new Adjustment("Belt of Con +2");
		belt.addEffect("Constitution", "Enhancement", 2);
		Adjustment bearsEndurance = new Adjustment("Bear's Endurance");
		bearsEndurance.addEffect("Constitution", "Enhancement", 4);
		con.addAdjustment(belt);
		con.addAdjustment(bearsEndurance);
		belt.toggleAdjustment();
		bearsEndurance.toggleAdjustment();
		
		assertEquals(18, con.getValue());
	}
	
	@Test
	public void getAllBonusTypesTest() {
		Stat test = new Stat("Test", 0);
		Adjustment typeA = new Adjustment("Type A");
		typeA.addEffect("Test", "A", 1);
		Adjustment typeB = new Adjustment("Type B");
		typeA.addEffect("Test", "B", 1);
		test.addAdjustment(typeA);
		test.addAdjustment(typeB);

		assertEquals(2, test.getAllBonusTypes().size());
		assertTrue(test.getAllBonusTypes().contains("A"));
		assertTrue(test.getAllBonusTypes().contains("B"));
	}
	
	@Test
	public void statInAStatTest() {
		Stat outerStat = new Stat("Outer", 1);
		
		Stat innerStat = new Stat("Inner", 2);
		
		Adjustment insert = new Adjustment("Insert");
		insert.addEffect("Outer", "Enhancement", innerStat);
		outerStat.addAdjustment(insert);
		insert.toggleAdjustment();
		
		assertEquals(3, outerStat.getValue());
	}
	
	@Test
	public void adjustmentInStatAdjustment() {
		Stat testAttack = new Stat("Attack");
		
		Stat BAB = new Stat("BAB");
		Adjustment fighterLevel3 = new Adjustment("Fighter 3");
		fighterLevel3.addEffect("BAB", "Fighter", 3);
		fighterLevel3.toggleAdjustment();
		BAB.addAdjustment(fighterLevel3);
		
		Adjustment BABtoAttack = new Adjustment("BAB to Attack");
		BABtoAttack.addEffect("Attack", "BAB", BAB);
		BABtoAttack.toggleAdjustment();
		testAttack.addAdjustment(BABtoAttack);
		
		assertEquals(3, testAttack.getValue());
		
		Stat str = new Ability("Strength");
		str.setBaseValue(14);
		Adjustment StrToAttack = new Adjustment("Strength to attack");
		StrToAttack.addEffect("Attack", "Strength", str);
		StrToAttack.toggleAdjustment();
		testAttack.addAdjustment(StrToAttack);
		
		assertEquals(5, testAttack.getValue());
	}
}