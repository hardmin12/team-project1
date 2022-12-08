package com.oraclejava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.oraclejava.model.Profile;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, Long>{

	@Query("select pr from Profile pr where pr.doguser.username= :userid ")
	List<Profile> findByUserId(String userid);

}
