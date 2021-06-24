package com.xavier.basicPathfinderServer.json.mappers;

import java.util.LinkedList;
import java.util.List;

import com.xavier.basicPathfinderServer.characterOwned.Feat;
import com.xavier.basicPathfinderServer.json.FeatJson;

public class FeatMapper {
	public static List<FeatJson> map(List<Feat> feats) {
		List<FeatJson> featJsons = new LinkedList<FeatJson>();
		
		for (Feat feat : feats) {
			FeatJson featJson = new FeatJson(feat.name, feat.description.replace("\\r\\n", "")); //TODO: make returns actually put in a line break
			featJsons.add(featJson);
		}
		
		return featJsons;
	}
}
