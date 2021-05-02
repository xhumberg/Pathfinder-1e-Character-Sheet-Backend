package com.xavier.basicPathfinderServer.json;

import java.util.List;

import com.xavier.basicPathfinderServer.PathfinderCharacter;
import com.xavier.basicPathfinderServer.json.mappers.AbilityJson;
import com.xavier.basicPathfinderServer.json.mappers.AbilityListMapper;
import com.xavier.basicPathfinderServer.json.mappers.SkillJson;
import com.xavier.basicPathfinderServer.json.mappers.SkillListMapper;
import com.xavier.basicPathfinderServer.json.mappers.SpellcastingListMapper;

public class CharacterJson {
	public final int characterId;
	public final String name;
	public final String imageUrl;
	public final String alignment;
	public final String player;
	public final String race;
	public final String size;
	public final String gender;
	public final String age;
	public final String weight;
	public final String height;
	public final String typeAndSubtype;
	public final int totalLevel;
	public final String classes;
	public final String senses;
	public final String landSpeed;
	public final String climbSpeed;
	public final String swimSpeed;
	public final String flySpeed;
	public final int unspentSkillRanks;
	public final int ac;
	public final int flatFooted;
	public final int touch;
	public final int cmd;
	public final String fortitude;
	public final String reflex;
	public final String will;
	public final List<String> specialDefenses;
	public final List<String> specialOffenses;
	public final List<SkillJson> skills;
	public final List<AbilityJson> ability;
	public final List<SpellcastingJson> spellcasting;
	
	public CharacterJson(PathfinderCharacter character) {
		this.characterId = character.getCharacterId();
		this.name = character.getName();
		this.imageUrl = character.getImageUrl();
		this.alignment = character.getAlignment();
		this.player = character.getPlayer();
		this.race = character.getRace();
		this.size = character.getSize();
		this.gender = character.getGender();
		this.age = character.getAge();
		this.weight = character.getWeight();
		this.height = character.getHeight();
		this.typeAndSubtype = character.getTypeAndSubtype();
		this.totalLevel = character.getTotalLevel();
		this.classes = character.getClasses();
		this.senses = character.getSenses();
		this.landSpeed = character.getLandSpeed();
		this.climbSpeed = character.getClimbSpeed();
		this.swimSpeed = character.getSwimSpeed();
		this.flySpeed = character.getFlySpeed();
		this.unspentSkillRanks = character.getRemainingSkillRanks();
		this.ac = character.getStatValue("AC");
		this.flatFooted = character.getStatValue("Flat-Footed");
		this.touch = character.getStatValue("Touch");
		this.cmd = -1;
		this.fortitude = "+" + character.getStatValue("Fortitude"); //TODO: could be negative
		this.reflex = "+" + character.getStatValue("Reflex"); //TODO: could be negative
		this.will = "+" + character.getStatValue("Will"); //TODO: could be negative
		
		this.specialDefenses = character.getSpecialDefenses();
		this.specialOffenses = character.getSpecialOffenses();
		this.skills = SkillListMapper.map(character.getSkills());
		this.ability = AbilityListMapper.map(character.abilities);
		this.spellcasting = SpellcastingListMapper.map(character.spellcastingByClass);
	}

	public int getCharacterId() {
		return characterId;
	}

	public String getName() {
		return name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getAlignment() {
		return alignment;
	}

	public String getPlayer() {
		return player;
	}

	public String getRace() {
		return race;
	}

	public String getSize() {
		return size;
	}

	public String getGender() {
		return gender;
	}

	public String getAge() {
		return age;
	}

	public String getWeight() {
		return weight;
	}

	public String getHeight() {
		return height;
	}

	public String getTypeAndSubtype() {
		return typeAndSubtype;
	}

	public int getTotalLevel() {
		return totalLevel;
	}

	public String getClasses() {
		return classes;
	}

	public String getSenses() {
		return senses;
	}

	public String getLandSpeed() {
		return landSpeed;
	}

	public String getClimbSpeed() {
		return climbSpeed;
	}

	public String getSwimSpeed() {
		return swimSpeed;
	}

	public String getFlySpeed() {
		return flySpeed;
	}

	public List<AbilityJson> getAbility() {
		return ability;
	}

	public List<SpellcastingJson> getSpellcasting() {
		return spellcasting;
	}

	public List<String> getSpecialDefenses() {
		return specialDefenses;
	}

	public List<String> getSpecialOffenses() {
		return specialOffenses;
	}

	public int getUnspentSkillRanks() {
		return unspentSkillRanks;
	}
}
