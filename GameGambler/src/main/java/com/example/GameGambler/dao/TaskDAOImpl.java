package com.example.GameGambler.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Repository;

import com.example.GameGambler.model.ScoreDetails;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;  

@Repository
public class TaskDAOImpl implements TaskDAO {
	
	@Autowired
	protected JdbcTemplate jdbcTemplate;	
	
	@Autowired
	ScoreDetails scoredata;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public int insertUser(String first_name,String last_name,String username,String password) {
		final String sql =getinsertUserSQL();
			logger.info(" : insert sql {}", sql);
			final KeyHolder k = new GeneratedKeyHolder();
			
			try {
				jdbcTemplate.update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);							
						ps.setString(1, first_name);
						ps.setString(2, last_name);	
						ps.setString(3, username);	
						ps.setString(4, password);	
						return ps;
					}
				}, k);
				//System.out.println("postvalues sql="+sql);
				
			} catch (Exception ex) {
				logger.info(" error while inserting transaction {}", ex.getMessage());
				//throw DataBaseException(" Creating entity failed. See my logs for more detials ");
			}
			int pk=Integer.parseInt(k.getKeys().get("userid").toString());		
			
			return pk;
		}

	@Override
	public List<String> findUser(String username, String password) {
		final String sql =getfindUserSQL();
		List<String> res = new ArrayList<>();
		
		logger.info(" :: select sql {}", sql);		
		try {
			String row=jdbcTemplate.query(
			           sql,
			            new PreparedStatementSetter() {
			              public void setValues(PreparedStatement preparedStatement) throws
			                SQLException {
			                  preparedStatement.setString(1, username);
			                  preparedStatement.setString(2, password);
			
			              }
			            }, 
			            new ResultSetExtractor<String>() {
			              public String extractData(ResultSet resultSet) throws SQLException,
			                DataAccessException {
			                  if (resultSet.next()) {
			                	  String id=resultSet.getString(1);
			                	 String name=resultSet.getString(2)+" "+resultSet.getString(3);
			                	  res.add(id);
			                	  res.add(name);
			                      return name;
			                  }
			                  else {
			                	  return null;
			                  }
			              }
			            }
			        );
			    
												
		} catch (Exception ex) {
			logger.info(" error while getting user {}", ex.getMessage());
		}
		return res;
	}


	@Override
	public void insertScoredetails(String userid,int score) {
		final String sql =getinsertUserScoreSQL();
			logger.info(" : insert sql {}", sql);
			
			try {
				jdbcTemplate.update(new PreparedStatementCreator() {
					@Override
					public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
						PreparedStatement ps = con.prepareStatement(sql, Statement.NO_GENERATED_KEYS);							
						ps.setString(1, userid);
						ps.setInt(2, score);	
						ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));	
						return ps;
					}
				});
				//System.out.println("postvalues sql="+sql);
				
			} catch (Exception ex) {
				logger.info(" error while inserting transaction {}", ex.getMessage());
				//throw DataBaseException(" Creating entity failed. See my logs for more detials ");
			}
				
			
		}

	@Override
	public ScoreDetails getScoredetails(int userid) {
		final String sql =getUserScoreSQL();
		final ScoreDetails sd = new ScoreDetails();
		logger.info(" : select sql {}", sql);
		
	
		RowCallbackHandler callback = new RowCallbackHandler() {

			public void processRow(ResultSet rs) throws SQLException {
				
				sd.setUserid(rs.getInt("userid"));
				sd.setScore(rs.getInt("score"));
				sd.setPlaytime((Timestamp)rs.getTimestamp("timeofplay"));
			
			}
		};
		jdbcTemplate.query(sql, callback, userid);
		
		return sd;	
	}
}