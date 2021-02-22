package com.xavier.basicPathfinderServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class CharacterController {

	PathfinderCharacter character;
	
	@Autowired
	public CharacterController() {
		character = Prosopa.get();
	}
	
	@GetMapping("/character/prosopa")
	public String getProsopa() {
		Gson gson = new Gson();
		return gson.toJson(character.convertToJson());
	}
	
	@PutMapping("/character/prosopa/toggle/{adjustmentName}") 
	public void toggleAdjustment(@PathVariable String adjustmentName) {
		System.out.println("Time to toggle " + adjustmentName);
		character.toggleAdjustment(adjustmentName);
	}
	
}