/**
 * 
 */
package com.example.GameGambler.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.GameGambler.model.ScoreDetails;
import com.example.GameGambler.model.User;
import com.example.GameGambler.repository.UserRepository;
import com.example.GameGambler.serviceImpl.TaskDAOImpl;
/**
 * @author Divya
 *
 */
@Controller
public class WelcomeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	TaskDAOImpl taskDAOImpl;
	
	User user=new User();
	ScoreDetails scoredetails=new ScoreDetails();
	
	@RequestMapping(value = "/welcome")
	String showLoginPage(ModelMap model) {
		return ("welcome");
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@PostMapping("/saveDetails")
	public String saveDetails(@RequestParam("first_name") String first_name,
			@RequestParam("last_name") String last_name, @RequestParam("username") String username,
			@RequestParam("password") String password, ModelMap modelMap) {
		
		
		user.setFirst_name(first_name);
		user.setLast_name(last_name);
		user.setUsername(username);
		user.setPassword(password);
		/**
		 * Insert User details to db
		 */
		if(taskDAOImpl.insertUser(user)) {
			return "reg_success";
		}
		else {
			String message="Registration Failed, Username already exists!!";
			modelMap.put("message",message);
			return "error";
		}
	}
	
	@PostMapping("/findUser")
	public String authenticate(@RequestParam("username") String username,
			@RequestParam("password") String password, ModelMap modelMap) {		
		/**
		 * authenticate user by checking username and password
		 */
		List<User> userList=taskDAOImpl.findUser(username, password);	
		if(userList!=null && userList.size()>0) {
			user=userList.get(0);
		String name=user.getFirst_name()+" "+user.getLast_name();
		logger.info("User ="+name);
		int id=user.getUserid();		
		/**
		 * retrieving the previous winning history by userid from database
		 */
		List<ScoreDetails> scoreList=taskDAOImpl.fetchScoreDetailsOfUser(id);
		
		List<String> scores=new ArrayList<String>();
		List<String> timeOfPlay=new ArrayList<String>();
		if(scoreList!=null && scoreList.size()>0)	{
			for (ScoreDetails list : scoreList) {
				if(list.getScore() > 0) {
				scores.add(list.getScore().toString());
				timeOfPlay.add(list.getTimeofplay().toString());
				}
			}
		}
		modelMap.put("id", id);
		modelMap.put("name", name);
		modelMap.put("scorelist", scores);
		modelMap.put("playlist", timeOfPlay);
		return "play";
		}
		else {

			String message="Login Failed, Username and Password didn't match!!";
			modelMap.put("message",message);
			return "error";
		}
		/**		 		
		if (userList != null && userList.size() > 0)
			user = userList.get(0);
			
		modelMap.put("id", id);
		modelMap.put("name", user);
		
		String id=user.get(0).toString();
		String name=user.get(1).toString();
		
		modelMap.put("ScoreDetails",list);
		modelMap.put("id", id);
		modelMap.put("name", name);
		return "play";**/

	}
}
