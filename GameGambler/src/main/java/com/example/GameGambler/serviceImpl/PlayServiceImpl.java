package com.example.GameGambler.serviceImpl;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.GameGambler.common.Constant;
import com.example.GameGambler.service.PlayService;

@Service
public class PlayServiceImpl implements PlayService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public String fetchBetChance(String winPercentage, String bonusPercentage, String winBonusPercentage)
			throws Exception {
		try {
			Random rn = new Random();
			int chance = rn.nextInt(100) + 1;
			logger.info(String.valueOf(chance));
			if(chance <= Integer.parseInt(winPercentage))
				return Constant.WIN_STATUS;
			else if(chance <= (Integer.parseInt(winPercentage)+Integer.parseInt(bonusPercentage)))
				return Constant.BONUS_STATUS;
			else if(chance <= (Integer.parseInt(winPercentage)+Integer.parseInt(bonusPercentage)+Integer.parseInt(winBonusPercentage)))
				return Constant.WIN_BONUS_STATUS;
			else
				return Constant.LOSS;						
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public int fetchScore(int numOfCoins,String chance)
			throws Exception {
		 
		try {			
			if(chance.equalsIgnoreCase("WIN"))
				return (numOfCoins*2);
			else if(chance.equalsIgnoreCase("BONUS"))
				return (numOfCoins);
			else if(chance.equalsIgnoreCase("WIN_AND_BONUS"))
				return (numOfCoins*2);
			else
				return (numOfCoins*0);						
		} catch (Exception e) {
			throw e;
		}
		
	}
}