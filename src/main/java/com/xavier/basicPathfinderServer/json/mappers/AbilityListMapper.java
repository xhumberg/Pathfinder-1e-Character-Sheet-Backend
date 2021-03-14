package com.xavier.basicPathfinderServer.json.mappers;

import java.util.LinkedList;
import java.util.List;

import com.xavier.basicPathfinderServer.Ability;

public class AbilityListMapper {
	public static List<AbilityJson> map(List<Ability> abilities) {
		List<AbilityJson> json = new LinkedList<>();
		for (Ability ability : abilities) {
			json.add(new AbilityJson(ability.getName(), ability.getFullValue(), ability.getValue()));
		}
		
		return json;
	}
}
