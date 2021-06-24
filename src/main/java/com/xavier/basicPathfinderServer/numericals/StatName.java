package com.xavier.basicPathfinderServer.numericals;

public enum StatName {
	//Abilities
	STRENGTH("Strength", "Str"),
	DEXTERITY("Dexterity", "Dex"),
	CONSTITUTION("Constitution", "Con"),
	INTELLIGENCE("Intelligence", "Int"),
	WISDOM("Wisdom", "Wis"),
	CHARISMA("Charisma", "Cha"),
	
	//Skills
	ACROBATICS("Acrobatics"),
	APPRAISE("Appraise"),
	BLUFF("Bluff"),
	CLIMB("Climb"),
	CRAFT_A("Craft A", "Craft", "Craft 1"),
	CRAFT_B("Craft B", "Craft 2"),
	DIPLOMACY("Diplomacy"),
	DISABLE_DEVICE("Disable Device"),
	DISGUISE("Disguise"),
	ESCAPE_ARTIST("Escape Artist"),
	FLY("Fly"),
	HANDLE_ANIMAL("Handle Animal"),
	HEAL("Heal"),
	INTIMIDATE("Intimidate"),
	KNOWLEDGE_ARCANA("Knowledge (Arcana)", "Kn Arcana"),
	KNOWLEDGE_DUNGEONEERING("Knowledge (Dungeoneering)", "Kn Dungeoneering"),
	KNOWLEDGE_ENGINEERING("Knowledge (Engineering)", "Kn Engineering"),
	KNOWLEDGE_GEOGRAPHY("Knowledge (Geography)", "Kn Geography"),
	KNOWLEDGE_HISTORY("Knowledge (History)", "Kn History"),
	KNOWLEDGE_LOCAL("Knowledge (Local)", "Kn Local"),
	KNOWLEDGE_NATURE("Knowledge (Nature)", "Kn Nature"),
	KNOWLEDGE_NOBILITY("Knowledge (Nobility)", "Kn Nobility"),
	KNOWLEDGE_PLANES("Knowledge (Planes)", "Kn Planes"),
	KNOWLEDGE_RELIGION("Knowledge (Religion)", "Kn Religion"),
	LINGUISTICS("Linguistics"),
	PERCEPTION("Perception"),
	PERFORM("Perform"),
	PROFESSION("Profession"),
	RIDE("Ride"),
	SENSE_MOTIVE("Sense Motive"),
	SLEIGHT_OF_HAND("Sleight of Hand"),
	SPELLCRAFT("Spellcraft"),
	STEALTH("Stealth"),
	SURVIVAL("Survival"),
	SWIM("Swim"),
	UMD("UMD", "Use Magic Device"),
	
	//Schools of Magic
	ABJURATION("Abjuration"),
	CONJURATION("Conjuration"),
	DIVINATION("Divination"),
	ENCHANTMENT("Enchantment"),
	EVOCATION("Evocation"),
	ILLUSION("Illusion"),
	NECROMANCY("Necromancy"),
	TRANSMUTATION("Transmutation"),
	
	//Misc Stats
	ALL_SKILLS("All Skills", "Skills"),
	LEVEL("Level"),
	INITIATIVE("Initiative", "Init"),
	BAB("BAB", "Base Attack Bonus"),
	ALL_ATTACKS("All Attacks", "All Attack", "Attacks", "Attack Rolls"),
	MELEE_ATTACKS("Melee Attacks", "Melee Attack", "Melee Attack Rolls"),
	RANGED_ATTACKS("Ranged Attacks", "Ranged Attack", "Ranged Attack Rolls"),
	ALL_DAMAGE("All Damage", "Damage"),
	MELEE_DAMAGE("Melee Damage", "Melee Damage Rolls"),
	RANGED_DAMAGE("Ranged Damage", "Ranged Damage Rolls"),
	ALL_SAVES("All Saves", "Saves"),
	WILL("Will Save", "Will"),
	FORTITUDE("Fortitude Save", "Fortitude", "Fort"),
	REFLEX("Reflex Save", "Reflex", "Ref"),
	ALL_AC("All AC"),
	AC("AC"),
	FLAT_FOOTED("Flat-Footed"),
	TOUCH("Touch", "Touch AC"),
	
	//Stats not added to character map
	SPELL_DC("Spell DC", "DC"),
	WEAPON_ATTACK("Weapon Attack"),
	WEAPON_DAMAGE("Weapon Damage");
	
	public String[] displayStrings;
	
	private StatName(String...displayStrings) {
		this.displayStrings = displayStrings;
	}
	
	public static StatName decode(String displayString) {
		if (displayString.isBlank()) {
			return null;
		}
		
		for (StatName statName : StatName.values()) {
			for (String statDisplayString : statName.displayStrings) {
				if (statDisplayString.toUpperCase().equals(displayString.toUpperCase())) {
					return statName;
				}
			}
			
		}
		
		throw new IllegalArgumentException("Invalid Stat Name: " + displayString);
	}
}
