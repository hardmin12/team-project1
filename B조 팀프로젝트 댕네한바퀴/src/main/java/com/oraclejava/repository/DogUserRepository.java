package com.oraclejava.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.oraclejava.model.DogUser;

public interface DogUserRepository extends PagingAndSortingRepository<DogUser, Long> {

	Optional<DogUser> findByUsername(String username);

}
