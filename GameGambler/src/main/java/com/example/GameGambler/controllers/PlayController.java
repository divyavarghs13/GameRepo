package com.example.GameGambler.controllers;

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
import com.example.GameGambler.dao.TaskDAO;
import com.example.GameGambler.service.PlayService;


@Controller
public class PlayController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private PlayService abcService;
	
	@Autowired
	TaskDAO taskDAOImpl;

	@Value("${winPercentage}")
	private String winPercentage;
	
	@Value("${bonusPercentage}")
	private String bonusPercentage;
	
	@Value("${winBonusPercentage}")
	private String winBonusPercentage;
		
	@PostMapping("/play")
	public String playGame(@RequestParam("numOfCoins") String numOfCoins,@RequestParam("id") String id, ModelMap modelMap){
		try {
			//System.out.println("ID="+id);
			String chance=abcService.fetchBetChance(winPercentage, bonusPercentage, winBonusPercentage);
			int earnedScore=abcService.fetchScore(Integer.parseInt(numOfCoins), chance);
			taskDAOImpl.insertScoredetails(id, earnedScore);
			modelMap.put("chance", chance);
			modelMap.put("earnedScore", earnedScore);
			logger.info(chance);
			logger.info(String.valueOf(earnedScore));
			return "result";
		} 
		catch(Exception e) {
			return null;
		}
				
	}
}
