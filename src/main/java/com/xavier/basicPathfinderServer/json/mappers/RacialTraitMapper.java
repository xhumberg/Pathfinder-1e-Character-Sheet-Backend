package com.xavier.basicPathfinderServer.json.mappers;

import java.util.LinkedList;
import java.util.List;

import com.xavier.basicPathfinderServer.RacialTrait;
import com.xavier.basicPathfinderServer.json.RacialTraitJson;

public class RacialTraitMapper {
	public static List<RacialTraitJson> map(List<RacialTrait> racialTraits) {
		List<RacialTraitJson> racialTraitJsons = new LinkedList<RacialTraitJson>();
		
		for (RacialTrait racialTrait : racialTraits) {
			RacialTraitJson racialTraitJson = new RacialTraitJson(racialTrait.name, racialTrait.description.replace("\\r\\n", "")); //TODO: make returns actually put in a line break
			racialTraitJsons.add(racialTraitJson);
		}
		
		return racialTraitJsons;
	}
}
