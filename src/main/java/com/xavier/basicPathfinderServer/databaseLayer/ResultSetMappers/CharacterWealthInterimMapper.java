package com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xavier.basicPathfinderServer.databaseLayer.ResultSetMappers.interimObjects.CharacterWealthInterim;

public class CharacterWealthInterimMapper implements ResultSetMapper<Object> {

	@Override
	public CharacterWealthInterim map(ResultSet resultSet) {
		try {
			while (resultSet.next()) {
				int earned = resultSet.getInt("EarnedGold");
				int spent = resultSet.getInt("SpentGold");
				return new CharacterWealthInterim(earned, spent);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new CharacterWealthInterim(0, 0);
	}

}
