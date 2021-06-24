package com.xavier.basicPathfinderServer.json.mappers;

import java.util.LinkedList;
import java.util.List;

import com.xavier.basicPathfinderServer.json.MiscTrackedResourceJson;
import com.xavier.basicPathfinderServer.numericals.TrackedResource;

public class MiscTrackedResourceMapper {
	public static List<MiscTrackedResourceJson> map(List<TrackedResource> resources) {
		List<MiscTrackedResourceJson> resourceJsons = new LinkedList<>();
		
		for (TrackedResource resource : resources) {
			MiscTrackedResourceJson json = new MiscTrackedResourceJson(resource.getId(), resource.getName(), resource.getName() + ". TODO: implement description", resource.getRemaining());
			resourceJsons.add(json);
		}
		
		return resourceJsons;
	}
}
