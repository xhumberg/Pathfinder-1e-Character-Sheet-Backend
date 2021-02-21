package com.xavier.basicPathfinderServer;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
public class CharacterController {

	@GetMapping("/character/prosopa")
	@CrossOrigin(origins = "http://localhost:3000")
	public String getProsopa() {
		PathfinderCharacter prosopa = new PathfinderCharacter("Prosopa", "https://media.discordapp.net/attachments/526680690218106891/731649744937418792/107571564_607483240193629_5533577863028070138_n.png?width=684&height=606");
		Gson gson = new Gson();
		return gson.toJson(prosopa.convertToJson());
	}
	
}