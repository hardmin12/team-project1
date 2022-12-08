package com.oraclejava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.oraclejava.repository.DogUserRepository;

@Controller
public class AdminController {
	private DogUserRepository petUserRepository;
	@Autowired
	public AdminController(DogUserRepository petUserRepository) {
		super();
		this.petUserRepository = petUserRepository;
								
	}
	@GetMapping("/admin/list")
	public String adminList(Model model) {
		model.addAttribute("admin", petUserRepository.findAll());
		
		return "adminlist";
}
}	
	
	

	

	
	

	
	


