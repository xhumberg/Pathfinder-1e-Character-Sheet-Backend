package com.xavier.spellScraper;

public class SpellScraper {

	public static final String scrape = "PFS Legal Hungry Pit\r\n" + 
			"Source Advanced Player's Guide pg. 228\r\n" + 
			"School conjuration (creation); Level arcanist 5, sorcerer 5, summoner 5, summoner (unchained) 5, wizard 5\r\n" + 
			"Casting\r\n" + 
			"Casting Time 1 standard action\r\n" + 
			"Components V, S, F (miniature shovel costing 10 gp)\r\n" + 
			"Effect\r\n" + 
			"Range medium (100 ft. + 10 ft./level)\r\n" + 
			"Effect 10-ft.-by-10-ft. hole, 10 ft. deep/2 levels\r\n" + 
			"Duration 1 round + 1 round/level\r\n" + 
			"Saving Throw Reflex negates, Reflex half, see text; Spell Resistance no\r\n" + 
			"Description\r\n" + 
			"This spell functions as create pit, except that the pit has the ability to squeeze and crush any creature trapped within it and has a maximum depth of 100 feet. Creatures who fall into the hole take falling damage as normal. In addition, anyone within the pit, not just those on the bottom, takes 4d6 points of bludgeoning damage each round as the pit contracts and then returns to its normal size (a successful Reflex save halves this damage). The ever-shifting walls of the pit are quite difficult to scale and have a Climb DC of 35."
			;
			
			
			
			
			
			
	public static final int spellID = 14;
	
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
