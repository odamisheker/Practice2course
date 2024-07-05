package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

	@Autowired
	private UserService userService;

	@GetMapping("/signup")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "signup";
	}

	@PostMapping("/signup")
	public String registerUser(@Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "signup";
		}

		if (userService.findByUsername(user.getUsername()).isPresent()) {
			model.addAttribute("userExistsError", "Username already exists");
			return "signup";
		}

		userService.saveUser(user);
		return "redirect:/login";
	}
	@GetMapping("/login")
	public String showLoginForm(Model model) {
		//model.addAttribute("user", new User());
		return "login";
	}
}
