package com.xavier.basicPathfinderServer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class CharacterController {

	PathfinderCharacter character;
	
	@GetMapping("/character/prosopa")
	public String getProsopa() {
		character = Prosopa.get();
		Gson gson = new Gson();
		return gson.toJson(character.convertToJson());
	}
	
	@PutMapping("/character/prosopa/toggle/{adjustmentName}") 
	public void toggleAdjustment(@PathVariable String adjustmentName) {
		character.toggleAdjustment(adjustmentName);
	}
	
}