package com.xavier.basicPathfinderServer.controllers;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.xavier.basicPathfinderServer.PathfinderCharacter;
import com.xavier.basicPathfinderServer.characterOwned.Spell;
import com.xavier.basicPathfinderServer.databaseLayer.CharacterFromDatabaseLoader;
import com.xavier.basicPathfinderServer.databaseLayer.DatabaseAccess;
import com.xavier.basicPathfinderServer.databaseLayer.UserAccessDatabaseChecker;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers.AccessibleCharactersMapper;
import com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers.AdjustmentDatabaseModifier;
import com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers.HealthDatabaseModifier;
import com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers.SpellDatabaseModifier;
import com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers.TrackedResourceDatabaseModifier;
import com.xavier.basicPathfinderServer.google.GoogleAuthenticationResponseJson;
import com.xavier.basicPathfinderServer.numericals.Adjustment;

@RestController
public class CharacterController {
	Map<String, PathfinderCharacter> loadedCharacters; //TODO: Make this a cache
	Map<String, List<String>> cachedCharacterAccess; //TODO: Make this a cache
	Gson gson;
	
	private final String GET_CHARACTERS_FOR_USER_QUERY = "SELECT CharacterName, PathfinderCharacter.CharacterID FROM UserIDToEmail INNER JOIN UserAccess ON UserIDToEmail.UserID = UserAccess.UserID INNER JOIN PathfinderCharacter ON UserAccess.CharacterID = PathfinderCharacter.CharacterID WHERE UserIDToEmail.UserEmail = (?) OR UserAccess.UserID = -1;";
	
	@Autowired
	public CharacterController() {
		gson = new Gson();
		loadedCharacters = new HashMap<>();
		cachedCharacterAccess = new HashMap<>();
	}
	
	@GetMapping("/character/{id}")
	public String getProsopa(@PathVariable String id, @RequestParam(required = false) String token) {
		assertStringsAreSanitized(id);
		if (token == null){
			PathfinderCharacter character = new PathfinderCharacter("-1", "Error: cannot access character without logging in", "");
			return gson.toJson(character.convertToJson());
		} else {
			System.out.println("Request to get character id " + id);
			PathfinderCharacter character = loadCharacterID(id, token);
			return gson.toJson(character.convertToJson());
		}
	}
	
	private PathfinderCharacter loadCharacterID(String id, String token) {
		GoogleAuthenticationResponseJson authenticatedGoogleToken = authenticateToken(token);
		System.out.println(authenticatedGoogleToken.getEmail() + " wants to load character " + id);
		
		assertLoggedInUserHasAccessToCharacter(id, authenticatedGoogleToken.getEmail());
		
		if(!loadedCharacters.containsKey(id)) {
			loadedCharacters.put(id, CharacterFromDatabaseLoader.loadCharacter(id));
		} 
		return loadedCharacters.get(id);
	}
	
	public void assertLoggedInUserHasAccessToCharacter(String id, String email) {
		List<String> availableCharacters = cachedCharacterAccess.get(email);
		if (availableCharacters != null && availableCharacters.contains(id)) {
			return;
		}
		
		if (UserAccessDatabaseChecker.canEmailAccessCharacter(email, id)) {
			if (cachedCharacterAccess.get(email) == null) {
				cachedCharacterAccess.put(email, new LinkedList<String>());
			}
			cachedCharacterAccess.get(email).add(id);
		} else {
			throw new RuntimeException("Email " + email + " does not have access to character " + id + " but tried to access anyways.");
		}
	}

	@GetMapping("character/load")
	public String loadFromDatabase(@RequestParam String token) throws URISyntaxException, ClassNotFoundException {
		//TODO: query to make sure the user has access permission instead of just assuming they do
		GoogleAuthenticationResponseJson authenticatedGoogleToken = authenticateToken(token);
		System.out.println("Load characters for " + authenticatedGoogleToken.getEmail());
		
        DatabaseAccess<List<String>> db = new DatabaseAccess<>();
        List<String> response = db.executeSelectQuery(new AccessibleCharactersMapper(), GET_CHARACTERS_FOR_USER_QUERY, authenticatedGoogleToken.getEmail());
        db.close();
        if (response != null) {
        	return gson.toJson(response);
        }
        //If error
		return "[]";
	}

