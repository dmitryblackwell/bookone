package com.bookshelf.web;

import com.bookshelf.util.MailUtil;
import com.mchange.v2.lang.ThreadUtils;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.bookshelf.constant.PageConstants;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeController {

	@GetMapping
	public String home() {
		return PageConstants.REDIRECT_BOOKS;
	}

	@GetMapping("/login")
	public String login() throws MessagingException {
		//MailUtil.sendMail( "dmitryblackwell@gmail.com", "It WORKED!!!", "you know what to do...");
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
