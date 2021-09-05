package com.xavier.basicPathfinderServer.databaseLayer;

import java.util.List;
import java.util.Map;

import com.xavier.basicPathfinderServer.PathfinderCharacter;
import com.xavier.basicPathfinderServer.characterOwned.CharacterClass;
import com.xavier.basicPathfinderServer.characterOwned.ClassFeature;
import com.xavier.basicPathfinderServer.characterOwned.Feat;
import com.xavier.basicPathfinderServer.characterOwned.Item;
import com.xavier.basicPathfinderServer.characterOwned.RacialTrait;
import com.xavier.basicPathfinderServer.characterOwned.Spell;
import com.xavier.basicPathfinderServer.characterOwned.Weapon.WeaponType;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers.AllowedAdjustmentsMapper;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers.CharacterHealthInterimMapper;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers.CharacterWealthInterimMapper;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers.ClassFeatureMapper;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers.ClassMapper;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers.ClassSkillMapper;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers.EnabledAdjustmentsMapper;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers.FeatMapper;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers.ItemMapper;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers.KnownSpellMapper;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers.MiscResourceMapper;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers.PathfinderCharacterMapper;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers.RacialTraitMapper;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers.SkillRanksMapper;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers.SpellInterimMapper;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers.WeaponInterimMapper;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.interimObjects.CharacterHealthInterim;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.interimObjects.CharacterWealthInterim;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.interimObjects.SpellNameLevelAndClassInterim;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.interimObjects.WeaponInterim;
import com.xavier.basicPathfinderServer.numericals.Adjustment;
import com.xavier.basicPathfinderServer.numericals.StatName;
import com.xavier.basicPathfinderServer.numericals.TrackedResource;

public class CharacterFromDatabaseLoader {

	private final static String GET_CHARACTER_QUERY = "select * from PathfinderCharacter where CharacterID = ?";
	private final static String GET_ALLOWED_ADJUSTMENTS_QUERY = "select AllowedAdjustments.AdjustmentID, AdjustmentName, AdjustmentEffect from AllowedAdjustments inner join StandardAdjustments on AllowedAdjustments.AdjustmentID = StandardAdjustments.AdjustmentID where CharacterID = ?";
	private final static String GET_ENABLED_ADJUSTMENTS_QUERY = "select AdjustmentName from EnabledAdjustments inner join StandardAdjustments on EnabledAdjustments.AdjustmentID = StandardAdjustments.AdjustmentID where CharacterID = ?";
	private final static String GET_CLASSES_FOR_CHARACTER = "select * from AvailableClasses inner join CharacterClasses on AvailableClasses.ClassID = CharacterClasses.ClassID where CharacterClasses.CharacterID = ?";
	private final static String GET_SKILL_RANKS = "select * from SkillRanks where CharacterID = ?";
	private final static String GET_CLASS_SKILLS = "select * from ClassSkills where CharacterID = ?";
	private final static String GET_KNOWN_SPELLS = "select * from SpellsKnown inner join AvailableSpells on SpellsKnown.SpellID = AvailableSpells.SpellID where CharacterID = ?";
	private final static String GET_PREPPED_SPELLS_QUERY = "select ClassID, SpellsPrepped.SpellLevel, SpellName from SpellsPrepped inner join AvailableSpells on SpellsPrepped.SpellID = AvailableSpells.SpellID where CharacterID = ?";
	private final static String GET_SPELLS_CAST = "select SpellName, SpellsCast.SpellLevel, ClassID from SpellsCast inner join AvailableSpells on AvailableSpells.SpellID = SpellsCast.SpellID where SpellsCast.CharacterID = ?";
	private final static String GET_EQUIPMENT = "select * from CharacterEquipment inner join AvailableItems on AvailableItems.ItemID = CharacterEquipment.ItemID left join TrackedResources on TrackedResources.ResourceID = CharacterEquipment.TrackedResourceID where CharacterID = ?";
	private final static String GET_FEATS = "select * from AvailableFeats inner join CharacterFeats on AvailableFeats.FeatID = CharacterFeats.FeatID where CharacterID = ?";
	private final static String GET_CLASS_FEATURES = "select * from CharacterClassFeatures inner join AvailableClassFeatures on CharacterClassFeatures.FeatureID = AvailableClassFeatures.FeatureID left join TrackedResources on CharacterClassFeatures.TrackedResourceID = TrackedResources.ResourceID where CharacterID = ?";
	private final static String GET_MISC_TRACKED_RESOURCES = "select * from CharactersTrackedResources inner join TrackedResources on CharactersTrackedResources.TrackedResourceId = TrackedResources.ResourceID where CharacterID = ?";
	private final static String GET_CHARACTER_WEALTH = "select * from CharacterWealth where CharacterID = ?";
	private final static String GET_CHARACTER_HEALTH = "select * from CharacterHP where CharacterID = ?";
	private final static String GET_CHARACTER_RACIAL_TRAITS = "select * from RacialTraits inner join CharacterRacialTraits on RacialTraits.TraitID = CharacterRacialTraits.TraitID where CharacterID = ?";
	private final static String GET_WEAPONS = "select * from CharacterWeapons inner join WeaponDefinitions on CharacterWeapons.WeaponID = WeaponDefinitions.WeaponID where CharacterID = ?";
	
