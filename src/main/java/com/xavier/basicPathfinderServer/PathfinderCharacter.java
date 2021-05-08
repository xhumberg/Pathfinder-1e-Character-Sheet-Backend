package com.xavier.basicPathfinderServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.xavier.basicPathfinderServer.Weapon.WeaponType;
import com.xavier.basicPathfinderServer.json.CharacterJson;
import com.xavier.basicPathfinderServer.json.WeaponStats;

public class PathfinderCharacter {
	
	public int characterId;
	public String name;
	public String playerName;
	public String imageUrl;
	public String alignment;
	public String race;
	public String characterSize;
	public String gender;
	public String age;
	public String weight;
	public String height;
	public List<Ability> abilities;
	public final HashMap<String, Adjustment> adjustments;
	public List<Adjustment> allowedAdjustments;
	public final List<String> typesAndSubtypes;
	public final List<String> senses;
	public final List<String> specialDefenses;
	public final List<String> specialOffenses;
	public final List<String> speed;
	public final List<CharacterClass> classes;
	public final HashMap<String, Stat> allStats;
	public final HashMap<Weapon.WeaponType, HashMap<Weapon, WeaponStats>> weapons;
	//Weapon damage.........
	public final HashMap<Integer, Spellcasting> spellcastingByClass;
	public HP hp;
	public SkillRanks skillRanks;
	private final List<Item> items;
	private final List<Feat> feats;
	private final List<RacialTrait> racialTraits;
	private final List<ClassFeature> classFeatures;
	private final List<TrackedResource> miscTrackedResources;
	private int totalEarnedGold;
	private int spentGold;
	
	public PathfinderCharacter(int characterId, String name, String imageUrl) {
		this.characterId = characterId;
		this.name = name;
		this.imageUrl = imageUrl;
		allStats = new HashMap<>();
		initAbilities();
		initOtherNeededStats();
		adjustments = new HashMap<>();
		allowedAdjustments = new ArrayList<>();
		typesAndSubtypes = new ArrayList<>();
		senses = new ArrayList<>();
		specialDefenses = new ArrayList<>();
		specialOffenses = new ArrayList<>();
		speed = new ArrayList<>();
		classes = new ArrayList<>();
		weapons = new HashMap<>();
		spellcastingByClass = new HashMap<>();
		hp = new HP(getAbility("Constitution"));
		skillRanks = new SkillRanks(getAbility("Intelligence"));
		items = new LinkedList<>();
		feats = new LinkedList<>();
		racialTraits = new LinkedList<>();
		classFeatures = new LinkedList<>();
		miscTrackedResources = new LinkedList<>();
		totalEarnedGold = 0;
		
		System.out.println("New character created. ID: " + characterId + "; Name: " + name);
	}

	private void initAbilities() {
		abilities = new ArrayList<Ability>(6);
		abilities.add(new Ability("Strength"));
		abilities.add(new Ability("Dexterity"));
		abilities.add(new Ability("Constitution"));
		abilities.add(new Ability("Intelligence"));
		abilities.add(new Ability("Wisdom"));
		abilities.add(new Ability("Charisma"));
		addAbilitiesToStats();
	}
	
	private void addAbilitiesToStats() {
		for(Ability ability : abilities) {
			allStats.put(ability.getName(), ability);
		}
	}
	
	private void initOtherNeededStats() {
		initAttackMods();
		initSaves();
		initAC();
		initSkills();
		initNewStat("Level");
		initStatWithStats("Initiative", getAbility("Dexterity"));
	}

	private void initAttackMods() {
		initNewStat("BAB");
		initNewStat("All Attacks");
		initNewStat("Melee Attacks");
		initNewStat("Ranged attacks");
		initNewStat("All Damage");
		initNewStat("Melee Damage");
		initNewStat("Ranged Damage");
	}

	private void initSaves() {
		Stat allSaves = initNewStat("All Saves");
		initStatWithStats("Will", getAbility("Wisdom"), allSaves);
		initStatWithStats("Fortitude", getAbility("Constitution"), allSaves);
		initStatWithStats("Reflex", getAbility("Dexterity"), allSaves);
	}
	
	private void initAC() {
		Stat allAC = initNewStat("All AC");
		initStatWithStats("AC", getAbility("Dexterity"), allAC);
		initStatWithStats("Flat-Footed", allAC);
		initStatWithStats("Touch", getAbility("Dexterity"), allAC);
		setStatBase("AC", 10);
		setStatBase("Flat-Footed", 10);
		setStatBase("Touch", 10);
	}
	
