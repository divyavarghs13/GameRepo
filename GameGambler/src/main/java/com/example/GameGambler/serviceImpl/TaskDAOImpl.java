package com.example.GameGambler.serviceImpl;

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
import org.springframework.stereotype.Service;

import com.example.GameGambler.model.ScoreDetails;
import com.example.GameGambler.model.User;
import com.example.GameGambler.repository.ScoreRepository;
import com.example.GameGambler.repository.UserRepository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

@Service
public class TaskDAOImpl {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private ScoreRepository scoreRepo;

	public boolean insertUser(User user) {
		boolean insert=false;
		String username=user.getUsername().toString();
		try {
			
			/** check whether username is already existing in db **/
			List<User>existingList=userRepo.isExistsUserName(username);
			if(existingList!=null){
				logger.info("Username already exists!!");
				insert=false;
			}
			else {
				userRepo.save(user);
				insert= true;
			}
		} catch (Exception ex) {
			logger.info(" error while inserting user {}", ex.getMessage());
		}
		return insert;
	}
	
	public List<User> findUser(String username, String password) {
		List<User> userList = new ArrayList<User>();
		try {
			
			userList = userRepo.findByUserNameAndPassword(username, password);	
			if(userList.isEmpty()){
				logger.info("Username and Password didn't match!!");
			}

		} catch (Exception ex) {
			logger.info(" error while retrieving User {}", ex.getMessage());
		}
		return userList;
	}

	public void insertScoredetails(ScoreDetails score) {
		try {
			scoreRepo.save(score);
		} catch (Exception ex) {
			logger.info(" error while inserting Scoredetails {}", ex.getMessage());
		}
	}
	
	public List<ScoreDetails> fetchScoreDetailsOfUser(int userid) {
		List<ScoreDetails> scoreList = new ArrayList<ScoreDetails>();
		try {
			 scoreList=scoreRepo.findByScoreDetailsOfUser(userid);

		} catch (Exception ex) {
			logger.info(" error while retrieving Scoredetails {}", ex.getMessage());
		}
		return scoreList;
	}

}