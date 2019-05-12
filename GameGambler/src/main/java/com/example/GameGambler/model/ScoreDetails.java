package com.example.GameGambler.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component
public class ScoreDetails  implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int userid;
    int score;
	Timestamp playtime;
	
	public int getUserid() {
		return userid;
	}
	@Override
	public String toString() {
		return "ScoreDetails [userid=" + userid + ", score=" + score + ", playtime=" + playtime + "]";
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Timestamp getCurrentTime()
    {      
		java.sql.Timestamp t = null;
       try{             	
    	   t=new Timestamp(System.currentTimeMillis());
    	   //System.out.println("formatted Time="+t);
       }
       catch(Exception e){ 
        e.printStackTrace(); 
       }
       return t; 
   }
	public Timestamp getPlaytime() {
		return playtime;
	}

	public void setPlaytime(Timestamp playtime) {
		this.playtime = getCurrentTime();
	}
	
    
}
