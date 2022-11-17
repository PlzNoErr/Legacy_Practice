package com.ssafy.hw.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ssafy.hw.model.dao.UserDao;
import com.ssafy.hw.model.dto.User;
import com.ssafy.hw.model.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public String doLogin(User user, HttpSession session, Model model) {
		String view = "/index";
		User data = userService.getUserById(user.getUserId());
		
		if (user.getUserId().equals(data.getUserId()) && user.getUserPass().equals(data.getUserPass())) {
			user.setUserName(data.getUserName());
			session.setAttribute("loginUser", user);
			view = "redirect:/";
		} else {
			model.addAttribute("msg", "로그인 실패");
		}

		return view;
	}

	@GetMapping("/logout")
	public String doLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