	private void initSkills() {
		Stat allSkills = initNewStat("All Skills");
		
		initSkillWithStats("Acrobatics", getAbility("Dexterity"), allSkills);
		initSkillWithStats("Appraise", getAbility("Intelligence"), allSkills);
		initSkillWithStats("Bluff", getAbility("Charisma"), allSkills);
		initSkillWithStats("Climb", getAbility("Strength"), allSkills);
		initSkillWithStats("Craft A", getAbility("Intelligence"), allSkills);
		initSkillWithStats("Craft B", getAbility("Intelligence"), allSkills);
		initSkillWithStats("Diplomacy", getAbility("Charisma"), allSkills);
		initSkillWithStats("Disable Device", getAbility("Dexterity"), allSkills);
		initSkillWithStats("Disguise", getAbility("Charisma"), allSkills);
		initSkillWithStats("Escape Artist", getAbility("Dexterity"), allSkills);
		initSkillWithStats("Fly", getAbility("Dexterity"), allSkills);
		initSkillWithStats("Handle Animal", getAbility("Charisma"), allSkills);
		initSkillWithStats("Heal", getAbility("Wisdom"), allSkills);
		initSkillWithStats("Intimidate", getAbility("Charisma"), allSkills);
		initSkillWithStats("Knowledge (Arcana)", getAbility("Intelligence"), allSkills);
		initSkillWithStats("Knowledge (Dungeoneering)", getAbility("Intelligence"), allSkills);
		initSkillWithStats("Knowledge (Engineering)", getAbility("Intelligence"), allSkills);
		initSkillWithStats("Knowledge (Geography)", getAbility("Intelligence"), allSkills);
		initSkillWithStats("Knowledge (History)", getAbility("Intelligence"), allSkills);
		initSkillWithStats("Knowledge (Local)", getAbility("Intelligence"), allSkills);
		initSkillWithStats("Knowledge (Nature)", getAbility("Intelligence"), allSkills);
		initSkillWithStats("Knowledge (Nobility)", getAbility("Intelligence"), allSkills);
		initSkillWithStats("Knowledge (Planes)", getAbility("Intelligence"), allSkills);
		initSkillWithStats("Knowledge (Religion)", getAbility("Intelligence"), allSkills);
		initSkillWithStats("Linguistics", getAbility("Intelligence"), allSkills);
		initSkillWithStats("Perception", getAbility("Wisdom"), allSkills);
		initSkillWithStats("Perform", getAbility("Charisma"), allSkills);
		initSkillWithStats("Profession", getAbility("Wisdom"), allSkills);
		initSkillWithStats("Ride", getAbility("Dexterity"), allSkills);
		initSkillWithStats("Sense Motive", getAbility("Wisdom"), allSkills);
		initSkillWithStats("Sleight of Hand", getAbility("Dexterity"), allSkills);
		initSkillWithStats("Spellcraft", getAbility("Intelligence"), allSkills);
		initSkillWithStats("Stealth", getAbility("Dexterity"), allSkills);
		initSkillWithStats("Survival", getAbility("Wisdom"), allSkills);
		initSkillWithStats("Swim", getAbility("Strength"), allSkills);
		initSkillWithStats("UMD", getAbility("Charisma"), allSkills);
	}

	private Stat initStatWithStats(String statName, Stat... otherStatsToAdd) {
		Stat stat = initNewStat(statName);
		for (Stat otherStat : otherStatsToAdd) {
			stat.addStat(otherStat);
		}
		return stat;
	}

	private Stat initNewStat(String statName) {
		Stat newStat = new Stat(statName);
		allStats.put(statName, newStat);
		return newStat;
	}

	private Skill initSkillWithStats(String statName, Stat... otherStatsToAdd) {
		Skill skill = initNewSkill(statName);
		for (Stat otherStat : otherStatsToAdd) {
			skill.addStat(otherStat);
		}
		return skill;
	}

	private Skill initNewSkill(String statName) {
		Skill newSkill = new Skill(statName);
		allStats.put(statName, newSkill);
		return newSkill;
	}
	
	private void setStatBase(String statName, int newBase) {
		Stat stat = allStats.get(statName);
		stat.setBaseValue(newBase);
	}
	
	public void setAbility(String abilityName, int baseValue) {
		for (Ability ability : abilities) {
			if (ability.getName().equals(abilityName)) {
				ability.setBaseValue(baseValue);
				break;
			}
		}
		System.out.println(name + " has a " + baseValue + " base " + abilityName);
	}
	
