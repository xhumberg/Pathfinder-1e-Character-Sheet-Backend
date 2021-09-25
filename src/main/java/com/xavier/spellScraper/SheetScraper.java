package com.xavier.spellScraper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.xavier.basicPathfinderServer.databaseLayer.DatabaseAccess;

public class SheetScraper {
	public static void main(String[] args) {
		BufferedReader reader = null;
		DatabaseAccess<Object> dbAccess = new DatabaseAccess<Object>();
		try {
			reader = new BufferedReader(new FileReader(new File("D:\\Users\\Owner\\Downloads\\spell_full - Updated 31Mar2020 - spell_full - Updated 31Mar2020.tsv")));
			
			System.out.println(reader.readLine());
			
			Integer totalSpells = 0;
			String spellLine = reader.readLine();
			long longestDescriptionLength = 0;
			String longestDescriptionSpell = "Error";
			do {
				String[] info = spellLine.split("\t");
				if (info.length != 93) {
					System.out.println(info.length);
				}
				
				String levels = info[4];
				String name = info[0];
				String school = info[1];
				String tags = formatTags(info[2], info[3]);
				String castingTime = info[5];
				String components = info[6];
				String range = info[8];
				String target = formatTarget(info[9], info[10], info[11]);
				
				if (target.equals("WOAH!")) {
					System.out.println(name);
				}
				String duration = info[12];
				String savingThrow = info[15];
				String spellResistance = info[16];
				String description = info[17];
				
				System.out.println("INSERT INTO AvailableSpells VALUES " + format(totalSpells, levels, name, school, tags, castingTime, components, range, target, duration, savingThrow, spellResistance, description));
				
				if (longestDescriptionLength < description.length()) {
					longestDescriptionLength = description.length();
					longestDescriptionSpell = name;
				}
				longestDescriptionLength = Math.max(longestDescriptionLength, description.length());
				
				dbAccess.executeModifyQuery("INSERT INTO AvailableSpells VALUES " + format(totalSpells, levels, name, school, tags, castingTime, components, range, target, duration, savingThrow, spellResistance, description));
				
				totalSpells++;
				spellLine = reader.readLine();
			} while (spellLine != null);
			System.out.println("Found " + totalSpells + " spells");
			System.out.println("Longest description was " + longestDescriptionLength + " characters. It was the spell " + longestDescriptionSpell);
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	private static String formatTarget(String area, String effect, String targets) {
		String result = area;
		if (!effect.isBlank() && !result.contains(effect)) {
			if (!result.isBlank()) {
				result += "; " + effect;
			} else {
				result = effect;
			}
		}
		
		if (!targets.isBlank() && !result.contains(targets)) {
			if (!result.isBlank()) {
				result += "; " + targets;
			} else {
				result = targets;
			}
		}
		
		return result;
	}

	private static String format(Object... specs) {
		StringBuilder output = new StringBuilder("(");
		for (Object spec : specs) {
			if (spec instanceof String) {
				output.append("~");
				output.append(spec);
				output.append("~");
			} else {
				output.append(spec);
			}
			
			output.append(", ");
		}
		
		output.replace(output.length()-2, output.length(), ");");
		
		String returnVal = output.toString().replace("'", "''");
		returnVal = returnVal.replace("~", "'");
		
		return returnVal;
	}

	private static String formatTags(String subschool, String descriptor) {
		if (subschool.isBlank() && descriptor.isBlank()) {
			return "";
		} else if (subschool.isBlank() && !descriptor.isBlank()) {
			return descriptor;
		} else if (!subschool.isBlank() && descriptor.isBlank()) {
			return subschool;
		} else {
			return subschool + ", " + descriptor;
		}
	}
}
