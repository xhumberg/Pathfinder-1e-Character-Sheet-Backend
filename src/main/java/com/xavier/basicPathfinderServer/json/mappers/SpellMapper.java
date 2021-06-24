package com.xavier.basicPathfinderServer.json.mappers;

import com.xavier.basicPathfinderServer.characterOwned.Spell;
import com.xavier.basicPathfinderServer.json.SpellJson;

public class SpellMapper {

	public static SpellJson map(Spell spell, int level) {
		String spellName = "";
		if (spell.getSavingThrow().contains("harmless") || spell.getSavingThrow().equals("none")) {
			spellName = spell.getName();
		} else {
			spellName = spell.getName() + " (DC " + spell.getSpellDCValue() + ")";
		}
		
		StringBuilder description = new StringBuilder();
		description.append("<b>School</b> ").append(spell.getSchool());
		if (spell.getTags() != null && !spell.getTags().isEmpty()) {
			description.append(" (").append(spell.getTags()).append(")");
		}
		description.append("; <b>Level</b> ").append(spell.getLevelInformation()).append("<br>");
		
		description.append("<hr>CASTING<hr>");
		description.append("<b>Casting Time</b> ").append(spell.getCastingTime()).append("<br>");
		description.append("<b>Components</b> ").append(spell.getComponents()).append("<br>");
		
		description.append("<hr>EFFECT<hr>");
		description.append("<b>Range</b> ").append(spell.getRange()).append("<br>");
		description.append("<b>Target</b> ").append(spell.getTarget()).append("<br>");
		description.append("<b>Duration</b> ").append(spell.getDuration()).append("<br>");
		description.append("<b>Saving Throw</b> ").append(spell.getSavingThrow()).append("; <b>Spell Resistance</b> ").append(spell.getSpellResistance()).append("<br>");		
		description.append("<hr>DESCRIPTION<hr>");		
		description.append(spell.getDescription().replace("\\r\\n", "<br>"));
		
		SpellJson json = new SpellJson(spellName, description.toString(), spell.getClassId(), level, spell.hasBeenCast());
		return json;
	}

}