	public CharacterJson convertToJson() {
		return new CharacterJson(this);
	}

	public Adjustment toggleAdjustment(String adjustmentName) {
		Adjustment adjustment = adjustments.get(adjustmentName);
		adjustment.toggleAdjustment();
		
		handleMiscAdjustmentFeatures(adjustment);
		return adjustment;
	}

	public void addAdjustment(Adjustment adjustment) {
		handleMiscAdjustmentFeatures(adjustment);
		adjustments.put(adjustment.name, adjustment);
		addAdjustmentToApplicableStats(adjustment);
	}
	
	public void handleMiscAdjustmentFeatures(Adjustment adjustment) {
		if (adjustment.isEnabled()) {
			if (!adjustment.types.isEmpty())
				typesAndSubtypes.addAll(adjustment.types);
			if (!adjustment.senses.isEmpty())
				senses.addAll(adjustment.senses);
			if (!adjustment.specialDefenses.isEmpty())
				specialDefenses.addAll(adjustment.specialDefenses);
			if (!adjustment.specialOffenses.isEmpty())
				specialOffenses.addAll(adjustment.specialOffenses);
			if (adjustment.speed != null)
				speed.add(adjustment.speed);
		} else {
			if (!adjustment.types.isEmpty())
				typesAndSubtypes.removeAll(adjustment.types);
			if (!adjustment.senses.isEmpty())
				senses.removeAll(adjustment.senses);
			if (!adjustment.specialDefenses.isEmpty())
				specialDefenses.removeAll(adjustment.specialDefenses);
			if (!adjustment.specialOffenses.isEmpty())
				specialOffenses.removeAll(adjustment.specialOffenses);
			if (adjustment.speed != null)
				speed.remove(adjustment.speed);
		}
	}

	private void addAdjustmentToApplicableStats(Adjustment adjustment) {
		for (String adjustedStatName : adjustment.getAdjustedStats()) {
			Stat adjustedStat = allStats.get(adjustedStatName);
			adjustedStat.addAdjustment(adjustment);
		}
	}

	public int getStatValue(String statName) {
		return allStats.get(statName).getValue();
	}

	private int getBaseStatValue(String statName) {
		return allStats.get(statName).getBase();
	}
	
	public int getAbilityValue(String abilityName) {
		return ((Ability)allStats.get(abilityName)).getFullValue();
	}

	public int getAbilityMod(String abilityName) {
		return ((Ability)allStats.get(abilityName)).getMod();
	}
	
	Stat getStat(String statName) {
		return allStats.get(statName);
	}
	
	void addStat(String statName) {
		allStats.put(statName, new Stat(statName));
	}

	public void giveWeapon(Weapon weapon, String attackStat, String damageStat, WeaponType type) {
		HashMap<Weapon, WeaponStats> weaponsOfType = weapons.get(type);
		if (weaponsOfType == null) {
			weaponsOfType = new HashMap<Weapon, WeaponStats>();
			weapons.put(type, weaponsOfType);
		}
		
		String attackModString = weapon.getTitle() + " attack mod";
		Stat weaponAttackStat = new Stat(attackModString);
		Adjustment weaponAttackAdjustment = new Adjustment(-1, attackStat, true);
		weaponAttackAdjustment.addEffect(attackModString, attackStat, getStat(attackStat));
		weaponAttackAdjustment.addEffect(attackModString, "All Attacks", getStat("All Attacks"));
		weaponAttackAdjustment.addEffect(attackModString , "BAB", getStat("BAB"));
		if (type == Weapon.WeaponType.MELEE) {
			weaponAttackAdjustment.addEffect(attackModString, "Melee Specific", getStat("Melee Attacks"));
		} else {
			weaponAttackAdjustment.addEffect(attackModString, "Ranged Specific", getStat("Ranged Attacks"));
		}
		weaponAttackStat.addAdjustment(weaponAttackAdjustment);
		
		String damageModString = weapon.getTitle() + " damage mod";
		Stat weaponDamageStat = new Stat(damageModString);
		Adjustment weaponDamageAdjustment = new Adjustment(-1, damageStat, true);
		if (damageStat != null && !damageStat.isBlank())
			weaponDamageAdjustment.addEffect(damageModString, damageStat, getStat(damageStat));
		weaponDamageAdjustment.addEffect(weapon.getTitle(), "All Damage", getStat("All Damage"));
		if (type == Weapon.WeaponType.MELEE) {
			weaponDamageAdjustment.addEffect(damageModString, "Melee Specific", getStat("Melee Damage"));
		} else {
			weaponDamageAdjustment.addEffect(damageModString, "Ranged Specific", getStat("Ranged Damage"));
		}
		weaponDamageStat.addAdjustment(weaponDamageAdjustment);
		
		weaponsOfType.put(weapon, new WeaponStats(weaponAttackStat, weaponDamageStat));
	}

