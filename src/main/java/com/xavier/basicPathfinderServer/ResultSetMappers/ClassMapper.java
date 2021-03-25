package com.xavier.basicPathfinderServer.ResultSetMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.xavier.basicPathfinderServer.CastingType;
import com.xavier.basicPathfinderServer.CharacterClass;

public class ClassMapper implements ResultSetMapper<Object> {

	@Override
	public List<CharacterClass> map(ResultSet resultSet) {
		List<CharacterClass> classes = new ArrayList<>();
		try {
			while (resultSet.next()) {
				int level = Integer.parseInt(resultSet.getString("ClassLevel"));
				int bab = Integer.parseInt(resultSet.getString("ClassBAB"));
				int fort = Integer.parseInt(resultSet.getString("ClassFort"));
				int ref = Integer.parseInt(resultSet.getString("ClassRef"));
				int will = Integer.parseInt(resultSet.getString("ClassWill"));
				String name = resultSet.getString("ClassName");
				boolean spellcasting = resultSet.getString("Spellcasting").equals("t");
				CastingType type = CastingType.valueOf(resultSet.getString("SpellcastingType"));
				String ability = resultSet.getString("SpellcastingAbility");
				String spellsPerDayString = resultSet.getString("SpellsPerDay");
				Map<Integer, Integer> baseSpellsPerDay = SpellsPerDayParser.parse(spellsPerDayString);
				
				CharacterClass characterClass = new CharacterClass(level, bab, fort, ref, will, name, spellcasting, type, ability, baseSpellsPerDay);
				System.out.println(baseSpellsPerDay);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classes;
	}

}
