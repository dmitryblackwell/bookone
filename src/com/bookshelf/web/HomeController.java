package com.bookshelf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bookshelf.constant.PageConstants;

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
}
