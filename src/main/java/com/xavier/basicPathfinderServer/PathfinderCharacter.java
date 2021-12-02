package com.xavier.basicPathfinderServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.xavier.basicPathfinderServer.characterOwned.CastingType;
import com.xavier.basicPathfinderServer.characterOwned.CharacterClass;
import com.xavier.basicPathfinderServer.characterOwned.ClassFeature;
import com.xavier.basicPathfinderServer.characterOwned.Feat;
import com.xavier.basicPathfinderServer.characterOwned.Item;
import com.xavier.basicPathfinderServer.characterOwned.RacialTrait;
import com.xavier.basicPathfinderServer.characterOwned.Spell;
import com.xavier.basicPathfinderServer.characterOwned.Spellcasting;
import com.xavier.basicPathfinderServer.characterOwned.Weapon;
import com.xavier.basicPathfinderServer.characterOwned.Weapon.WeaponType;
import com.xavier.basicPathfinderServer.json.CharacterJson;
import com.xavier.basicPathfinderServer.json.WeaponStats;
import com.xavier.basicPathfinderServer.numericals.Ability;
import com.xavier.basicPathfinderServer.numericals.Adjustment;
import com.xavier.basicPathfinderServer.numericals.HP;
import com.xavier.basicPathfinderServer.numericals.MultipliedStat;
import com.xavier.basicPathfinderServer.numericals.Skill;
import com.xavier.basicPathfinderServer.numericals.SkillRanks;
import com.xavier.basicPathfinderServer.numericals.Stat;
import com.xavier.basicPathfinderServer.numericals.StatName;
import com.xavier.basicPathfinderServer.numericals.TrackedResource;

public class PathfinderCharacter {
	
	public String characterId;
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
	public final HashMap<StatName, Stat> allStats;
	public final HashMap<Weapon.WeaponType, HashMap<Weapon, WeaponStats>> weapons;
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
	
	public PathfinderCharacter(String characterId, String name, String imageUrl) {
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
		hp = new HP(getAbility(StatName.CONSTITUTION));
		skillRanks = new SkillRanks(getAbility(StatName.INTELLIGENCE));
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
		abilities.add(new Ability(StatName.STRENGTH));
		abilities.add(new Ability(StatName.DEXTERITY));
		abilities.add(new Ability(StatName.CONSTITUTION));
		abilities.add(new Ability(StatName.INTELLIGENCE));
		abilities.add(new Ability(StatName.WISDOM));
		abilities.add(new Ability(StatName.CHARISMA));
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
		initNewStat(StatName.LEVEL);
		initStatWithStats(StatName.INITIATIVE, getAbility(StatName.DEXTERITY));
		
		MultipliedStat strengthAndAHalf = new MultipliedStat(StatName.STRENGTH_AND_A_HALF, getStat(StatName.STRENGTH), 1.5);
		allStats.put(StatName.STRENGTH_AND_A_HALF, strengthAndAHalf);
	}

	private void initAttackMods() {
		initNewStat(StatName.BAB);
		initNewStat(StatName.ALL_ATTACKS);
		initNewStat(StatName.MELEE_ATTACKS);
		initNewStat(StatName.RANGED_ATTACKS);
		initNewStat(StatName.ALL_DAMAGE);
		initNewStat(StatName.MELEE_DAMAGE);
		initNewStat(StatName.RANGED_DAMAGE);
		
		initNewStat(StatName.ADDITIONAL_ATTACKS_AT_FULL_BAB);
		initNewStat(StatName.ADDITIONAL_FIRST_ITERATIVES);
		initNewStat(StatName.ADDITIONAL_SECOND_ITERATIVES);
		initNewStat(StatName.ADDITIONAL_THIRD_ITERATIVES);
		
		initNewStat(StatName.PENALTY_TO_ALL_BUT_1ST_ATTACK);
	}

	private void initSaves() {
		Stat allSaves = initNewStat(StatName.ALL_SAVES);
		initStatWithStats(StatName.WILL, getAbility(StatName.WISDOM), allSaves);
		initStatWithStats(StatName.FORTITUDE, getAbility(StatName.CONSTITUTION), allSaves);
		initStatWithStats(StatName.REFLEX, getAbility(StatName.DEXTERITY), allSaves);
	}
	
