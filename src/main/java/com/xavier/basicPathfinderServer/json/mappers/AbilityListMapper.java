package com.xavier.basicPathfinderServer.json.mappers;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.xavier.basicPathfinderServer.Ability;

public class AbilityListMapper {
	public static List<String> map(List<Ability> abilities) {
		List<AbilityJson> json = new LinkedList<>();
		for (Ability ability : abilities) {
			json.add(new AbilityJson(ability.name, ability.getValue(), ability.getMod()));
		}
		
		Gson gson = new Gson();
		List<String> jsonStrings = new LinkedList<>();
		for (AbilityJson abilityJson : json) {
			jsonStrings.add(gson.toJson(abilityJson));
		}
		return jsonStrings;
	}
}
