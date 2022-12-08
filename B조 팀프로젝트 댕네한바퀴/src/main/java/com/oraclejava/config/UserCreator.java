package com.oraclejava.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.oraclejava.model.DogUser;
import com.oraclejava.repository.DogUserRepository;
import com.oraclejava.util.Authority;


@Component
public class UserCreator implements ApplicationRunner {
	
		private final PasswordEncoder passwordEncoder;
		private final DogUserRepository dogUserRepository;
		
		
		




public UserCreator(PasswordEncoder passwordEncoder, DogUserRepository dogUserRepository) {
			super();
			this.passwordEncoder = passwordEncoder;
			this.dogUserRepository = dogUserRepository;
		}







@Override
	public void run(ApplicationArguments args) throws Exception {		
		DogUser user = new DogUser();
		user.setUsername("son");
		user.setPassword(passwordEncoder.encode("qatar"));
		user.setEmail("son@co.kr");
	    user.setAdress("신도림");
	    user.setAdmin(true);
	    user.setAuthority(Authority.ADMIN);
		

		dogUserRepository.save(user);
		
	}
	
}
