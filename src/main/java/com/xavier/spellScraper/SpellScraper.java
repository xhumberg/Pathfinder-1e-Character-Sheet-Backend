package com.xavier.spellScraper;

public class SpellScraper {

	public static final String scrape = "PFS Legal Telekinesis\r\n" + 
			"Source PRPG Core Rulebook pg. 357\r\n" + 
			"School transmutation; Level arcanist 5, magus 5, occultist 5, psychic 4, sorcerer 5, spiritualist 5, wizard 5\r\n" + 
			"Casting\r\n" + 
			"Casting Time 1 standard action\r\n" + 
			"Components V, S\r\n" + 
			"Effect\r\n" + 
			"Range long (400 ft. + 40 ft./level)\r\n" + 
			"Target or Targets see text\r\n" + 
			"Duration concentration (up to 1 round/level) or instantaneous; see text\r\n" + 
			"Saving Throw Will negates (object) or none; see text; Spell Resistance yes (object); see text\r\n" + 
			"Description\r\n" + 
			"You move objects or creatures by concentrating on them. Depending on the version selected, the spell can provide a gentle, sustained force, perform a variety of combat maneuvers, or exert a single short, violent thrust.\r\n" + 
			"\r\n" + 
			"Sustained Force: A sustained force moves an object weighing no more than 25 pounds per caster level (maximum 375 pounds at 15th level) up to 20 feet per round. A creature can negate the effect on an object it possesses with a successful Will save or with spell resistance.\r\n" + 
			"\r\n" + 
			"This version of the spell can last 1 round per caster level, but it ends if you cease concentration. The weight can be moved vertically, horizontally, or in both directions. An object cannot be moved beyond your range. The spell ends if the object is forced beyond the range. If you cease concentration for any reason, the object falls or stops.\r\n" + 
			"\r\n" + 
			"An object can be telekinetically manipulated as if with one hand. For example, a lever or rope can be pulled, a key can be turned, an object rotated, and so on, if the force required is within the weight limitation. You might even be able to untie simple knots, though delicate activities such as these require DC 15 Intelligence checks.\r\n" + 
			"\r\n" + 
			"Combat Maneuver: Alternatively, once per round, you can use telekinesis to perform a bull rush, disarm, grapple (including pin), or trip. Resolve these attempts as normal, except that they don't provoke attacks of opportunity, you use your caster level in place of your Combat Maneuver Bonus, and you add your Intelligence modifier (if a wizard) or Charisma modifier (if a sorcerer) in place of your Strength or Dexterity modifier. No save is allowed against these attempts, but spell resistance applies normally. This version of the spell can last 1 round per caster level, but it ends if you cease concentration.\r\n" + 
			"\r\n" + 
			"Violent Thrust: Alternatively, the spell energy can be spent in a single round. You can hurl one object or creature per caster level (maximum 15) that are within range and all within 10 feet of each other toward any target within 10 feet per level of all the objects. You can hurl up to a total weight of 25 pounds per caster level (maximum 375 pounds at 15th level).\r\n" + 
			"\r\n" + 
			"You must succeed on attack rolls (one per creature or object thrown) to hit the target with the items, using your base attack bonus + your Intelligence modifier (if a wizard) or Charisma modifier (if a sorcerer). Weapons cause standard damage (with no Strength bonus; note that arrows or bolts deal damage as daggers of their size when used in this manner). Other objects cause damage ranging from 1 point per 25 pounds (for less dangerous objects) to 1d6 points of damage per 25 pounds (for hard, dense objects). Objects and creatures that miss their target land in a square adjacent to the target.\r\n" + 
			"\r\n" + 
			"Creatures who fall within the weight capacity of the spell can be hurled, but they are allowed Will saves (and spell resistance) to negate the effect, as are those whose held possessions are targeted by the spell.\r\n" + 
			"\r\n" + 
			"If a telekinesed creature is hurled against a solid surface, it takes damage as if it had fallen 10 feet (1d6 points).";
	
	
	
	
	
			
			
			
			
	public static final int spellID = 19;
	
	public static final String SEP = "', '";
	
	public static void main(String[] args) {
		String[] ScrapedSpell = scrape.split("\r\n");
		if (ScrapedSpell.length < 13) {
			System.out.println("Something weird happened.");
		}
		StringBuilder spellInsert = new StringBuilder("(");
		spellInsert.append(spellID);
		spellInsert.append(", '");
		String[] schoolAndStuff = ScrapedSpell[2].split("; Level ");
		spellInsert.append(schoolAndStuff[1]);
		spellInsert.append(SEP);
		if (ScrapedSpell[0].contains("PFS Legal")) {
			spellInsert.append(ScrapedSpell[0].replace("PFS Legal ", ""));
		} else {
			System.out.println("Not PFS legal. Need to implement something new");
		}
		String[] schoolAndTags = schoolAndStuff[0].split(" ");
		spellInsert.append(SEP);
		spellInsert.append(capitalize(schoolAndTags[1]));
		spellInsert.append(SEP);
		String[] tags = new String[schoolAndTags.length-2];
		for (int i = 2; i < schoolAndTags.length; i++) {
			tags[i-2] = schoolAndTags[i].replace("[", "").replace("]", "").replace("(", "").replace(")", "");
		}
		if (tags.length > 0) {
			for (int i = 0; i < tags.length-1; i++) {
				spellInsert.append(tags[i] + ", ");
			}
			spellInsert.append(tags[tags.length-1]);
		}
		spellInsert.append(SEP);
		spellInsert.append(ScrapedSpell[4].replace("Casting Time ", ""));
		spellInsert.append(SEP);
		spellInsert.append(ScrapedSpell[5].replace("Components ", ""));
		spellInsert.append(SEP);
		spellInsert.append(ScrapedSpell[7].replace("Range ", ""));
		spellInsert.append(SEP);
		spellInsert.append(ScrapedSpell[8].replace("Target ", "").replace("Effect ", ""));
		spellInsert.append(SEP);
		spellInsert.append(ScrapedSpell[9].replace("Duration ", ""));
		spellInsert.append(SEP);
		int i = 12;
		if (ScrapedSpell[10].contains("Spell Resistance")) {
			String[] saveAndResist = ScrapedSpell[10].split(";");
			spellInsert.append(saveAndResist[0].replace("Saving Throw ", ""));
			spellInsert.append(SEP);
			spellInsert.append(saveAndResist[1].replace(" Spell Resistance ", ""));
			spellInsert.append(SEP);
		} else {
			spellInsert.append("none" + SEP + "no" + SEP);
			i--;
		}
		for (; i < ScrapedSpell.length-1; i++) {
			spellInsert.append(ScrapedSpell[i].replace("'", "''")+"\\r\\n");
		}
		spellInsert.append(ScrapedSpell[i].replace("'", "''"));
		spellInsert.append("')");
		System.out.println(spellInsert.toString());
	}
	
	//From https://attacomsian.com/blog/capitalize-first-letter-of-string-java
	public static String capitalize(String str) {
	    if(str == null || str.isEmpty()) {
	        return str;
	    }

	    return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

}