	public int getMeleeAttack(String weaponTitle) {
		HashMap<Weapon, WeaponStats> meleeWeapons = weapons.get(Weapon.WeaponType.MELEE);
		
		for (Weapon weapon : meleeWeapons.keySet()) {
			if (weapon.getTitle().equals(weaponTitle)) {
				return meleeWeapons.get(weapon).attackStat.getValue();
			}
		}
		
		return -100;
	}
	
	public int getMeleeDamage(String weaponTitle) {
		HashMap<Weapon, WeaponStats> meleeWeapons = weapons.get(Weapon.WeaponType.MELEE);
		
		for (Weapon weapon : meleeWeapons.keySet()) {
			if (weapon.getTitle().equals(weaponTitle)) {
				return meleeWeapons.get(weapon).damageStat.getValue();
			}
		}
		
		return -100;
	}

	public void giveSpellcasting(int classId, String name, CastingType type, int casterLevel, String castingStat) {
		Spellcasting newSpellcasting = new Spellcasting(classId, name, type, casterLevel, castingStat, this);
		spellcastingByClass.put(classId, newSpellcasting);
	}

	public void setSpellsPerDay(int classId, int level, int basePerDay) {
		Spellcasting spellcastingStats = spellcastingByClass.get(classId);
		if (spellcastingStats != null) {
			spellcastingStats.setSpellsPerDay(level, basePerDay);
		}
	}

	public int getSpellsPerDay(int classId, int level) {
		Spellcasting spellcastingStats = spellcastingByClass.get(classId);
		if (spellcastingStats != null) {
			return spellcastingStats.getSpellsPerDay(level);
		}
		return -1;
	}

	public void giveSpellKnown(int classId, Spell spell) {
		Spellcasting spellcasting = spellcastingByClass.get(classId);
		if (spellcasting != null) {
			spellcasting.addSpellKnown(spell);
		}
	}

	public void prepSpell(int classId, String spellName, int level) {
		Spellcasting spellcasting = spellcastingByClass.get(classId);
		if (spellcasting != null) {
			spellcasting.prepSpell(spellName, level);
		}
	}
	
	public void castSpell(int classId, String spellName, int level) {
		Spellcasting spellcasting = spellcastingByClass.get(classId);
		if (spellcasting != null) {
			spellcasting.castSpell(spellName, level);
		}
	}

	public Ability getAbility(String abilityName) {
		return (Ability)allStats.get(abilityName);
	}

	public Integer getSpellDC(int classId, String spellName, int level) {
		Spellcasting spellcasting = spellcastingByClass.get(classId);
		if (spellcasting != null) {
			return spellcasting.getSpellDC(spellName, level);
		}
		return -1;
	}

	public void setSkillRanks(int ranks, String skillName) {
		Skill skill = (Skill)getStat(skillName);
		int totalRanks = skill.getSkillRanks();
		int additionalRanksToSpend = ranks-totalRanks;
		skillRanks.spendRanks(additionalRanksToSpend);
		skill.setRanks(ranks);
		
		System.out.println(name + " has " + ranks + " ranks in " + skillName);
	}

	public void setClassSkill(String skillName) {
		Skill skill = (Skill)getStat(skillName);
		skill.setClassSkill();
		System.out.println(name + " has " + skillName + " as a class skill.");
	}

	public void addHitDice(int numberOfDice, int diceType) {
		hp.addHitDice(numberOfDice, diceType);
	}

	public Integer getMaxHealth() {
		return hp.getMaxHealth();
	}

	public void setFavoredClassBonusHP(int classBonus) {
		hp.setFavoredClassBonusHP(classBonus);
	}

	public Integer getCurrentHealth() {
		return hp.getCurrentHealth();
	}

	public void takeDamage(int damage) {
		hp.takeDamage(damage);
	}

	public void heal(int health) {
		hp.heal(health);
	}

	public void fullHeal() {
		hp.fullHeal();
	}

	public void addTotalSkillRanks(int levelsInClass, int numberOfRanks) {
		skillRanks.addRanks(levelsInClass, numberOfRanks);
	}