	private void initAC() {
		Stat allAC = initNewStat(StatName.ALL_AC);
		initStatWithStats(StatName.AC, getAbility(StatName.DEXTERITY), allAC);
		initStatWithStats(StatName.FLAT_FOOTED, allAC);
		initStatWithStats(StatName.TOUCH, getAbility(StatName.DEXTERITY), allAC);
		setStatBase(StatName.AC, 10);
		setStatBase(StatName.FLAT_FOOTED, 10);
		setStatBase(StatName.TOUCH, 10);
	}
	
	private void initSkills() {
		Stat allSkills = initNewStat(StatName.ALL_SKILLS);
		
		initSkillWithStats(StatName.ACROBATICS, getAbility(StatName.DEXTERITY), allSkills);
		initSkillWithStats(StatName.APPRAISE, getAbility(StatName.INTELLIGENCE), allSkills);
		initSkillWithStats(StatName.BLUFF, getAbility(StatName.CHARISMA), allSkills);
		initSkillWithStats(StatName.CLIMB, getAbility(StatName.STRENGTH), allSkills);
		initSkillWithStats(StatName.CRAFT_A, getAbility(StatName.INTELLIGENCE), allSkills);
		initSkillWithStats(StatName.CRAFT_B, getAbility(StatName.INTELLIGENCE), allSkills);
		initSkillWithStats(StatName.DIPLOMACY, getAbility(StatName.CHARISMA), allSkills);
		initSkillWithStats(StatName.DISABLE_DEVICE, getAbility(StatName.DEXTERITY), allSkills);
		initSkillWithStats(StatName.DISGUISE, getAbility(StatName.CHARISMA), allSkills);
		initSkillWithStats(StatName.ESCAPE_ARTIST, getAbility(StatName.DEXTERITY), allSkills);
		initSkillWithStats(StatName.FLY, getAbility(StatName.DEXTERITY), allSkills);
		initSkillWithStats(StatName.HANDLE_ANIMAL, getAbility(StatName.CHARISMA), allSkills);
		initSkillWithStats(StatName.HEAL, getAbility(StatName.WISDOM), allSkills);
		initSkillWithStats(StatName.INTIMIDATE, getAbility(StatName.CHARISMA), allSkills);
		initSkillWithStats(StatName.KNOWLEDGE_ARCANA, getAbility(StatName.INTELLIGENCE), allSkills);
		initSkillWithStats(StatName.KNOWLEDGE_DUNGEONEERING, getAbility(StatName.INTELLIGENCE), allSkills);
		initSkillWithStats(StatName.KNOWLEDGE_ENGINEERING, getAbility(StatName.INTELLIGENCE), allSkills);
		initSkillWithStats(StatName.KNOWLEDGE_GEOGRAPHY, getAbility(StatName.INTELLIGENCE), allSkills);
		initSkillWithStats(StatName.KNOWLEDGE_HISTORY, getAbility(StatName.INTELLIGENCE), allSkills);
		initSkillWithStats(StatName.KNOWLEDGE_LOCAL, getAbility(StatName.INTELLIGENCE), allSkills);
		initSkillWithStats(StatName.KNOWLEDGE_NATURE, getAbility(StatName.INTELLIGENCE), allSkills);
		initSkillWithStats(StatName.KNOWLEDGE_NOBILITY, getAbility(StatName.INTELLIGENCE), allSkills);
		initSkillWithStats(StatName.KNOWLEDGE_PLANES, getAbility(StatName.INTELLIGENCE), allSkills);
		initSkillWithStats(StatName.KNOWLEDGE_RELIGION, getAbility(StatName.INTELLIGENCE), allSkills);
		initSkillWithStats(StatName.LINGUISTICS, getAbility(StatName.INTELLIGENCE), allSkills);
		initSkillWithStats(StatName.PERCEPTION, getAbility(StatName.WISDOM), allSkills);
		initSkillWithStats(StatName.PERFORM, getAbility(StatName.CHARISMA), allSkills);
		initSkillWithStats(StatName.PROFESSION, getAbility(StatName.WISDOM), allSkills);
		initSkillWithStats(StatName.RIDE, getAbility(StatName.DEXTERITY), allSkills);
		initSkillWithStats(StatName.SENSE_MOTIVE, getAbility(StatName.WISDOM), allSkills);
		initSkillWithStats(StatName.SLEIGHT_OF_HAND, getAbility(StatName.DEXTERITY), allSkills);
		initSkillWithStats(StatName.SPELLCRAFT, getAbility(StatName.INTELLIGENCE), allSkills);
		initSkillWithStats(StatName.STEALTH, getAbility(StatName.DEXTERITY), allSkills);
		initSkillWithStats(StatName.SURVIVAL, getAbility(StatName.WISDOM), allSkills);
		initSkillWithStats(StatName.SWIM, getAbility(StatName.STRENGTH), allSkills);
		initSkillWithStats(StatName.UMD, getAbility(StatName.CHARISMA), allSkills);
	}

