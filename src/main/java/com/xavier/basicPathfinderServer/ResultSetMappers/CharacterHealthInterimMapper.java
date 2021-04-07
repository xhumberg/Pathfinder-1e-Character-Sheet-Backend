package com.xavier.basicPathfinderServer.ResultSetMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xavier.basicPathfinderServer.ResultSetMappers.interimObjects.CharacterHealthInterim;
import com.xavier.basicPathfinderServer.ResultSetMappers.interimObjects.CharacterWealthInterim;

public class CharacterHealthInterimMapper implements ResultSetMapper<Object> {

	@Override
	public CharacterHealthInterim map(ResultSet resultSet) {
		try {
			while (resultSet.next()) {
				int favoredClass = resultSet.getInt("FavoredClassBonusHP");
				int damage = resultSet.getInt("DamageTaken");
				return new CharacterHealthInterim(favoredClass, damage);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new CharacterHealthInterim(0, 0);
	}

}