	public Integer getRemainingSkillRanks() {
		return skillRanks.getRemainingRanks();
	}

	public Integer getMaxRanks() {
		return skillRanks.getMaxRanks();
	}

	public String getName() {
		return name;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}
	
	public String getAlignment() {
		return alignment;
	}
	
	public void setPlayer(String player) {
		this.playerName = player;
		System.out.println(name + " is played by " + player);
	}

	public String getPlayer() {
		return playerName;
	}
	
	public void setAllowedAdjustments(List<Adjustment> adjustments) {
		allowedAdjustments = adjustments;
		for (Adjustment adjustment : adjustments) {
			addAdjustment(adjustment);
		}
	}

	public List<Adjustment> getAllowedAdjustments() {
		return allowedAdjustments;
	}

	public void toggleAdjustments(List<String> enabledAdjustments) {
		for (String adjustment : enabledAdjustments) {
			toggleAdjustment(adjustment);
		}
	}

	public void addClasses(List<CharacterClass> classes) {
		for (CharacterClass characterClass : classes) {
			addClass(characterClass);
			System.out.println(name + " has class: " + characterClass.getName());
		}
	}
	
	public void addClass(CharacterClass characterClass) {
		classes.add(characterClass);
		addToStat("Level", characterClass.getLevel());
		addToStat("BAB", characterClass.getBab());
		addToStat("Fortitude", characterClass.getFort());
		addToStat("Reflex", characterClass.getRef());
		addToStat("Will", characterClass.getWill());
		addHitDice(characterClass.getLevel(), characterClass.getHitDice());
		addTotalSkillRanks(characterClass.getLevel(), characterClass.getBaseSkillsPerLevel());
		if (characterClass.hasSpellcasting()) {
			//TODO: Caster Level is not always class level (for example, bloodrager and paladin)
			giveSpellcasting(characterClass.getId(), characterClass.getName(), characterClass.getSpellcastingType(), characterClass.getLevel(), characterClass.getSpellcastingAbility());
			for (int spellLevel : characterClass.getBaseSpellsPerDay().keySet()) {
				int baseSpells = characterClass.getBaseSpellsPerDay().get(spellLevel);
				setSpellsPerDay(characterClass.getId(), spellLevel, baseSpells);
			}
		}
	}
	
	private void addToStat(String statName, int amount) {
		int statBase = getBaseStatValue(statName);
		statBase += amount;
		setStatBase(statName, statBase);
	}

	public void equip(Item item) {
		if (item.hasAdjustment()) {
			addAdjustment(item.getAdjustment());
		}
		items.add(item);
		item.setEquipped(true);
	}

	public List<Item> getItems() {
		return new LinkedList<Item>(items);
	}

	public void giveItem(Item item) {
		items.add(item);
	}

	public List<Item> getAllItemsWithName(String string) {
		List<Item> foundItems = new ArrayList<>();
		for (Item item : items) {
			if (item.getName().equals(string)) {
				foundItems.add(item);
			}
		}
		return foundItems;
	}

	public void giveFeat(Feat feat) {
		feats.add(feat);
		if (feat.effect != null) {
			addAdjustment(feat.effect);
		}
		System.out.println(name + " has taken " + feat.name);
	}

	public void giveRacialTrait(RacialTrait trait) {
		racialTraits.add(trait);
		if (trait.effect != null) {
			addAdjustment(trait.effect);
		}
		System.out.println(name + " has racial trait " + trait.name);
	}

	public void giveClassFeature(ClassFeature feature) {
		classFeatures.add(feature);
		if (feature.effect != null) {
			addAdjustment(feature.effect);
		}
		System.out.println(name + " has " + feature.name + " as a class feature.");
	}

	public void giveMiscTrackedResource(TrackedResource resource) {
		miscTrackedResources.add(resource);
		System.out.println(name + " has misc tracked resource: " + resource.getName());
	}

	public void setTotalEarnedGold(int earnedGold) {
		totalEarnedGold = earnedGold;
	}
	
	public int getTotalEarnedGold() {
		return totalEarnedGold;
	}

	/**
	 * @param spentGold - The amount of gold spent not on items (like flipping the barkeep a gold or bribing a guard) and on used items no longer on the character sheet.
	 */
	public void setSpentGold(int spentGold) {
		this.spentGold = spentGold;
	}
	
	public int getSpentGold() {
		return spentGold;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
		System.out.println(name + " is a " + race);
	}

	public void setSize(String characterSize) {
		this.characterSize = characterSize;
		System.out.println(name + " is base size " + characterSize);
	}
	
