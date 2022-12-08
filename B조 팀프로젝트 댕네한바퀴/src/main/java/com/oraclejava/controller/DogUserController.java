package com.oraclejava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.oraclejava.repository.ProfileRepository;

@Controller
public class DogUserController {

	private ProfileRepository profileRepository;

	public DogUserController(ProfileRepository profileRepository) {
		super();
		this.profileRepository = profileRepository;
	}
	
	@GetMapping("/userlist")
	public String userlist(Model model) {
		model.addAttribute("user", profileRepository.findAll());
		return "userlist";
	}
}
