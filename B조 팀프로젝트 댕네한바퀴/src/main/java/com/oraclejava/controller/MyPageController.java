package com.oraclejava.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.oraclejava.model.DogUser;
import com.oraclejava.model.Profile;
import com.oraclejava.repository.DogUserRepository;
import com.oraclejava.repository.PetRepository;
import com.oraclejava.repository.ProfileRepository;

@Controller
public class MyPageController {

	private ProfileRepository profileRepository;
	private PetRepository petRepository;
	private DogUserRepository dogUserRepository;

	public MyPageController(ProfileRepository profileRepository, PetRepository petRepository,
			DogUserRepository dogUserRepository) {
		super();
		this.profileRepository = profileRepository;
		this.petRepository = petRepository;
		this.dogUserRepository = dogUserRepository;
	}

	@GetMapping("/profileform")
	public String addProfile(@ModelAttribute Profile profile, Authentication loginUser, Model model) {
		// model.addAttribute("name", loginUser.getName());
		// model.addAttribute("authority", loginUser.getAuthorities());
		return "profileform";
	}

	@PostMapping("/add_ok")
	public String addOk(@Validated Profile profile, BindingResult result, Authentication loginUser, Model model) { 
																													
																													
																													
		if (result.hasErrors()) {
			return addProfile(profile, loginUser, model);
		}

		DogUser user = dogUserRepository.findByUsername(loginUser.getName()).get(); // get은 옵셔널이라 무조건 붙임
		profile.setDoguser(user);
		profileRepository.save(profile);
		petRepository.save(profile.getPet());
		return "redirect:/";
	}

	@GetMapping("/mypage")
	public String getMyPage(Authentication loginUser, Model model) {

		model.addAttribute("userid", loginUser.getName());
		model.addAttribute("authority", loginUser.getAuthorities());

		model.addAttribute("profiles", profileRepository.findByUserId(loginUser.getName())); // 겟 네임으로 쓰도록 정해져있음

		return "mypage";

	}

	@PostMapping("/edit/{id}") // 프로필 아이디
	public String editProfile(@PathVariable Long id, Model model) {

		model.addAttribute("profile", profileRepository.findById(id));
		return "profileForm";

	}

	@PostMapping("/edit_ok")
	public String editOk(@Validated Profile profile, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return editProfile(profile.getId(), model); 
		}

		profileRepository.save(profile);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteProfile(@PathVariable Long id) {
//		Profile profile = profileRepository.findById(id).get();

		petRepository.deleteById(id);
//		petRepository.delete(profile.getPet());
		return "redirect:/";
	}

}
