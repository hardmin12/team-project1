package com.oraclejava.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.oraclejava.model.Pet;
import com.oraclejava.repository.PetRepository;
import com.oraclejava.repository.ProfileRepository;

@Controller
public class MainController {

	private ProfileRepository profileRepository;
	private PetRepository petRepository;

	public MainController(ProfileRepository profileRepository, PetRepository petRepository) {
		super();
		this.profileRepository = profileRepository;
		this.petRepository = petRepository;
	}

	//repository를 통해 model의 값을 가져와서 html에 타임리프 써서 id표현하기
	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("mainkey", profileRepository.findAll());
		return "main";
	}
	
	@RequestMapping("/list")
	public String productList(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
		if (keyword == null)
			keyword = "";// 키워드가 없으면 전체 검색
		List<Pet> list = petRepository.search(keyword);
		model.addAttribute("list", list);
		return "friendlist";
	}
}
