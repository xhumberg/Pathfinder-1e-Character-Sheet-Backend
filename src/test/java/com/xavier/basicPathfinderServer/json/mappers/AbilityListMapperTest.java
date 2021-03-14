package com.xavier.basicPathfinderServer.json.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.xavier.basicPathfinderServer.Ability;

class AbilityListMapperTest {

	@Test
	void test() {
		List<Ability> abilities = new LinkedList<>();
		
		Ability strength = new Ability("Strength");
		strength.setBaseValue(14);
		
		abilities.add(strength);
		
		List<AbilityJson> jsons = AbilityListMapper.map(abilities);
		AbilityJson strengthJson = jsons.get(0);
		
		assertEquals(2, strengthJson.getMod());
		assertEquals(14, strengthJson.getValue());
		assertEquals("Strength", strengthJson.getName());
	}

}
