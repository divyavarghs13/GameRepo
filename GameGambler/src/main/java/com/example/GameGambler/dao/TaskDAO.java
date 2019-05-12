/**
 * 
 */
package com.example.GameGambler.dao;

import java.util.List;
import java.util.Map;

import com.example.GameGambler.model.ScoreDetails;

/**
 * @author Divya
 *
 */
public interface TaskDAO {
public int insertUser(String first_name,String last_name,String username,String password);
public List<String> findUser(String username,String password);
public void insertScoredetails(String userid,int earnedScore);
public ScoreDetails getScoredetails(int userid);

default String getinsertUserSQL() {
return new StringBuilder().append(" insert into USER ").append(" (first_name, last_name, username, password)")
	.append(" values")
	.append(" (?,?,?,?) ").toString();
}

default String getfindUserSQL() {
return new StringBuilder().append(" select * from USER").append(" where username=")	
	.append(" ? ").append(" and password=").append(" ? ").toString();
}

default String getinsertUserScoreSQL() {
return new StringBuilder().append(" insert into SCOREDETAILS ").append(" (userid, score, timeofplay)")	
		.append(" values")
		.append(" (?,?,?) ").toString();
}

default String getUserScoreSQL() {
return new StringBuilder().append(" select * from SCOREDETAILS ").append(" where userid=")	
		.append(" ? ").toString();
}

}