	private Stat initStatWithStats(StatName statName, Stat... otherStatsToAdd) {
		Stat stat = initNewStat(statName);
		for (Stat otherStat : otherStatsToAdd) {
			stat.addStat(otherStat);
		}
		return stat;
	}

	private Stat initNewStat(StatName statName) {
		Stat newStat = new Stat(statName);
		allStats.put(statName, newStat);
		return newStat;
	}

	private Skill initSkillWithStats(StatName statName, Stat... otherStatsToAdd) {
		Skill skill = initNewSkill(statName);
		for (Stat otherStat : otherStatsToAdd) {
			skill.addStat(otherStat);
		}
		return skill;
	}

	private Skill initNewSkill(StatName statName) {
		Skill newSkill = new Skill(statName);
		allStats.put(statName, newSkill);
		return newSkill;
	}
	
	private void setStatBase(StatName statName, int newBase) {
		Stat stat = allStats.get(statName);
		stat.setBaseValue(newBase);
	}
	
	public void setAbility(StatName abilityName, int baseValue) {
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
		if (adjustment == null) {
			return;
		}
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
		for (StatName adjustedStatName : adjustment.getAdjustedStats()) {
			Stat adjustedStat = allStats.get(adjustedStatName);
			if (adjustedStat == null) {
				adjustedStat = new Stat(adjustedStatName);
				allStats.put(adjustedStatName, adjustedStat);
				System.out.println("Tried to adjust stat '" + adjustedStatName + "' which does not exist. It has been created, but the adjustment is likely to not behave as intended");
			}
			adjustedStat.addAdjustment(adjustment);
		}
	}

	public int getStatValue(StatName statName) {
		return allStats.get(statName).getValue();
	}

	private int getBaseStatValue(StatName statName) {
		return allStats.get(statName).getBase();
	}
	
	public int getAbilityValue(StatName abilityName) {
		return ((Ability)allStats.get(abilityName)).getFullValue();
	}

	public int getAbilityMod(StatName abilityName) {
		return ((Ability)allStats.get(abilityName)).getMod();
	}
	
	public Stat getStat(StatName statName) {
		return allStats.get(statName);
	}
	
	public void addStat(StatName statName) {
		allStats.put(statName, new Stat(statName));
	}

