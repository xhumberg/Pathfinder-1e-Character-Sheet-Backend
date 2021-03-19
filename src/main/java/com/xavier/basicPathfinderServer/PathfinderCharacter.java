package com.xavier.basicPathfinderServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.xavier.basicPathfinderServer.Weapon.WeaponType;
import com.xavier.basicPathfinderServer.json.CharacterJson;
import com.xavier.basicPathfinderServer.json.mappers.AbilityListMapper;

public class PathfinderCharacter {
	
	public String name;
	public String imageUrl;
	public List<Ability> abilities;
	public final HashMap<String, Adjustment> adjustments;
	public final HashMap<String, Adjustment> items;
	public final HashMap<String, Stat> allStats;
	public final HashMap<Weapon.WeaponType, HashMap<Weapon, Stat>> weaponAttack;
	public final HashMap<String, Spellcasting> spellcastingByClass;
	public HP hp;
	public SkillRanks skillRanks;
	
	public PathfinderCharacter(String name, String imageUrl) {
		this.name = name;
		this.imageUrl = imageUrl;
		allStats = new HashMap<>();
		initAbilities();
		initOtherNeededStats();
		adjustments = new HashMap<>();
		items = new HashMap<>();
		weaponAttack = new HashMap<>();
		spellcastingByClass = new HashMap<>();
		hp = new HP(getAbility("Constitution"));
		skillRanks = new SkillRanks(getAbility("Intelligence"));
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
	}

	private void initAttackMods() {
		initNewStat("All Attacks");
		initNewStat("Melee Attacks");
		initNewStat("Ranged attacks");
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
			}
		}
	}
	
	public CharacterJson convertToJson() {
		return new CharacterJson(name, imageUrl, AbilityListMapper.map(abilities));
	}

	public void toggleAdjustment(String adjustmentName) {
		adjustments.get(adjustmentName).toggleAdjustment();
	}

	public void addAdjustment(Adjustment adjustment) {
		adjustments.put(adjustment.name, adjustment);
		addAdjustmentToApplicableStats(adjustment);
	}
	
	public void addItem(Adjustment adjustment) {
		items.put(adjustment.name, adjustment);
		addAdjustmentToApplicableStats(adjustment);
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
		HashMap<Weapon, Stat> weaponsOfType = weaponAttack.get(type);
		if (weaponsOfType == null) {
			weaponsOfType = new HashMap<Weapon, Stat>();
			weaponAttack.put(type, weaponsOfType);
		}
		Stat weaponStat = new Stat(weapon.getTitle());
		Adjustment weaponAttackAdjustment = new Adjustment(attackStat, true);
		weaponAttackAdjustment.addEffect(weapon.getTitle(), attackStat, getStat(attackStat));
		weaponAttackAdjustment.addEffect(weapon.getTitle(), "All Attacks", getStat("All Attacks"));
		if (type == Weapon.WeaponType.MELEE) {
			weaponAttackAdjustment.addEffect(weapon.getTitle(), "Melee Specific", getStat("Melee Attacks"));
		} else {
			weaponAttackAdjustment.addEffect(weapon.getTitle(), "Ranged Specific", getStat("Ranged Attacks"));
		}
		weaponStat.addAdjustment(weaponAttackAdjustment);
		
		weaponsOfType.put(weapon, weaponStat);
	}

	public int getMeleeAttack(String weaponTitle) {
		HashMap<Weapon, Stat> meleeWeapons = weaponAttack.get(Weapon.WeaponType.MELEE);
		
		for (Weapon weapon : meleeWeapons.keySet()) {
			if (weapon.getTitle().equals(weaponTitle)) {
				return meleeWeapons.get(weapon).getValue();
			}
		}
		
		return -100;
	}

	public void giveSpellcasting(String className, CastingType type, String castingStat) {
		Spellcasting newSpellcasting = new Spellcasting(className, type, castingStat, this);
		spellcastingByClass.put(className, newSpellcasting);
	}

	public void setSpellsPerDay(String className, int level, int basePerDay) {
		Spellcasting spellcastingStats = spellcastingByClass.get(className);
		if (spellcastingStats != null) {
			spellcastingStats.setSpellsPerDay(level, basePerDay);
		}
	}

	public int getSpellsPerDay(String className, int level) {
		Spellcasting spellcastingStats = spellcastingByClass.get(className);
		if (spellcastingStats != null) {
			return spellcastingStats.getSpellsPerDay(level);
		}
		return -1;
	}

	public void giveSpellKnown(String className, Spell spell) {
		Spellcasting spellcasting = spellcastingByClass.get(className);
		if (spellcasting != null) {
			spellcasting.addSpellKnown(spell);
		}
	}

	public void prepSpell(String className, String spellName, int level) {
		Spellcasting spellcasting = spellcastingByClass.get(className);
		if (spellcasting != null) {
			spellcasting.prepSpell(spellName, level);
		}
	}

	public Ability getAbility(String abilityName) {
		return (Ability)allStats.get(abilityName);
	}

	public Integer getSpellDC(String className, String spellName, int level) {
		Spellcasting spellcasting = spellcastingByClass.get(className);
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
	}

	public void setClassSkill(String skillName) {
		Skill skill = (Skill)getStat(skillName);
		skill.setClassSkill();
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
}
