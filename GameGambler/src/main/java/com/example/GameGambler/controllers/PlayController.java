package com.example.GameGambler.controllers;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.GameGambler.common.Constant;
import com.example.GameGambler.model.ScoreDetails;
import com.example.GameGambler.service.PlayService;
import com.example.GameGambler.serviceImpl.TaskDAOImpl;


@Controller
public class PlayController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PlayService abcService;
	
	@Autowired
	TaskDAOImpl taskDAOImpl;
	
	@Value("${winPercentage}")
	private String winPercentage;
	
	@Value("${bonusPercentage}")
	private String bonusPercentage;
	
	@Value("${winBonusPercentage}")
	private String winBonusPercentage;
	
	ScoreDetails score=new ScoreDetails();
	
	@PostMapping("/play")
	public String playGame(@RequestParam("numOfCoins") String numOfCoins,@RequestParam("id") int id, ModelMap modelMap){
		try {
			//System.out.println("ID="+id);
			modelMap.put("id", id);
			modelMap.put("numOfCoins", numOfCoins);
			return "result";
		} 
		
		catch(Exception e) {
			return null;
		}
				
	}
	
	@RequestMapping(value = "/bet/checkStatus", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody	
	public ResponseEntity<Object> testSegmentXML(@RequestParam("numOfCoins") Integer numOfCoins, @RequestParam("id") Integer id){
		try {
			logger.info("userID="+id);
			logger.info("numOfCoins="+numOfCoins);
			String status=abcService.fetchBetChance(winPercentage, bonusPercentage, winBonusPercentage);
			int earnedScore=abcService.fetchScore(numOfCoins, status);
			score.setScore(earnedScore);
			int userid=(int)id;
			score.setUserid(userid);
			score.setTimeofplay(new Timestamp(System.currentTimeMillis()));
			/**
			 * inserting the score to the database according to the userid
			 */
			taskDAOImpl.insertScoredetails(score);
			logger.info("Current Score="+earnedScore);
			String chance;
			if(status.equalsIgnoreCase("WIN"))
				chance=Constant.WIN_STATUS;
			else if(status.equalsIgnoreCase("BONUS"))
				chance=Constant.BONUS_STATUS;
			else if(status.equalsIgnoreCase("WIN_BONUS_STATUS"))
				chance=Constant.WIN_BONUS_STATUS;
			else if(status.equalsIgnoreCase("LOSS"))
				chance=Constant.LOSS;
			else 
				chance= Constant.UNEXPECTED_ERROR;
			
			logger.info("status="+status);
			return new ResponseEntity<Object>(chance, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(Constant.UNEXPECTED_ERROR, HttpStatus.OK);
		}
				
	}
}
