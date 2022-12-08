package com.oraclejava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.oraclejava.model.DogUser;
import com.oraclejava.model.Profile;
import com.oraclejava.repository.ProfileRepository;

@Controller
public class ProfileController {
	private final ProfileRepository profileRepository;

	public ProfileController(ProfileRepository profileRepository) {
		super();
		this.profileRepository = profileRepository;
	}

//	@GetMapping("/profiles")
//	public String profiles(Model model) {
//		model.addAttribute("profilelist", profileRepository.findAll());
//
//		return "profile";
//	}
//	@GetMapping()
//	public String add(@ModelAttribute Profile profile) {
//		
//		return "profile";
//	}
//	
//	@PostMapping("/profile_add_ok")
//	public String addOk(@Validated Profile profile, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			return add(profile);
//		}
//
//		profileRepository.save(profile);
//		return "redirect:/";
//	}
	
}
