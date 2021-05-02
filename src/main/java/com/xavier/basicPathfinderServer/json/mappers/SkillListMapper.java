package com.xavier.basicPathfinderServer.json.mappers;

import java.util.LinkedList;
import java.util.List;

import com.xavier.basicPathfinderServer.Skill;
import com.xavier.basicPathfinderServer.Stat;

public class SkillListMapper {
	public static List<SkillJson> map(List<Stat> skills) {
		List<SkillJson> json = new LinkedList<>();
		for (Stat skill : skills) {
			json.add(new SkillJson(skill.getName(), skill.getValue()));
		}
		
		return json;
	}
}
