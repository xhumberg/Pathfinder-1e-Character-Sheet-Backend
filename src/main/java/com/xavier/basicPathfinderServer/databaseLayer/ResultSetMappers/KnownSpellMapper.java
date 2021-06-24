package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xavier.basicPathfinderServer.characterOwned.Spell;

public class KnownSpellMapper implements ResultSetMapper<Object> {

	@Override
	public List<Spell> map(ResultSet resultSet) {
		List<Spell> spells = new ArrayList<>();
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("SpellID");
				String levelInformation = resultSet.getString("SpellLevel");
				String name = resultSet.getString("SpellName");
				String school = resultSet.getString("SpellSchool");
				String tags = resultSet.getString("SpellTags");
				String castingTime = resultSet.getString("SpellCastingTime");
				String components = resultSet.getString("SpellComponents");
				String range = resultSet.getString("SpellRange");
				String target = resultSet.getString("SpellTarget");
				String duration = resultSet.getString("SpellDuration");
				String savingThrow = resultSet.getString("SpellSavingThrow");
				String spellResistance = resultSet.getString("SpellSpellResistance");
				String description = resultSet.getString("SpellDescription");
				
				int classId = Integer.parseInt(resultSet.getString("ClassId"));
				
				Spell spell = new Spell(id, levelInformation, name, school, tags, castingTime, components, range, target, duration, savingThrow, spellResistance, description);
				spell.addClassId(classId);
				spells.add(spell);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return spells;
	}

}
