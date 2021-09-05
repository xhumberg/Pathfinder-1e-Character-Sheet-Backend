package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.ResultSetMapper;
import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.interimObjects.SpellNameLevelAndClassInterim;

public class SpellInterimMapper implements ResultSetMapper<Object> {

	@Override
	public List<SpellNameLevelAndClassInterim> map(ResultSet resultSet) {
		List<SpellNameLevelAndClassInterim> spells = new ArrayList<>();
		try {
			while (resultSet.next()) {
				int classId = Integer.parseInt(resultSet.getString("ClassID"));
				int level = Integer.parseInt(resultSet.getString("SpellLevel"));
				String spellName = resultSet.getString("SpellName");
				SpellNameLevelAndClassInterim spell = new SpellNameLevelAndClassInterim(classId, level, spellName);
				spells.add(spell);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return spells;
	}

}
