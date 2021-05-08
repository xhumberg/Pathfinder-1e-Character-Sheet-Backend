package com.xavier.basicPathfinderServer.json;

import java.util.List;

import com.xavier.basicPathfinderServer.PathfinderCharacter;
import com.xavier.basicPathfinderServer.json.mappers.AbilityListMapper;
import com.xavier.basicPathfinderServer.json.mappers.AdjustmentListMapper;
import com.xavier.basicPathfinderServer.json.mappers.ClassFeatureMapper;
import com.xavier.basicPathfinderServer.json.mappers.ClassTrackedFeatureMapper;
import com.xavier.basicPathfinderServer.json.mappers.EnabledAdjustmentListMapper;
import com.xavier.basicPathfinderServer.json.mappers.FeatMapper;
import com.xavier.basicPathfinderServer.json.mappers.GoldMapper;
import com.xavier.basicPathfinderServer.json.mappers.ItemMapper;
import com.xavier.basicPathfinderServer.json.mappers.MiscTrackedResourceMapper;
import com.xavier.basicPathfinderServer.json.mappers.RacialTraitMapper;
import com.xavier.basicPathfinderServer.json.mappers.SkillListMapper;
import com.xavier.basicPathfinderServer.json.mappers.SpellcastingListMapper;
import com.xavier.basicPathfinderServer.json.mappers.TrackedItemMapper;
import com.xavier.basicPathfinderServer.json.mappers.WeaponsMapper;

public class CharacterJson {
	public final int characterId;
	public final String name; //
	public final String imageUrl; //
	public final String alignment; //
	public final String player;
	public final String race; //
	public final String size; //
	public final String gender; //
	public final String age; //
	public final String weight; //
	public final String height; //
	public final String typeAndSubtype; //
	public final int totalLevel; //
	public final String classes; //
	public final String senses; //
	public final String landSpeed; //
	public final String climbSpeed;
	public final String swimSpeed;
	public final String flySpeed;
	public final int unspentSkillRanks; //UNSPENT!
	public final int ac;
	public final int flatFooted;
	public final int touch;
	public final int cmb;
	public final int cmd;
	public final String fortitude;
	public final String reflex;
	public final String will;
	public final String initiative;
	public final int bab;
	public final int maxHp;
	public final int currentHp;
	public final List<String> specialDefenses;
	public final List<String> specialOffenses;
	public final List<String> allowedAdjustments;
	public final List<String> enabledAdjustments;
	public final List<SkillJson> skills;
	public final List<AbilityJson> ability;
	public final List<SpellcastingJson> spellcasting;
	public final List<FeatJson> feats;
	public final List<ClassTrackedFeatureJson> classTrackedFeatures;
	public final List<ClassFeatureJson> classFeatures;
	public final List<RacialTraitJson> racialTraits;
	public final List<MiscTrackedResourceJson> miscTrackedResources;
	public final List<TrackedItemJson> trackedItems;
	public final List<ItemJson> items;
	public final List<WeaponJson> weapons;
	public final GoldJson gold;
	
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
		this.cmb = -1;
		this.cmd = -1;
		this.fortitude = "+" + character.getStatValue("Fortitude"); //TODO: could be negative
		this.reflex = "+" + character.getStatValue("Reflex"); //TODO: could be negative
		this.will = "+" + character.getStatValue("Will"); //TODO: could be negative
		this.initiative = "+" + character.getStatValue("Initiative");
		this.bab = character.getStatValue("BAB");
		this.maxHp = character.getMaxHealth();
		this.currentHp = character.getCurrentHealth();
		
		this.specialDefenses = character.getSpecialDefenses();
		this.specialOffenses = character.getSpecialOffenses();
		this.allowedAdjustments = AdjustmentListMapper.map(character.getAllowedAdjustments());
		this.enabledAdjustments = EnabledAdjustmentListMapper.map(character.getAllowedAdjustments());
		this.skills = SkillListMapper.map(character.getSkills());
		this.ability = AbilityListMapper.map(character.abilities);
		this.spellcasting = SpellcastingListMapper.map(character.spellcastingByClass);
		this.feats = FeatMapper.map(character.getFeats());
		this.classTrackedFeatures = ClassTrackedFeatureMapper.map(character.getClassFeatures());
		this.classFeatures = ClassFeatureMapper.map(character.getClassFeatures());
		this.racialTraits = RacialTraitMapper.map(character.getRacialTraits());
		this.miscTrackedResources = MiscTrackedResourceMapper.map(character.getMiscTrackedResources());
		this.trackedItems = TrackedItemMapper.map(character.getItems());
		this.items = ItemMapper.map(character.getItems());
		this.gold = GoldMapper.map(character);
		this.weapons = WeaponsMapper.map(character.getWeapons(), character.getStatValue("BAB"));
		
	}

	public int getAc() {
		return ac;
	}

	public int getFlatFooted() {
		return flatFooted;
	}

	public int getTouch() {
		return touch;
	}

	public int getCmd() {
		return cmd;
	}

	public String getFortitude() {
		return fortitude;
	}

	public String getReflex() {
		return reflex;
	}

	public String getWill() {
		return will;
	}

	public List<String> getAllowedAdjustments() {
		return allowedAdjustments;
	}

	public List<String> getEnabledAdjustments() {
		return enabledAdjustments;
	}

	public List<SkillJson> getSkills() {
		return skills;
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
