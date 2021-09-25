package com.xavier.basicPathfinderServer.controllers.jsonObjects;

public class ToggleAdjustmentJson {
    public final String googleToken;
    public final String adjustmentName;
    
    public ToggleAdjustmentJson(String googleToken, String adjustmentName) {
    	this.googleToken = googleToken;
    	this.adjustmentName = adjustmentName;
    }
}