	private GoogleAuthenticationResponseJson authenticateToken(String tokenString) {
		//Authenticate Token
		RestTemplate restTemplate = new RestTemplate();
		String urlToHit = "https://oauth2.googleapis.com/tokeninfo?id_token=" + tokenString.replace("\"", "");
		ResponseEntity<String> response = restTemplate.getForEntity(urlToHit, String.class);
		GoogleAuthenticationResponseJson responseJson = gson.fromJson(response.getBody(), GoogleAuthenticationResponseJson.class);
		assertStringsAreSanitized(responseJson.getEmail());
		return responseJson;
	}
	
	@PutMapping("/character/{id}/toggle/{adjustmentName}") 
	public void toggleAdjustment(@PathVariable String id, @PathVariable String adjustmentName, @RequestParam(required = false) String token) {
		assertStringsAreSanitized(id, adjustmentName);
		System.out.println("Time to toggle " + adjustmentName);
		PathfinderCharacter character = loadCharacterID(id, token);
		if (character.isAdjustmentEnabled(adjustmentName)) {
			System.out.println("Disabling " + adjustmentName + " for character id " + id);
			Adjustment adjustment = character.toggleAdjustment(adjustmentName);
			AdjustmentDatabaseModifier.disableAdjustment(adjustment.getId(), id);
			
		} else {
			System.out.println("Enabling " + adjustmentName + " for character id " + id);
			Adjustment adjustment = character.toggleAdjustment(adjustmentName);
			AdjustmentDatabaseModifier.enableAdjustment(adjustment.getId(), id);
		}
	}
	
	@PutMapping("/character/{id}/forceReload")
	public void forceCharacterReload(@PathVariable String id, @RequestParam String token) {
		assertStringsAreSanitized(id);
		authenticateToken(token);
		System.out.println("Forcing reload from database for character " + id);
		loadedCharacters.remove(id);
		loadCharacterID(id, token);
	}
	
	@PutMapping("/character/{id}/castSpell")
	public void castSpellforCharacter(@PathVariable String id, @RequestParam String token, @RequestParam String classId, @RequestParam String spellName, @RequestParam String level) {
		assertStringsAreSanitized(id, classId, spellName, level);
		PathfinderCharacter character = loadCharacterID(id, token);
		
		int DCstart = spellName.lastIndexOf('(');
		if (DCstart > 1) {
			spellName = spellName.substring(0, DCstart-1);
		}
		
		System.out.println("Casting spell " + spellName);
		Spell spellThatWasCast = character.castSpell(Integer.parseInt(classId), spellName, Integer.parseInt(level));
		if (spellThatWasCast != null) {
			SpellDatabaseModifier.castSpell(id, spellThatWasCast.getId(), Integer.parseInt(level), Integer.parseInt(classId));
		}
	}
	
	@PutMapping("/character/{id}/uncastSpell")
	public void uncastSpellforCharacter(@PathVariable String id, @RequestParam String token, @RequestParam String classId, @RequestParam String spellName, @RequestParam String level) {
		assertStringsAreSanitized(id, classId, spellName, level);
		PathfinderCharacter character = loadCharacterID(id, token);
		int DCstart = spellName.lastIndexOf('(');
		if (DCstart > 1) {
			spellName = spellName.substring(0, DCstart-1);
		}
		System.out.println("Uncasting spell " + spellName);
		Spell spellThatWasUncast = character.uncastSpell(Integer.parseInt(classId), spellName, Integer.parseInt(level));
		if (spellThatWasUncast != null) {
			SpellDatabaseModifier.uncastSpell(id, spellThatWasUncast.getId(), Integer.parseInt(level), Integer.parseInt(classId));
		}
	}
	
	@PutMapping("/character/{id}/heal")
	public void healCharacter(@PathVariable String id, @RequestParam String token, @RequestParam String amount) {
		assertStringsAreSanitized(id, amount);
		PathfinderCharacter character = loadCharacterID(id, token);
		int damageRemaining = character.heal(Integer.parseInt(amount));
		HealthDatabaseModifier.setDamageTaken(damageRemaining, id);
	}
	
	@PutMapping("/character/{id}/damage")
	public void damageCharacter(@PathVariable String id, @RequestParam String token, @RequestParam String amount) {
		assertStringsAreSanitized(id, amount);
		PathfinderCharacter character = loadCharacterID(id, token);
		int totalDamage = character.takeDamage(Integer.parseInt(amount));
		HealthDatabaseModifier.setDamageTaken(totalDamage, id);
	}
	