	public void giveWeapon(Weapon weapon, StatName attackStat, StatName damageStat, WeaponType type) {
		//TODO: Weapons need to be redone to use specific weapon stats that don't require the StatName enum
		HashMap<Weapon, WeaponStats> weaponsOfType = weapons.get(type);
		if (weaponsOfType == null) {
			weaponsOfType = new HashMap<Weapon, WeaponStats>();
			weapons.put(type, weaponsOfType);
		}
		
		Stat weaponAttackStat = new Stat(StatName.WEAPON_ATTACK);
		Adjustment weaponAttackAdjustment = new Adjustment(-1, attackStat.displayStrings[0], true);
		weaponAttackAdjustment.addEffect(StatName.WEAPON_ATTACK, attackStat.displayStrings[0], getStat(attackStat));
		weaponAttackAdjustment.addEffect(StatName.WEAPON_ATTACK, "All Attacks", getStat(StatName.ALL_ATTACKS));
		weaponAttackAdjustment.addEffect(StatName.WEAPON_ATTACK , "BAB", getStat(StatName.BAB));
		weaponAttackAdjustment.addEffect(StatName.WEAPON_ATTACK, "Weapon Attack Bonus", weapon.attackMod);
		if (type == Weapon.WeaponType.MELEE) {
			weaponAttackAdjustment.addEffect(StatName.WEAPON_ATTACK, "Melee Specific", getStat(StatName.MELEE_ATTACKS));
		} else {
			weaponAttackAdjustment.addEffect(StatName.WEAPON_ATTACK, "Ranged Specific", getStat(StatName.RANGED_ATTACKS));
		}
		weaponAttackStat.addAdjustment(weaponAttackAdjustment);
		
		Stat weaponDamageStat = new Stat(StatName.WEAPON_DAMAGE);
		if (damageStat != null) {
			Adjustment weaponDamageAdjustment = new Adjustment(-1, damageStat.displayStrings[0], true);
			weaponDamageAdjustment.addEffect(StatName.WEAPON_DAMAGE, damageStat.displayStrings[0], getStat(damageStat));
			weaponDamageAdjustment.addEffect(StatName.WEAPON_DAMAGE, "All Damage", getStat(StatName.ALL_DAMAGE));
			weaponDamageAdjustment.addEffect(StatName.WEAPON_DAMAGE, "Weapon Damage Bonus", weapon.damageMod);
			if (type == Weapon.WeaponType.MELEE) {
				weaponDamageAdjustment.addEffect(StatName.WEAPON_DAMAGE, "Melee Specific", getStat(StatName.MELEE_DAMAGE));
			} else {
				weaponDamageAdjustment.addEffect(StatName.WEAPON_DAMAGE, "Ranged Specific", getStat(StatName.RANGED_DAMAGE));
			}
			weaponDamageStat.addAdjustment(weaponDamageAdjustment);
		}
		
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

	public void giveSpellcasting(int classId, String name, CastingType type, int casterLevel, StatName castingStat) {
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
	
	public Spell castSpell(int classId, String spellName, int level) {
		Spellcasting spellcasting = spellcastingByClass.get(classId);
		if (spellcasting != null) {
			return spellcasting.castSpell(spellName, level);
		}
		return null;
	}
	
	public Spell uncastSpell(int classId, String spellName, int level) {
		Spellcasting spellcasting = spellcastingByClass.get(classId);
		if (spellcasting != null) {
			return spellcasting.uncastSpell(spellName, level);
		}
		return null;
	}

	public Ability getAbility(StatName statName) {
		return (Ability)allStats.get(statName);
	}

	public Integer getSpellDC(int classId, String spellName, int level) {
		Spellcasting spellcasting = spellcastingByClass.get(classId);
		if (spellcasting != null) {
			return spellcasting.getSpellDC(spellName, level);
		}
		return -1;
	}

	public void setSkillRanks(int ranks, StatName skillName) {
		Skill skill = (Skill)getStat(skillName);
		int totalRanks = skill.getSkillRanks();
		int additionalRanksToSpend = ranks-totalRanks;
		skillRanks.spendRanks(additionalRanksToSpend);
		skill.setRanks(ranks);
		
		System.out.println(name + " has " + ranks + " ranks in " + skillName);
	}

	public void setClassSkill(StatName skillName) {
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

	public int takeDamage(int damage) {
		return hp.takeDamage(damage);
	}

	public int heal(int health) {
		return hp.heal(health);
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
			if (adjustment != null)
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
		addToStat(StatName.LEVEL, characterClass.getLevel());
		addToStat(StatName.BAB, characterClass.getBab());
		addToStat(StatName.FORTITUDE, characterClass.getFort());
		addToStat(StatName.REFLEX, characterClass.getRef());
		addToStat(StatName.WILL, characterClass.getWill());
		addHitDice(characterClass.getLevel(), characterClass.getHitDice());
		addTotalSkillRanks(characterClass.getLevel(), characterClass.getBaseSkillsPerLevel());
		if (characterClass.hasSpellcasting()) {
			//TODO: Caster Level is not always class level (for example, bloodrager and paladin)
			giveSpellcasting(characterClass.getId(), characterClass.getName(), characterClass.getSpellcastingType(), characterClass.getCasterLevel(), characterClass.getSpellcastingAbility());
			for (int spellLevel : characterClass.getBaseSpellsPerDay().keySet()) {
				int baseSpells = characterClass.getBaseSpellsPerDay().get(spellLevel);
				setSpellsPerDay(characterClass.getId(), spellLevel, baseSpells);
			}
		}
	}
	
	private void addToStat(StatName statName, int amount) {
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

	public String getCharacterId() {
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
		
		skills.add(getStat(StatName.ACROBATICS));
		skills.add(getStat(StatName.APPRAISE));
		skills.add(getStat(StatName.BLUFF));
		skills.add(getStat(StatName.CLIMB));
		skills.add(getStat(StatName.CRAFT_A));
		skills.add(getStat(StatName.CRAFT_B));
		skills.add(getStat(StatName.DIPLOMACY));
		skills.add(getStat(StatName.DISABLE_DEVICE));
		skills.add(getStat(StatName.DISGUISE));
		skills.add(getStat(StatName.ESCAPE_ARTIST));
		skills.add(getStat(StatName.FLY));
		skills.add(getStat(StatName.HANDLE_ANIMAL));
		skills.add(getStat(StatName.HEAL));
		skills.add(getStat(StatName.INTIMIDATE));
		skills.add(getStat(StatName.KNOWLEDGE_ARCANA));
		skills.add(getStat(StatName.KNOWLEDGE_DUNGEONEERING));
		skills.add(getStat(StatName.KNOWLEDGE_ENGINEERING));
		skills.add(getStat(StatName.KNOWLEDGE_GEOGRAPHY));
		skills.add(getStat(StatName.KNOWLEDGE_HISTORY));
		skills.add(getStat(StatName.KNOWLEDGE_LOCAL));
		skills.add(getStat(StatName.KNOWLEDGE_NATURE));
		skills.add(getStat(StatName.KNOWLEDGE_NOBILITY));
		skills.add(getStat(StatName.KNOWLEDGE_PLANES));
		skills.add(getStat(StatName.KNOWLEDGE_RELIGION));
		skills.add(getStat(StatName.LINGUISTICS));
		skills.add(getStat(StatName.PERCEPTION));
		skills.add(getStat(StatName.PERFORM));
		skills.add(getStat(StatName.PROFESSION));
		skills.add(getStat(StatName.RIDE));
		skills.add(getStat(StatName.SENSE_MOTIVE));
		skills.add(getStat(StatName.SLEIGHT_OF_HAND));
		skills.add(getStat(StatName.SPELLCRAFT));
		skills.add(getStat(StatName.STEALTH));
		skills.add(getStat(StatName.SURVIVAL));
		skills.add(getStat(StatName.SWIM));
		skills.add(getStat(StatName.UMD));
		
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

	public int reduceUsesForItem(int resourceId) {
		for (Item item : items) {
			if (item.getTrackedResourceId() == resourceId) {
				return item.reduceTrackedResource();
			}
		}
		return -1;
	}
	
	public int reduceUsesForClassFeature(int resourceId) {
		for (ClassFeature feature : classFeatures) {
			if (feature.getTrackedResourceId() == resourceId) {
				return feature.reduceTrackedResource();
			}
		}
		return -1;
	}

	public int reduceUsesForMiscResource(int resourceId) {
		for (TrackedResource miscResource : miscTrackedResources) {
			if (miscResource.getId() == resourceId) {
				return miscResource.reduce();
			}
		}
		return -1;
	}
	
	public int increaseUsesForItem(int resourceId) {
		for (Item item : items) {
			if (item.getTrackedResourceId() == resourceId) {
				return item.increaseTrackedResource();
			}
		}
		return -1;
	}

	public int increaseUsesForClassFeature(int resourceId) {
		for (ClassFeature feature : classFeatures) {
			if (feature.getTrackedResourceId() == resourceId) {
				return feature.increaseTrackedResource();
			}
		}
		return -1;
	}

	public int increaseUsesForMiscResource(int resourceId) {
		for (TrackedResource miscResource : miscTrackedResources) {
			if (miscResource.getId() == resourceId) {
				return miscResource.increase();
			}
		}
		return -1;
	}
}
