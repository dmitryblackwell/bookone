package com.blackwell.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.blackwell.constant.PageConstants;


@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping
	public String home() {
		return PageConstants.REDIRECT_BOOKS;
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/getCurrentUser")
	public String getCurrentUser(){
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return "redirect:/users/" + userDetails.getUsername();
	}
}
