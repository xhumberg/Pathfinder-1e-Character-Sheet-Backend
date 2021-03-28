package com.xavier.basicPathfinderServer.ResultSetMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xavier.basicPathfinderServer.Spell;
import com.xavier.basicPathfinderServer.ResultSetMappers.interimObjects.PreppedSpellInterim;

public class PreppedSpellMapper implements ResultSetMapper<Object> {

	@Override
	public List<PreppedSpellInterim> map(ResultSet resultSet) {
		List<PreppedSpellInterim> spells = new ArrayList<>();
		try {
			while (resultSet.next()) {
				int classId = Integer.parseInt(resultSet.getString("ClassID"));
				int level = Integer.parseInt(resultSet.getString("SpellLevel"));
				String spellName = resultSet.getString("SpellName");
				PreppedSpellInterim spell = new PreppedSpellInterim(classId, level, spellName);
				spells.add(spell);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return spells;
	}

}
