package com.blackwell.web;

import com.blackwell.entity.Comment;
import com.blackwell.model.BookDTO;
import com.blackwell.model.ScoreDTO;
import com.blackwell.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.blackwell.constant.PageConstants;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	CommentRepository commentRepository;

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
