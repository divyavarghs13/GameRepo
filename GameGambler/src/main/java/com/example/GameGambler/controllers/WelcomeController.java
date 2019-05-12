/**
 * 
 */
package com.example.GameGambler.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.GameGambler.dao.TaskDAOImpl;
import com.example.GameGambler.model.ScoreDetails;
import com.example.GameGambler.dao.TaskDAO;
import com.example.GameGambler.controllers.PlayController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author Divya
 *
 */
@Controller
public class WelcomeController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TaskDAO taskDAOImpl;
	
	@Autowired
	PlayController playcontroller;
	
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

		taskDAOImpl.insertUser(first_name, last_name, username, password);
		return "reg_success";

	}
	@PostMapping("/findUser")
	public String authenticate(@RequestParam("username") String username,
			@RequestParam("password") String password, ModelMap modelMap) {

		List<String> user=taskDAOImpl.findUser(username, password);	
		String id=user.get(0).toString();
		String name=user.get(1).toString();
		
		/**Added for history **/
		
		ScoreDetails sd=taskDAOImpl.getScoredetails(Integer.parseInt(id));	
		List<ScoreDetails> list=new ArrayList();		
		list.add(sd);
		System.out.println(sd);
		modelMap.put("ScoreDetails",list);
		modelMap.put("id", id);
		modelMap.put("name", name);
		return "play";

	}
}
