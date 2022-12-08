package com.oraclejava.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oraclejava.model.DogUser;
import com.oraclejava.repository.DogUserRepository;

@Service
public class LoginService implements UserDetailsService {
	private final DogUserRepository dogUserRepository;

	public LoginService(DogUserRepository dogUserRepository) {
		super();
		this.dogUserRepository = dogUserRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 유저이름으로 검색
		DogUser user = dogUserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("그런 유저 없습니다"));

		return new User(user.getUsername(), user.getPassword(),
				AuthorityUtils.createAuthorityList(user.getAuthority().name()));

	}

}
