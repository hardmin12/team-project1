package com.oraclejava.controller;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.oraclejava.model.DogUser;
import com.oraclejava.repository.DogUserRepository;
import com.oraclejava.util.Authority;

@Controller
public class LoginController {
	
	private final PasswordEncoder passwordEncoder;
	private final DogUserRepository petUserRepository;


	
	public LoginController(PasswordEncoder passwordEncoder, DogUserRepository petUserRepository) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.petUserRepository = petUserRepository;
	}



	// 로그인
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	

	// 회원가입
	@GetMapping("/register")
	public String register(@ModelAttribute("user") DogUser dogUser) {  
		return "register";
	}
	
	@PostMapping("/register")
	public String regi_ok(@Validated @ModelAttribute("user") DogUser dogUser,
				BindingResult result) {
		if (result.hasErrors()) {
			return register(dogUser);
		}
		
		// 패스워드 암호화!
		dogUser.setPassword(passwordEncoder.encode(dogUser.getPassword()));
		
		// 권한 부여
		dogUser.setAuthority(Authority.USER);  // 일반 사용자
		
		// DB저장
		petUserRepository.save(dogUser);
		
		return "redirect:/login?regi_ok";
	}
	@GetMapping("/signUp")
	public String sign(Model model, HttpSession session) {
		session.invalidate();
		return "signUp";
}
	
}
