package com.example.GameGambler;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.GameGambler.model.User;
import com.example.GameGambler.service.PlayService;

import com.example.GameGambler.serviceImpl.TaskDAOImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameGamblerApplicationTests {

	
	@Autowired
	private PlayService abcService;
	

	@Autowired
	private TaskDAOImpl taskDAOImpl;
	
	@Value("${winPercentage}")
	private String winPercentage;
	
	@Value("${bonusPercentage}")
	private String bonusPercentage;
	
	@Value("${winBonusPercentage}")
	private String winBonusPercentage;
	
	@Test
	public void fetchBetWinChanceTest(){
		try {
			String chance=abcService.fetchBetChance(winPercentage, bonusPercentage, winBonusPercentage);
			System.out.println(chance);
			if(chance.equalsIgnoreCase("WIN"))
				assertEquals(chance, "WIN");
			else if(chance.equalsIgnoreCase("BONUS"))
				assertEquals(chance, "BONUS");
			else if(chance.equalsIgnoreCase("WIN_AND_BONUS"))
				assertEquals(chance, "WIN_AND_BONUS");
			else
				assertEquals(chance, "LOSS");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
/**
	@Test(expected = SQLException.class)
	public void loginTest(){
		User u=new User();
		u.setFirst_name("Divya");
		u.setLast_name("Vargs");
		u.setUsername("divyavargs");
		u.setPassword("abc");
		taskDAOImpl.insertUser(u);
		
	}
	**/

}
