package com.xavier.basicPathfinderServer.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.xavier.basicPathfinderServer.Adjustment;
import com.xavier.basicPathfinderServer.AdjustmentStringConverter;
import com.xavier.basicPathfinderServer.CastingType;
import com.xavier.basicPathfinderServer.CharacterClass;
import com.xavier.basicPathfinderServer.Feat;
import com.xavier.basicPathfinderServer.PathfinderCharacter;
import com.xavier.basicPathfinderServer.RacialTrait;
import com.xavier.basicPathfinderServer.ResultSetMappers.PathfinderCharacterMapper;

class ComprehensiveCharacterJsonTest {
	
	static PathfinderCharacter johnDoe;
	static CharacterJson json;

	@BeforeAll
	static void setup() {
		johnDoe = new PathfinderCharacter("123", "John Doe", "abc.xyz");
		johnDoe.setAbility("Strength", 14);
		johnDoe.setAbility("Dexterity", 12);
		johnDoe.setAbility("Intelligence", 14);
		johnDoe.setAlignment("LN");
		johnDoe.setPlayer("Jane Doe");
		johnDoe.setRace("Human");
		johnDoe.setSize("Medium");
		johnDoe.setGender("Male");
		johnDoe.setAge("Early 20s");
		johnDoe.setWeight("Skinny");
		johnDoe.setHeight("Almost 6 feet");
		giveHumanoidHumanType();
		giveFighterAndBarbarianLevels();
		
		json = new CharacterJson(johnDoe);
	}

	private static void giveHumanoidHumanType() {
		//Racial Trait
		int id = 0;
		String name = "Human: Humanoid (Human)";
		String description = "Humans are humanoids with the human subtype";
		Adjustment effect = AdjustmentStringConverter.convert(-1, name, "[Type: Humanoid (Human)][Sense: Slightly Above Average Smell]", false);
		effect.toggleAdjustment();
		johnDoe.giveRacialTrait(new RacialTrait(id, name, description, effect));
		
		Adjustment featEffect = AdjustmentStringConverter.convert(-1, "Custom Feat", 
				"[Special Defense: Stinky toes][Special Offense: Finger of Stupidity][Speed: 35 feet]", false);
		featEffect.toggleAdjustment();
		johnDoe.giveFeat(new Feat(10, "Custom Feat", "Does custom things!", featEffect));
	}
	
	private static void giveFighterAndBarbarianLevels() {
		CharacterClass fighter = new CharacterClass(0, 3, 0, 0, 0, 0, 0, 0, "Fighter 3", false, CastingType.NONE, -1, null, null);
		CharacterClass barb = new CharacterClass(0, 2, 0, 0, 0, 0, 0, 0, "Barbarian 2", false, CastingType.NONE, -1, null, null);
		johnDoe.addClass(fighter);
		johnDoe.addClass(barb);
	}
	
	@Test
	void basicValuesTest() {
		assertEquals(123, json.getCharacterId());
		assertEquals("John Doe", json.getName());
		assertEquals("abc.xyz", json.getImageUrl());
		assertEquals("LN", json.getAlignment());
		assertEquals("Jane Doe", json.getPlayer());
		assertEquals("Human", json.getRace());
		assertEquals("Medium", json.getSize());
		assertEquals("Male", json.getGender());
		assertEquals("Early 20s", json.getAge());
		assertEquals("Skinny", json.getWeight());
		assertEquals("Almost 6 feet", json.getHeight());
	}
	
	@Test
	void typeTest() {
		assertEquals("Humanoid (Human)", json.getTypeAndSubtype());
	}
	
	@Test
	void totalLevelTest() {
		assertEquals(5, json.getTotalLevel());
	}
	
	@Test
	void classesTest() {
		assertEquals("Fighter 3 / Barbarian 2", json.getClasses());
	}

	@Test
	void sensesTest() {
		assertEquals("Slightly Above Average Smell", json.getSenses());
	}
	
	@Test
	void specialDefensesTest() {
		assertEquals(Collections.singletonList("Stinky toes"), json.getSpecialDefenses());
	}
	
	@Test
	void specialOffensesTest() {
		assertEquals(Collections.singletonList("Finger of Stupidity"), json.getSpecialOffenses());
	}
	
	@Test
	void speedTest() {
		assertEquals("35 feet", json.getLandSpeed());
	}
	
	@Test
	void unspentSkillRanksTest() {
		assertEquals(10, json.getUnspentSkillRanks());
	}
}
