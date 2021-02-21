package com.xavier.basicPathfinderServer;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CapitalController {

	@GetMapping("/capital/{phrase}")
	@CrossOrigin(origins = "http://localhost:3000")
	public String capitalize(@PathVariable String phrase) {
		System.out.println("There was a request to capitalize " + phrase);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "{\"caps\": \"" + phrase.toUpperCase() + "\"}";
	}
}
