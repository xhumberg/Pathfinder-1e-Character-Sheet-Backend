package com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers;

import com.xavier.basicPathfinderServer.databaseLayer.DatabaseAccess;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.simpleMappers.SingleIntegerMapper;

public class RacialTraitDatabaseModifier {
	
	private final static String GET_LARGEST_AVAILABLE_RACIAL_TRAIT_ID = "SELECT traitId FROM RacialTraits ORDER BY traitId DESC LIMIT 1";
	private final static String ADD_RACIAL_TRAIT = "INSERT INTO RacialTraits VALUES (?, ?, ?, ?)";
	private final static String DELETE_RACIAL_TRAIT = "DELETE FROM RacialTraits WHERE traitId = ?";
	
	private final static String GIVE_CHARACTER_RACIAL_TRAIT = "INSERT INTO CharacterRacialTraits values (?, ?)";
	private final static String TAKE_CHARACTER_RACIAL_TRAIT = "DELETE FROM CharacterRacialTraits WHERE traitId = ? AND CharacterID = ?";
	private final static String DELETE_ALL_INSTANCES_OF_RACIAL_TRAIT_FROM_CHARACTERS = "DELETE FROM CharacterRacialTraits WHERE traitId = ?";

	public static int addNewRacialTrait(String name, String description, String adjustment) {
		DatabaseAccess<Integer> db = new DatabaseAccess<>();
		int traitId = db.executeSelectQuery(new SingleIntegerMapper(), GET_LARGEST_AVAILABLE_RACIAL_TRAIT_ID) + 1;
		db.executeModifyQuery(ADD_RACIAL_TRAIT, traitId, name, description, adjustment);
		db.close();
		return traitId;
	}

	public static void deleteRacialTrait(int id) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(DELETE_ALL_INSTANCES_OF_RACIAL_TRAIT_FROM_CHARACTERS, id);
		db.executeModifyQuery(DELETE_RACIAL_TRAIT, id);
		db.close();
	}
	
	public static void giveRacialTraitToCharacter(int traitId, String characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(GIVE_CHARACTER_RACIAL_TRAIT, characterId, traitId);
		db.close();
	}
	
	public static void takeRacialTraitFromCharacter(int traitId, String characterId) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		db.executeModifyQuery(TAKE_CHARACTER_RACIAL_TRAIT, traitId, characterId);
		db.close();
	}
}
