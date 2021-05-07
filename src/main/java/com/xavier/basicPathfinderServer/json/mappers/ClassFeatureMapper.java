package com.xavier.basicPathfinderServer.json.mappers;

import java.util.LinkedList;
import java.util.List;

import com.xavier.basicPathfinderServer.ClassFeature;
import com.xavier.basicPathfinderServer.json.ClassFeatureJson;

public class ClassFeatureMapper {
	public static List<ClassFeatureJson> map(List<ClassFeature> features) {
		List<ClassFeatureJson> featureJsons = new LinkedList<>();
		
		for (ClassFeature feature : features) {
			if (feature.trackedResource == null) {
				ClassFeatureJson json = new ClassFeatureJson(feature.name, feature.description);
				featureJsons.add(json);
			}
		}
		
		return featureJsons;
	}
}