	@PutMapping("/character/{id}/reduceResource/{resourceType}/{resourceId}")
	public void reduceResource(@PathVariable String id, @RequestParam String token, @PathVariable String resourceType, @PathVariable String resourceId) {
		assertStringsAreSanitized(id, resourceType, resourceId);
		PathfinderCharacter character = loadCharacterID(id, token);
		if (resourceType.equals("ITEM")) {
			int remainingUses = character.reduceUsesForItem(Integer.parseInt(resourceId));
			TrackedResourceDatabaseModifier.setResourcesRemaining(remainingUses, Integer.parseInt(resourceId));
		} else if (resourceType.equals("CLASS_FEATURE")) {
			int remainingUses = character.reduceUsesForClassFeature(Integer.parseInt(resourceId));
			TrackedResourceDatabaseModifier.setResourcesRemaining(remainingUses, Integer.parseInt(resourceId));
		} else if (resourceType.equals("MISC")) {
			int remainingUses = character.reduceUsesForMiscResource(Integer.parseInt(resourceId));
			TrackedResourceDatabaseModifier.setResourcesRemaining(remainingUses, Integer.parseInt(resourceId));
		} else {
			throw new IllegalArgumentException("Resource type: " + resourceType + " not supported!");
		}
	}
	
	@PutMapping("/character/{id}/increaseResource/{resourceType}/{resourceId}")
	public void increaseResource(@PathVariable String id, @RequestParam String token, @PathVariable String resourceType, @PathVariable String resourceId) {
		assertStringsAreSanitized(id, resourceType, resourceId);
		PathfinderCharacter character = loadCharacterID(id, token);
		if (resourceType.equals("ITEM")) {
			int remainingUses = character.increaseUsesForItem(Integer.parseInt(resourceId));
			TrackedResourceDatabaseModifier.setResourcesRemaining(remainingUses, Integer.parseInt(resourceId));
		} else if (resourceType.equals("CLASS_FEATURE")) {
			int remainingUses = character.increaseUsesForClassFeature(Integer.parseInt(resourceId));
			TrackedResourceDatabaseModifier.setResourcesRemaining(remainingUses, Integer.parseInt(resourceId));
		} else if (resourceType.equals("MISC")) {
			int remainingUses = character.increaseUsesForMiscResource(Integer.parseInt(resourceId));
			TrackedResourceDatabaseModifier.setResourcesRemaining(remainingUses, Integer.parseInt(resourceId));
		} else {
			throw new IllegalArgumentException("Resource type: " + resourceType + " not supported!");
		}
	}
	
	private void assertStringsAreSanitized(String... strings) {
		List<String> invalidStrings = new LinkedList<String>();
		invalidStrings.add("UserIDToEmail");
		invalidStrings.add("UserAccess");
		invalidStrings.add("PathfinderCharacter");
		invalidStrings.add("AvailableItems");
		invalidStrings.add("CharacterEquipment");
		invalidStrings.add("AvailableFeats");
		invalidStrings.add("CharacterFeats");
		invalidStrings.add("AvailableClassFeatures");
		invalidStrings.add("AvailableClasses");
		invalidStrings.add("CharacterClasses");
		invalidStrings.add("ClassesToClassFeatures");
		invalidStrings.add("CharacterClassFeatures");
		invalidStrings.add("TrackedResources");
		invalidStrings.add("CharactersTrackedResources");
		invalidStrings.add("AvailableSpells");
		invalidStrings.add("SpellsPrepped");
		invalidStrings.add("SpellsCast");
		invalidStrings.add("SpellsKnown");
		invalidStrings.add("CharacterWealth");
		invalidStrings.add("StandardAdjustments");
		invalidStrings.add("EnabledAdjustments");
		invalidStrings.add("AllowedAdjustments");
		invalidStrings.add("SkillRanks");
		invalidStrings.add("ClassSkills");
		invalidStrings.add("CharacterHP");
		invalidStrings.add("RacialTraits");
		invalidStrings.add("CharacterRacialTraits");
		invalidStrings.add("WeaponDefinitions");
		invalidStrings.add("CharacterWeapons");
		invalidStrings.add(" * ");
		
		for (String string : strings) {
			for (String invalidString : invalidStrings) {
				if (string.toLowerCase().contains(invalidString.toLowerCase())) {
					throw new RuntimeException("The phrase " + invalidString + " is not allowed.");
				}
			}
		}
	}
	
}