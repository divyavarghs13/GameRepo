package com.example.GameGambler.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

public class ScoreMapper implements RowMapper<ScoreDetails> {
	@Autowired
	ScoreDetails scoredetails;
	@Override
	public ScoreDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		scoredetails.setScore(rs.getInt("score"));
		scoredetails.setScore(rs.getInt("timeOfPlay"));
	        return scoredetails;
	}

}
