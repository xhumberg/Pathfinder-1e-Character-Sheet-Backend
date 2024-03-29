package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.complexMappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.ResultSetMapper;

public class ClassSkillMapper implements ResultSetMapper<Object> {

	@Override
	public List<String> map(ResultSet resultSet) {
		List<String> classSkills = new LinkedList<>();
		try {
			while (resultSet.next()) {
				String classSkill = resultSet.getString("SkillName");
				classSkills.add(classSkill);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classSkills;
	}

}
