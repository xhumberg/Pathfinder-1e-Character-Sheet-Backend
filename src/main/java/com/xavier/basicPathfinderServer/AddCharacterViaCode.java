package com.xavier.basicPathfinderServer;

import com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers.AdjustmentDatabaseModifier;
import com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers.CharacterDatabaseModifier;
import com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers.CharacterWealthDatabaseModifier;
import com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers.ClassDatabaseModifier;
import com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers.ClassFeatureDatabaseModifier;
import com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers.FeatDatabaseModifier;
import com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers.HealthDatabaseModifier;
import com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers.ItemDatabaseModifier;
import com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers.RacialTraitDatabaseModifier;
import com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers.SkillsDatabaseModifier;
import com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers.SpellDatabaseModifier;
import com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers.TrackedResourceDatabaseModifier;
import com.xavier.basicPathfinderServer.databaseLayer.databaseModifiers.WeaponsDatabaseModifier;

public class AddCharacterViaCode {

	public static void main(String[] args) {
//		String characterId = CharacterDatabaseModifier.addNewCharacter("Nemesis", "Michelle", "https://scontent-lax3-1.xx.fbcdn.net/v/t1.15752-9/211822471_516155882785208_1562427295516678306_n.jpg?_nc_cat=110&ccb=1-5&_nc_sid=ae9488&_nc_ohc=pqvFmfsqSPwAX-opBOv&_nc_oc=AQnO3vZV0skaC5Q_xo0mA-SOL_wSvfT5bC18us9jf-kFyy33Nx_avx6fI-egI-DFbyQ&_nc_ht=scontent-lax3-1.xx&oh=dad804556f327114a53e22529505a107&oe=6158B148", "CG", "Gnome Tiefling", "Small", "Female", "61", "32 lbs", "2' 9\"", 18, 14, 14, 10, 18, 6);
		
//		RacialTraitDatabaseModifier.giveRacialTraitToCharacter(0, characterId);
//		int smallId = RacialTraitDatabaseModifier.addNewRacialTrait("Size Small", "Small creatures receive a +1 bonus to AC and Attack Rolls, but a -1 to CMB and CMD", "All AC##Size##1; All Attacks##Size##1; CMB##Size##-1; CMD##Size##-1");
//		RacialTraitDatabaseModifier.giveRacialTraitToCharacter(smallId, characterId);
//		RacialTraitDatabaseModifier.giveRacialTraitToCharacter(2, characterId);
//		RacialTraitDatabaseModifier.giveRacialTraitToCharacter(3, characterId);
//		RacialTraitDatabaseModifier.giveRacialTraitToCharacter(4, characterId);
//		RacialTraitDatabaseModifier.giveRacialTraitToCharacter(5, characterId);
//		RacialTraitDatabaseModifier.giveRacialTraitToCharacter(6, characterId);
//		RacialTraitDatabaseModifier.giveRacialTraitToCharacter(7, characterId);
//		
//		int weaponId = WeaponsDatabaseModifier.addNewWeapon("+2 Small Switchscythe", "1d6", "P or S", "", 2, 2, 20, 4, "This scythe has a spring-loaded blade that folds down into the weapon’s handle.\r\n"
//				+ "\r\n"
//				+ "Opening the scythe blade is a swift action, and closing it again is a standard action. You can use Bluff or Sleight of Hand to treat a closed switchscythe as a hidden weapon; the apparatus can be disguised with 1 minute of work, which grants a +5 bonus on such checks. This disguise is ruined when the weapon is opened and must be reapplied to hide the weapon again.\r\n"
//				+ "\r\n"
//				+ "Any effects that apply to a scythe also apply to a switchscythe.", "two-handed", "exotic", "Trip", 10, "heavy blades");
//		WeaponsDatabaseModifier.giveWeaponToCharacter(weaponId, characterId, "Strength", "Strength");
//		
//		HealthDatabaseModifier.setClassBonusHP(6, characterId);
//		
//		SkillsDatabaseModifier.addClassSkill(characterId, "Bluff");
//		SkillsDatabaseModifier.addClassSkill(characterId, "Climb");
//		SkillsDatabaseModifier.addClassSkill(characterId, "Craft A");
//		SkillsDatabaseModifier.addClassSkill(characterId, "Craft B");
//		SkillsDatabaseModifier.addClassSkill(characterId, "Diplomacy");
//		SkillsDatabaseModifier.addClassSkill(characterId, "Disguise");
//		SkillsDatabaseModifier.addClassSkill(characterId, "Heal");
//		SkillsDatabaseModifier.addClassSkill(characterId, "Intimidate");
//		SkillsDatabaseModifier.addClassSkill(characterId, "Knowledge (Arcana)");
//		SkillsDatabaseModifier.addClassSkill(characterId, "Knowledge (Dungeoneering)");
//		SkillsDatabaseModifier.addClassSkill(characterId, "Knowledge (Nature)");
//		SkillsDatabaseModifier.addClassSkill(characterId, "Knowledge (Planes)");
//		SkillsDatabaseModifier.addClassSkill(characterId, "Knowledge (Religion)");
//		SkillsDatabaseModifier.addClassSkill(characterId, "Perception");
//		SkillsDatabaseModifier.addClassSkill(characterId, "Profession");
//		SkillsDatabaseModifier.addClassSkill(characterId, "Ride");
//		SkillsDatabaseModifier.addClassSkill(characterId, "Sense Motive");
//		SkillsDatabaseModifier.addClassSkill(characterId, "Spellcraft");
//		SkillsDatabaseModifier.addClassSkill(characterId, "Stealth");
//		SkillsDatabaseModifier.addClassSkill(characterId, "Survival");
//		SkillsDatabaseModifier.addClassSkill(characterId, "Swim");
//		
//		SkillsDatabaseModifier.setRanks(characterId, "Knowledge (Dungeoneering)", 6);
//		SkillsDatabaseModifier.setRanks(characterId, "Knowledge (Local)", 6);
//		SkillsDatabaseModifier.setRanks(characterId, "Knowledge (Nature)", 6);
//		SkillsDatabaseModifier.setRanks(characterId, "Knowledge (Planes)", 6);
//		SkillsDatabaseModifier.setRanks(characterId, "Knowledge (Religion)", 6);
//		SkillsDatabaseModifier.setRanks(characterId, "Perception", 6);
//		
//		CharacterWealthDatabaseModifier.setWealth(characterId, 16000, 0);
//		
//		int determinationTrackedResourceId = TrackedResourceDatabaseModifier.addNewTrackedResource("Determination", "Remaining uses of determination", 2, 2);
//		int baneTrackedResourceId = TrackedResourceDatabaseModifier.addNewTrackedResource("Bane", "Remaining rounds of Bane", 6, 6);
//		
//		int eatSin = ClassFeatureDatabaseModifier.addNewFeature("Eat Sin", "At 1st level, as a free action, when the sin eater inquisitor kills an enemy, she may eat the sins of that enemy by spending 1 minute adjacent to its corpse. This provokes attacks of opportunity. The inquisitor can rush this ritual, performing it as a full-round action that provokes attacks of opportunity, but she only gains half the normal benefit (see below).\r\n"
//				+ "\r\n"
//				+ "Eating the enemy’s sins heals the inquisitor of a number of hit points of damage equal to 1d8 + her inquisitor level (maximum +5). The enemy must have been killed by the sin eater within the last hour, and it must have had at least as many Hit Dice as half the inquisitor’s level. The inquisitor can use this ability once for each enemy she kills. This ability has no effect on mindless creatures or those with Intelligence 2 or less.\r\n"
//				+ "\r\n"
//				+ "At 5th level, the healing increases to 2d8 plus her inquisitor level (maximum +10); it increases to 3d8 + her inquisitor level (maximum +15) at 9th level and to 4d8 + her inquisitor level (maximum +20) at 13th level.\r\n"
//				+ "\r\n"
//				+ "In some faiths, this “eating” is a purely symbolic act, while in others, the inquisitor must eat a small amount of food and water as part of the ritual. A few extreme faiths actually require the inquisitor to eat some of the body of the slain enemy.\r\n"
//				+ "\r\n"
//				+ "At 8th level, when a sin eater eats the sins of a creature that would rise as an undead (such as someone slain by a shadow, spectre, or vampire), the sin eater may choose to accept 1 temporary negative level to absorb the taint in the corpse, preventing it from rising as an undead. This negative level can be removed with the appropriate magic, though it automatically expires after 24 hours, and never becomes a permanent negative level. At the GM’s discretion, this ability may prevent a ghost from using its rejuvenation ability.\r\n"
//				+ "\r\n"
//				+ "This ability replaces an inquisitor’s domain.", "");
//		int studiedTarget = ClassFeatureDatabaseModifier.addNewFeature("Studied Target", "A slayer can study an opponent he can see as a move action. The slayer then gains a +1 bonus on Bluff, Knowledge, Perception, Sense Motive, and Survival checks attempted against that opponent, and a +1 bonus on weapon attack and damage rolls against it. The DCs of slayer class abilities against that opponent increase by 1. A slayer can only maintain these bonuses against one opponent at a time; these bonuses remain in effect until either the opponent is dead or the slayer studies a new target.\r\n"
//				+ "\r\n"
//				+ "If a slayer deals sneak attack damage to a target, he can study that target as an immediate action, allowing him to apply his studied target bonuses against that target (including to the normal weapon damage roll).\r\n"
//				+ "\r\n"
//				+ "At 5th, 10th, 15th, and 20th levels, the bonuses on weapon attack rolls, damage rolls, and skill checks and to slayer DCs against a studied target increase by 1. In addition, at each such interval, the slayer is able to maintain these bonuses against an additional studied target at the same time. The slayer may discard this connection to a studied target as a free action, allowing him to study another target in its place.\r\n"
//				+ "\r\n"
//				+ "At 7th level, a slayer can study an opponent as a move or swift action.", "");
//		int monsterLore = ClassFeatureDatabaseModifier.addNewFeature("Moster Lore", "The inquisitor adds her Wisdom modifier on Knowledge skill checks in addition to her Intelligence modifier, when making skill checks to identify the abilities and weaknesses of creatures.", "");
//		int sternGaze = ClassFeatureDatabaseModifier.addNewFeature("Stern Gaze", "Inquisitors are skilled at sensing deception and intimidating their foes. An inquisitor receives a morale bonus on all Intimidate and Sense Motive checks equal to 1/2 her inquisitor level (minimum +1).", ""); //TODO: implement a half level system for adjustments
//		int cunningInitiative = ClassFeatureDatabaseModifier.addNewFeature("Cunning Initiative", "At 2nd level, an inquisitor adds her Wisdom modifier on initiative checks, in addition to her Dexterity modifier.", "Initiative##Wisdom##4"); //TODO: Make it so we can pass stats through this
//		int detectAlignment = ClassFeatureDatabaseModifier.addNewFeature("Detect Alignment", "At 2nd level, at will, an inquisitor can use detect chaos, detect evil, detect good, or detect law. She can only use one of these at any given time.", "");
//		int track = ClassFeatureDatabaseModifier.addNewFeature("Track", "At 2nd level, an inquisitor adds half her level on Survival skill checks made to follow or identify tracks.", "");
//		int determination = ClassFeatureDatabaseModifier.addNewFeature("Determination", "At 3rd level, the preacher is a person of few words on the battlefield, but those words hold great power and authority. Once per day, the inquisitor can use this ability to create one of the following effects. Each is a free action to use.\r\n"
//				+ "\r\n"
//				+ "Aggression: The preacher may reroll an attack roll that she just made before the results of the roll are revealed. She must take the result of the reroll, even if it’s worse than the original roll.\r\n"
//				+ "\r\n"
//				+ "Defense: When the inquisitor would be hit by a melee or ranged attack, as an immediate action she may add a +4 insight bonus to her Armor Class against that attack, and if this makes the inquisitor’s AC higher than the opponent’s attack roll, the attack misses.\r\n"
//				+ "\r\n"
//				+ "Warning: When a preacher’s ally within line of sight would be hit by a melee or ranged attack, she may call out a warning to that ally, and the attacker must reroll the attack and use the results of the second roll. The ally must be able to hear the preacher and must not be helpless for this ability to have any effect.\r\n"
//				+ "\r\n"
//				+ "Whenever the preacher could select a bonus teamwork feat (at 3rd, 6th, 9th, 12th, 15th, and 18th level), she can instead choose to increase her number of uses per day of this ability by one.\r\n"
//				+ "\r\n"
//				+ "This ability replaces solo tactics.", "");
//		int sneakAttack = ClassFeatureDatabaseModifier.addNewFeature("Sneak Attack 2d6", "This character deals an additional 2d6 damage to flat footed creatures or creatures they are flanking", "[Special Offense:+2d6 Sneak Attack]");
//		int bane = ClassFeatureDatabaseModifier.addNewFeature("Bane", "At 5th level, an inquisitor can imbue one of her weapons with the bane weapon special ability as a swift action. She must select one creature type when she uses this ability (and a subtype if the creature type selected is humanoid or outsider). Once selected, the type can be changed as a swift action. This ability only functions while the inquisitor wields the weapon. If dropped or taken, the weapon resumes granting this ability if it is returned to the inquisitor before the duration expires. This ability lasts for a number of rounds per day equal to the inquisitor’s level. These rounds do not need to be consecutive.", "");
//		int discernLies = ClassFeatureDatabaseModifier.addNewFeature("Discern Lies", "At 5th level, an inquisitor can discern lies, as per the spell, for a number of rounds per day equal to her inquisitor level. These rounds do not need to be consecutive. Activating this ability is an immediate action.", "");
//		int speakWithDead = ClassFeatureDatabaseModifier.addNewFeature("Speak With Dead", "At 6th level, when the inquisitor eats an enemy’s sins, within 10 minutes of doing so, she can ask the remnants of the enemy’s soul questions as if using speak with dead, with a caster level equal to her inquisitor level. She does not need the enemy’s corpse to use this ability (she can eat sin, move away from the corpse, then use speak with dead), though the soul gets a saving throw just as a corpse would.\r\n"
//				+ "\r\n"
//				+ "This ability replaces the bonus teamwork feat gained at 6th level.", "");
//		ClassFeatureDatabaseModifier.giveFeatureToCharacter(eatSin, characterId, -1);
//		ClassFeatureDatabaseModifier.giveFeatureToCharacter(studiedTarget, characterId, -1);
//		ClassFeatureDatabaseModifier.giveFeatureToCharacter(monsterLore, characterId, -1);
//		ClassFeatureDatabaseModifier.giveFeatureToCharacter(sternGaze, characterId, -1);
//		ClassFeatureDatabaseModifier.giveFeatureToCharacter(cunningInitiative, characterId, -1);
//		ClassFeatureDatabaseModifier.giveFeatureToCharacter(detectAlignment, characterId, -1);
//		ClassFeatureDatabaseModifier.giveFeatureToCharacter(track, characterId, -1);
//		ClassFeatureDatabaseModifier.giveFeatureToCharacter(determination, characterId, determinationTrackedResourceId);
//		ClassFeatureDatabaseModifier.giveFeatureToCharacter(sneakAttack, characterId, -1);
//		ClassFeatureDatabaseModifier.giveFeatureToCharacter(bane, characterId, baneTrackedResourceId);
//		ClassFeatureDatabaseModifier.giveFeatureToCharacter(discernLies, characterId, -1);
//		ClassFeatureDatabaseModifier.giveFeatureToCharacter(speakWithDead, characterId, -1);
//		
//		int inquisitor6 = ClassDatabaseModifier.addNewClass(6, 4, 5, 2, 5, 6, 8, "Inquisitor 6", true, "Spontaneous", 6, "Wisdom", "0-6 1-4 2-3", "0-6 1-4 2-4");
//		ClassDatabaseModifier.giveClassToCharacter(inquisitor6, characterId);
//		
//		int heavyArmorProf = FeatDatabaseModifier.addNewFeat("Heavy Armor Proficiency", "You are skilled at wearing heavy armor.", "");
//		int furiousFocus = FeatDatabaseModifier.addNewFeat("Furious Focus", "When you are wielding a two-handed weapon or a one-handed weapon with two hands, and using the Power Attack feat, you do not suffer Power Attack’s penalty on melee attack rolls on the first attack you make each turn. You still suffer the penalty on any additional attacks, including attacks of opportunity.", "");
//		int accomplishedSneakAttacker = FeatDatabaseModifier.addNewFeat("Accomplished Sneak Attacker", "Your sneak attack damage increases by 1d6. Your number of sneak attack dice cannot exceed half your character level (rounded up).", "");
//		FeatDatabaseModifier.giveFeatToCharacter(heavyArmorProf, characterId);
//		FeatDatabaseModifier.giveFeatToCharacter(furiousFocus, characterId);
//		FeatDatabaseModifier.giveFeatToCharacter(accomplishedSneakAttacker, characterId);
//		
//		int scytheItem = ItemDatabaseModifier.addNewItem("+2 Small Switchscythe", 8317, "Weapon", "This scythe has a spring-loaded blade that folds down into the weapon’s handle.\r\n"
//				+ "\r\n"
//				+ "Opening the scythe blade is a swift action, and closing it again is a standard action. You can use Bluff or Sleight of Hand to treat a closed switchscythe as a hidden weapon; the apparatus can be disguised with 1 minute of work, which grants a +5 bonus on such checks. This disguise is ruined when the weapon is opened and must be reapplied to hide the weapon again.\r\n"
//				+ "\r\n"
//				+ "Any effects that apply to a scythe also apply to a switchscythe.", "");
//		int oyoroi1 = ItemDatabaseModifier.addNewItem("+1 Small O-yoroi", 3000, "Armor", "Worn almost exclusively by highranking samurai, o-yoroi—or “great armor”—is a heavy combat armor that consists of various supplementary components that include both plate and lamellar elements. Each suit is crafted for a specific individual and displays the owner’s aesthetic. Upon completion, the suit is colored and sealed with a final lacquer finish. The centerpiece of o-yoroi is a cuirass consisting of two parts—a separate reinforcement for the right side called a waidate, and a kikko cuirass. The upper part of the waidate consists of a leather-covered iron plate. The cuirass’s leather shoulder straps—called watagami—are likewise armored with metal plates. Affixed to the cuirass are a number of supplementary pieces, including wide lamellar shoulder guards, a kikko sleeve for the shield arm, lacquered iron greaves worn over padded silk leggings, and a groin protector. The signature component of each suit of armor is the tiered kabuto helmet and its accompanying ho-ate mask. Ho-ate masks can be made of hardened leather or metal and are fashioned into fearsome visages such as oni, dragons, or other mythical beings.", "AC##Armor##9; Flat-Footed##Armor##9");
//		int cloakOfResistance1 = 10;
//		int beltOfGiantStrength = 16;
//		ItemDatabaseModifier.giveItemToCharacter(scytheItem, characterId, -1, 5317, true);
//		ItemDatabaseModifier.giveItemToCharacter(oyoroi1, characterId, -1, 3000, true);
//		ItemDatabaseModifier.giveItemToCharacter(cloakOfResistance1, characterId, -1, 1000, true);
//		ItemDatabaseModifier.giveItemToCharacter(beltOfGiantStrength, characterId, -1, 2000, true);
//
//		String characterId = "kUC8d6ZgDu";
//		int classId = inquisitor6;
//		
//		SpellDatabaseModifier.addSpellPrepped(characterId, 2, classId, 0);
//		SpellDatabaseModifier.addSpellPrepped(characterId, 103, classId, 0);
//		SpellDatabaseModifier.addSpellPrepped(characterId, 137, classId, 0);
//		SpellDatabaseModifier.addSpellPrepped(characterId, 162, classId, 0);
//		SpellDatabaseModifier.addSpellPrepped(characterId, 249, classId, 0);
//		SpellDatabaseModifier.addSpellPrepped(characterId, 514, classId, 0);
//		SpellDatabaseModifier.addSpellPrepped(characterId, 80, classId, 1);
//		SpellDatabaseModifier.addSpellPrepped(characterId, 1013, classId, 1);
//		SpellDatabaseModifier.addSpellPrepped(characterId, 1724, classId, 1);
//		SpellDatabaseModifier.addSpellPrepped(characterId, 480, classId, 1);
//		SpellDatabaseModifier.addSpellPrepped(characterId, 2498, classId, 2);
//		SpellDatabaseModifier.addSpellPrepped(characterId, 267, classId, 2);
//		SpellDatabaseModifier.addSpellPrepped(characterId, 297, classId, 2);
//		SpellDatabaseModifier.addSpellPrepped(characterId, 513, classId, 2);
		
//		AdjustmentDatabaseModifier.allowCharacterToUseAdjustment(0, characterId);
//		AdjustmentDatabaseModifier.allowCharacterToUseAdjustment(1, characterId);
//		AdjustmentDatabaseModifier.allowCharacterToUseAdjustment(19, characterId);
//		AdjustmentDatabaseModifier.allowCharacterToUseAdjustment(20, characterId);
//		int studiedTarget2 = AdjustmentDatabaseModifier.addNewAdjustment("Studied Target 2", "All Attacks##Studied Target##2; All Damage##Studied Target##2");
//		int bane = AdjustmentDatabaseModifier.addNewAdjustment("Bane", "[Special Offense: +2d6 damage on target of bane] All Attacks##Bane##2; All Damage##Bane##2");
//		AdjustmentDatabaseModifier.allowCharacterToUseAdjustment(studiedTarget2, characterId);
//		AdjustmentDatabaseModifier.allowCharacterToUseAdjustment(bane, characterId);
//		int furiousFocus2 = AdjustmentDatabaseModifier.addNewAdjustment("Furious Focus 2", "All Damage##Power Attack##6");
//		AdjustmentDatabaseModifier.allowCharacterToUseAdjustment(furiousFocus2, characterId);
//		int shieldOfFaith3 = AdjustmentDatabaseModifier.addNewAdjustment("Shield of Faith 3", "All AC##Deflection##3");
//		AdjustmentDatabaseModifier.allowCharacterToUseAdjustment(shieldOfFaith3, characterId);
//		AdjustmentDatabaseModifier.removeAllowCharacterToUseAdjustment(19, characterId);
		
		String manu = "eVbBMI8yjs";
//		int sixthWing7 = ClassDatabaseModifier.addNewClass(7, 5, 5, 2, 5, 2, 8, "Sixth Wing Bulwark 7", true, "PREPARED", 7, "Wisdom", "0-5 1-4 2-3 3-1", null);
//		ClassDatabaseModifier.takeClassFromCharacter(7, manu);
//		ClassDatabaseModifier.giveClassToCharacter(sixthWing7, manu);
//		SpellDatabaseModifier.migrateSpellsFromClassToClass(manu, 7, sixthWing7);
//		SpellDatabaseModifier.addSpellPrepped(manu, 1667, 8, 3);
//		SpellDatabaseModifier.addSpellKnown(manu, 1667, 8);
//		SpellDatabaseModifier.
//		int channelVigor = AdjustmentDatabaseModifier.addNewAdjustment("Channel Vigor, Legs", "AC##Dodge##1; Touch##Dodge##1; Reflex##Dodge##1; All Attacks##Haste##1");
//		AdjustmentDatabaseModifier.allowCharacterToUseAdjustment(channelVigor, manu);
//		int sacredArmorAdjustment = AdjustmentDatabaseModifier.addNewAdjustment("Sacred Armor", "AC##Stacking Enhancement to AC##1; Flat-Footed##Stacking Enhancement to AC");
//		AdjustmentDatabaseModifier.allowCharacterToUseAdjustment(sacredArmorAdjustment, manu);
////		int boroBead2Resource = TrackedResourceDatabaseModifier.addNewTrackedResource("Boro Bead L2", "Boro Beads are only usable once per day", 1, 1);
////		int boroBead2 = ItemDatabaseModifier.addNewItem("Boro Bead L2", 4000, "None", "This multicolored, sturdy glass bead is an aid to members of the alchemist class. Once per day on command, a boro bead enables the bearer to recharge any one extract that he had mixed and then consumed that day. The extract is then reconstituted and usable again, just as if it had not been drank. The extract must be of a particular level, depending on the bead. Different beads exist for recalling one extract per day of each level from 1st through 6th. A bead works on an infusion, but not a potion, elixir, bomb, mutagen, or non-magical alchemical material such as antitoxin.", "");
////		ItemDatabaseModifier.giveItemToCharacter(boroBead2, manu, boroBead2Resource, 2000, false);
////		int barkskin3 = AdjustmentDatabaseModifier.addNewAdjustment("Barkskin 3", "AC##Enhancement to Natural Armor##3; Flat-Footed##Enhancement to Natural Armor##3");
////		AdjustmentDatabaseModifier.allowCharacterToUseAdjustment(barkskin3, manu);
////		int BastardSword1 = WeaponsDatabaseModifier.addNewWeapon("+1 Bastard Sword", "1d10", "S", "", 1, 1, 19, 20, "A bastard sword is about 4 feet in length, making it too large to use in one hand without special training; thus, it is an exotic weapon. A character can use a bastard sword two-handed as a martial weapon.", "Melee", "Exotic", "", 6, "heavy blades");
////		int BastardSword1item = ItemDatabaseModifier.addNewItem("+1 Bastard Sword", 2335, "Weapon", "A bastard sword is about 4 feet in length, making it too large to use in one hand without special training; thus, it is an exotic weapon. A character can use a bastard sword two-handed as a martial weapon.", "");
////		WeaponsDatabaseModifier.giveWeaponToCharacter(BastardSword1, manu, "Strength", "Strength");
////		WeaponsDatabaseModifier.takeWeaponFromCharacter(1, manu);
////		ItemDatabaseModifier.giveItemToCharacter(2, manu, -1, 2000, true);
////		ItemDatabaseModifier.takeItemFromCharacter(13, manu);
//		HealthDatabaseModifier.setClassBonusHP(7, manu);
//		
//		int trackedSacredArmor = TrackedResourceDatabaseModifier.addNewTrackedResource("Sacred Armor", "Uses of Sacred Armor", 7, 7);
//		int sacredArmor = ClassFeatureDatabaseModifier.addNewFeature("Sacred Armor", "At 7th level, the warpriest gains the ability to enhance his armor with divine power as a swift action. This power grants the armor a +1 enhancement bonus. For every 3 levels beyond 7th, this bonus increases by 1 (to a maximum of +5 at 19th level). The warpriest can use this ability a number of minutes per day equal to his warpriest level. This duration must be used in 1-minute increments, but they don’t need to be consecutive.\r\n"
//				+ "\r\n"
//				+ "These bonuses stack with any existing bonuses the armor might have, to a maximum of +5. The warpriest can enhance armor any of the following armor special abilities: energy resistance (normal, improved, and greater), fortification (heavy, light, or moderate), glamered, and spell resistance (13, 15, 17, and 19). Adding any of these special abilities replaces an amount of bonus equal to the special ability’s base cost. For this purpose, glamered counts as a +1 bonus, energy resistance counts as +2, improved energy resistance counts as +4, and greater energy resistance counts as +5. Duplicate abilities do not stack. The armor must have at least a +1 enhancement bonus before any other special abilities can be added.\r\n"
//				+ "\r\n"
//				+ "The enhancement bonus and armor special abilities are determined the first time the ability is used each day and cannot be changed until the next day. These bonuses apply only while the warpriest is wearing the armor, and end immediately if the armor is removed or leaves the warpriest’s possession. This ability can be ended as a free action at the start of the warpriest’s turn. This ability cannot be applied to a shield.\r\n"
//				+ "\r\n"
//				+ "When the warpriest uses this ability, he can also use his sacred weapon ability as a free action by expending one use of his fervor.", null);
//		ClassFeatureDatabaseModifier.giveFeatureToCharacter(sacredArmor, manu, trackedSacredArmor);
		SkillsDatabaseModifier.setRanks(manu, "Profession", 7);
		SkillsDatabaseModifier.setRanks(manu, "Knowledge (Local)", 7);
		SkillsDatabaseModifier.setRanks(manu, "Sense Motive", 7);
		SkillsDatabaseModifier.setRanks(manu, "Swim", 4);
		
	}

}
