package com.xavier.basicPathfinderServer.ResultSetMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkillRanksMapper implements ResultSetMapper<Object> {

	@Override
	public Map<String, Integer> map(ResultSet resultSet) {
		Map<String, Integer> allSkillRanks = new HashMap<>();
		try {
			while (resultSet.next()) {
				String skillName = resultSet.getString("SkillName");
				int skillRanks = Integer.parseInt(resultSet.getString("SkillRanks"));
				allSkillRanks.put(skillName, skillRanks);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allSkillRanks;
	}

}
