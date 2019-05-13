package com.example.GameGambler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.GameGambler.service.PlayService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameGamblerApplicationTests {

	
	@Autowired
	private PlayService abcService;
	
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
			assertEquals(chance, "WIN");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
