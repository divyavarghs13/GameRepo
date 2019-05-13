package com.example.GameGambler.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="scoredetails")
public class ScoreDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer scoreid;
	
	@Column(name = "userid")
	private Integer userid;
	
	@Column(name = "score")
	private Integer score;
	
	@Column(name = "timeofplay")
	private Timestamp timeofplay;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Timestamp getTimeofplay() {
		return timeofplay;
	}

	public void setTimeofplay(Timestamp timeofplay) {
		this.timeofplay = new Timestamp(System.currentTimeMillis());
	}

	
	
}
