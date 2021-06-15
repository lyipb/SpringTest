package com.tutorial.springTutorial.contoller.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tutorial.springTutorial.service.user.UserService;



@Controller
public class UserController {
	 @Autowired
		UserService userService;

	 @RequestMapping("/MyFirstPage") //bcs thymeleaf has been disabled!!!
	    public String greeting(@RequestParam(value="title", required=false, defaultValue="xiao") String title, Model model) {
	        model.addAttribute("name", title);
	        return "index2";
	    }
	 
		private String message = "鐵人賽第七天加油!!!";
//		 
//		 @GetMapping("/")
//			public String index(Map<String, Object> model) {
//				model.put("message", this.message);
//				return "index";
//			}
		
         @GetMapping("/getUserId")
				public String index(Map<String, Object> model) {
				 	model.put("ID", userService.getUserId());
					return "index";
				}

}