	@SuppressWarnings("unchecked")
	public static PathfinderCharacter loadCharacter(String id) {
		DatabaseAccess<Object> db = new DatabaseAccess<>();
		PathfinderCharacter character = (PathfinderCharacter)db.executeSelectQuery(new PathfinderCharacterMapper(), GET_CHARACTER_QUERY, id);
		
		if (character != null) {
			List<RacialTrait> racialTraits = (List<RacialTrait>)db.executeSelectQuery(new RacialTraitMapper(character), GET_CHARACTER_RACIAL_TRAITS, id);
			for (RacialTrait racialTrait : racialTraits) {
				character.giveRacialTrait(racialTrait);
			}
			
			List<CharacterClass> classes = (List<CharacterClass>)db.executeSelectQuery(new ClassMapper(), GET_CLASSES_FOR_CHARACTER, id);
			character.addClasses(classes);
			
			List<ClassFeature> features = (List<ClassFeature>)db.executeSelectQuery(new ClassFeatureMapper(character), GET_CLASS_FEATURES, id);
			for (ClassFeature feature : features) {
				character.giveClassFeature(feature);
			}
			
			List<String> classSkills = (List<String>)db.executeSelectQuery(new ClassSkillMapper(), GET_CLASS_SKILLS, id);
			for (String classSkill : classSkills) {
				character.setClassSkill(StatName.decode(classSkill));
			}
			
			Map<String, Integer> skillRanks = (Map<String, Integer>)db.executeSelectQuery(new SkillRanksMapper(), GET_SKILL_RANKS, id);
			for (String skill : skillRanks.keySet()) {
				character.setSkillRanks(skillRanks.get(skill), StatName.decode(skill));
			}
			
			List<Spell> knownSpells = (List<Spell>)db.executeSelectQuery(new KnownSpellMapper(), GET_KNOWN_SPELLS, id);
			for (Spell knownSpell : knownSpells) {
				character.giveSpellKnown(knownSpell.getClassId(), knownSpell);
			}
			
			List<SpellNameLevelAndClassInterim> preppedSpells = (List<SpellNameLevelAndClassInterim>)db.executeSelectQuery(new SpellInterimMapper(), GET_PREPPED_SPELLS_QUERY, id);
			for (SpellNameLevelAndClassInterim preppedSpell : preppedSpells) {
				character.prepSpell(preppedSpell.getClassId(), preppedSpell.getSpellName(), preppedSpell.getLevel());
			}
			
			List<SpellNameLevelAndClassInterim> castSpells = (List<SpellNameLevelAndClassInterim>)db.executeSelectQuery(new SpellInterimMapper(), GET_SPELLS_CAST, id);
			for (SpellNameLevelAndClassInterim castSpell : castSpells) {
				character.castSpell(castSpell.getClassId(), castSpell.getSpellName(), castSpell.getLevel());
			}
			
			List<Item> items = (List<Item>)db.executeSelectQuery(new ItemMapper(character), GET_EQUIPMENT, id);
			for (Item item : items) {
				if (item.isEquipped()) {
					character.equip(item);
				} else {
					character.giveItem(item);
				}
			}
			
			List<Feat> feats = (List<Feat>)db.executeSelectQuery(new FeatMapper(character), GET_FEATS, id);
			for (Feat feat : feats) {
				character.giveFeat(feat);
			}
			
			List<TrackedResource> miscTrackedResources = (List<TrackedResource>)db.executeSelectQuery(new MiscResourceMapper(), GET_MISC_TRACKED_RESOURCES, id);	
			for (TrackedResource resource : miscTrackedResources) {
				character.giveMiscTrackedResource(resource);
			}
			
			List<WeaponInterim> weapons = (List<WeaponInterim>)db.executeSelectQuery(new WeaponInterimMapper(), GET_WEAPONS, id);
			for (WeaponInterim interim : weapons) {
				character.giveWeapon(interim.weapon, StatName.decode(interim.attackStat), StatName.decode(interim.damageStat), WeaponType.MELEE);
			}
			
			CharacterWealthInterim characterWealth = (CharacterWealthInterim)db.executeSelectQuery(new CharacterWealthInterimMapper(), GET_CHARACTER_WEALTH, id);
			character.setTotalEarnedGold(characterWealth.getEarnedGold());
			character.setSpentGold(characterWealth.getSpentGold());
			
			CharacterHealthInterim characterHealth = (CharacterHealthInterim)db.executeSelectQuery(new CharacterHealthInterimMapper(), GET_CHARACTER_HEALTH, id);
			character.setFavoredClassBonusHP(characterHealth.getFavoredClassBonusHp());
			character.takeDamage(characterHealth.getDamageTaken());

			List<Adjustment> allowedAdjustments = (List<Adjustment>)db.executeSelectQuery(new AllowedAdjustmentsMapper(character), GET_ALLOWED_ADJUSTMENTS_QUERY, id);
			character.setAllowedAdjustments(allowedAdjustments);
			
			List<String> enabledAdjustments = (List<String>)db.executeSelectQuery(new EnabledAdjustmentsMapper(), GET_ENABLED_ADJUSTMENTS_QUERY, id);
			character.toggleAdjustments(enabledAdjustments);
			
			db.close();
			return character;
		}
		db.close();
		return new PathfinderCharacter("-1", "Error: Couldn't load character", "https://www.aautomate.com/images/easyblog_shared/November_2018/11-12-18/human_error_stop_400.png");
	}
}
