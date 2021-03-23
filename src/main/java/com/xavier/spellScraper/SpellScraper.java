package com.xavier.spellScraper;

public class SpellScraper {

	public static final String scrape =
			"PFS Legal Telekinetic Charge\r\n" + 
			"Source Ultimate Combat pg. 247\r\n" + 
			"School evocation [force]; Level arcanist 4, bloodrager 4, psychic 4, sorcerer 4, spiritualist 4, wizard 4\r\n" + 
			"Casting\r\n" + 
			"Casting Time 1 standard action\r\n" + 
			"Components V, S\r\n" + 
			"Effect\r\n" + 
			"Range close (25 ft. + 5 ft./2 levels)\r\n" + 
			"Target one willing creature\r\n" + 
			"Duration instantaneous\r\n" + 
			"Saving Throw Will negates (harmless); Spell Resistance yes (harmless)\r\n" + 
			"Description\r\n" + 
			"You telekinetically launch an ally across the battlefield to anywhere within this spell’s range. While moving, your ally is flying just above the ground unless you wish otherwise. Movement from this spell provokes attacks of opportunity as normal, although you can lift your ally over objects or out of enemy reach, as long as your ally remains within this spell’s range. If your ally lands adjacent to an opponent, he can spend an immediate action to make a melee attack against that opponent with a +2 bonus on the attack roll."
			;
			
			
			
			
			
			
	public static final int spellID = 13;
	
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
