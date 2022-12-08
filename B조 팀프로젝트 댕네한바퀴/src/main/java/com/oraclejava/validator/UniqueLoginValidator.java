package com.oraclejava.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.oraclejava.repository.DogUserRepository;

public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String>{

	private final DogUserRepository dogUserRepository;
	
	
	public UniqueLoginValidator() {
		
		this.dogUserRepository = null;
	}

	@Autowired
	public UniqueLoginValidator(DogUserRepository dogUserRepository) {
		
		this.dogUserRepository = dogUserRepository;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return dogUserRepository == null || dogUserRepository.findByUsername(value).isEmpty();
	}
	
	
	
}