	public void setGender(String gender) {
		this.gender = gender;
		System.out.println(name + " is gender: " + gender);
	}

	public void setAge(String age) {
		this.age = age;
		System.out.println(name + " is age: " + age);
	}

	public void setWeight(String weight) {
		this.weight = weight;
		System.out.println(name + " weighs " + weight);
	}

	public void setHeight(String height) {
		this.height = height;
		System.out.println(name + " measures " + height);
	}

	public String getSize() {
		return characterSize;
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
		return typesAndSubtypes.toString().replace("[", "").replace("]", "");
	}

	public int getTotalLevel() {
		int total = 0;
		for (CharacterClass characterClass : classes) {
			total = total+characterClass.getLevel();
		}
		return total;
	}

	public String getClasses() {
		if (classes.size() == 1) {
			return classes.get(0).getName();
		} else {
			return classes.toString().replace("[", "").replace("]", "").replace(", ", " / ");
		}
	}

	public String getSenses() {
		return senses.toString().replace("[", "").replace("]", "");
	}

	public String getLandSpeed() {
		if (speed.size() > 0) {
			return speed.get(speed.size()-1);
		} else {
			return "30 feet";
		}
	}

	public String getClimbSpeed() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSwimSpeed() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFlySpeed() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCharacterId() {
		return characterId;
	}

	public List<String> getSpecialDefenses() {
		if (specialDefenses.isEmpty()) {
			return null;
		} else {
			return specialDefenses;
		}
	}
	
	public List<String> getSpecialOffenses() {
		if (specialOffenses.isEmpty()) {
			return null;
		} else {
			return specialOffenses;
		}
	}

	public List<Stat> getSkills() {
		ArrayList<Stat> skills = new ArrayList<>();
		
		skills.add(getStat("Acrobatics"));
		skills.add(getStat("Appraise"));
		skills.add(getStat("Bluff"));
		skills.add(getStat("Climb"));
		skills.add(getStat("Craft A"));
		skills.add(getStat("Craft B"));
		skills.add(getStat("Diplomacy"));
		skills.add(getStat("Disable Device"));
		skills.add(getStat("Disguise"));
		skills.add(getStat("Escape Artist"));
		skills.add(getStat("Fly"));
		skills.add(getStat("Handle Animal"));
		skills.add(getStat("Heal"));
		skills.add(getStat("Intimidate"));
		skills.add(getStat("Knowledge (Arcana)"));
		skills.add(getStat("Knowledge (Dungeoneering)"));
		skills.add(getStat("Knowledge (Engineering)"));
		skills.add(getStat("Knowledge (Geography)"));
		skills.add(getStat("Knowledge (History)"));
		skills.add(getStat("Knowledge (Local)"));
		skills.add(getStat("Knowledge (Nature)"));
		skills.add(getStat("Knowledge (Nobility)"));
		skills.add(getStat("Knowledge (Planes)"));
		skills.add(getStat("Knowledge (Religion)"));
		skills.add(getStat("Linguistics"));
		skills.add(getStat("Perception"));
		skills.add(getStat("Perform"));
		skills.add(getStat("Profession"));
		skills.add(getStat("Ride"));
		skills.add(getStat("Sense Motive"));
		skills.add(getStat("Sleight of Hand"));
		skills.add(getStat("Spellcraft"));
		skills.add(getStat("Stealth"));
		skills.add(getStat("Survival"));
		skills.add(getStat("Swim"));
		skills.add(getStat("UMD"));
		
		return skills;
	}

	public boolean isAdjustmentEnabled(String adjustmentName) {
		for (Adjustment adjustment : allowedAdjustments) {
			if (adjustment.getName().equals(adjustmentName)) {
				return adjustment.isEnabled();
			}
		}
		return false;
	}

	public List<Feat> getFeats() {
		return feats;
	}

	public List<ClassFeature> getClassFeatures() {
		return classFeatures;
	}

	public List<RacialTrait> getRacialTraits() {
		return racialTraits;
	}
	
	public List<TrackedResource> getMiscTrackedResources() {
		return miscTrackedResources;
	}

	public int getTotalSpentGold() {
		int itemSum = 0;
		for (Item item : items) {
			itemSum += item.getTrueCost();
		}
		return itemSum + spentGold;
	}
	
	public int getRemainingGold() {
		return getTotalEarnedGold() - getTotalSpentGold();
	}

	public Map<Weapon, WeaponStats> getWeapons() {
		return weapons.get(WeaponType.MELEE);
	}
}
