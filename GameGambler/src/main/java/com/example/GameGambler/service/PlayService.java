package com.example.GameGambler.service;

public interface PlayService {
	public String fetchBetChance(String winPercentage,String bonusPercentage,String winBonusPercentage) throws Exception;

	public int fetchScore(int numOfCoins, String chance) throws Exception; 
}
