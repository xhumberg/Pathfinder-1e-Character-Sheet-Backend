package com.xavier.basicPathfinderServer.json.mappers;

import java.util.LinkedList;
import java.util.List;

import com.xavier.basicPathfinderServer.json.SkillJson;
import com.xavier.basicPathfinderServer.numericals.Stat;

public class SkillListMapper {
	public static List<SkillJson> map(List<Stat> skills) {
		List<SkillJson> json = new LinkedList<>();
		for (Stat skill : skills) {
			json.add(new SkillJson(skill.getName().displayStrings[0], skill.getValue(), skill.getBase(), skill.hasModifiers("All Skills", "Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma")));
		}
		
		return json;
	}
}
