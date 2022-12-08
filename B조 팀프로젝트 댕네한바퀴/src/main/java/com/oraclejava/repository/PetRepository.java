package com.oraclejava.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.oraclejava.model.Pet;

public interface PetRepository extends PagingAndSortingRepository<Pet, Long>{
	@Query(value = "select p from Pet p where "
			+ "p.dogname like %:keyword% "
			+ "OR p.dogage like %:keyword% "
			+ "OR p.doggender like %:keyword% "
			+ "OR p.kind like %:keyword% "
			+ "OR p.dogsize like %:keyword% "
			+ "OR p.per like %:keyword%")
	//p.dogname like %:keyword% 까지하고 뒤이어 추가하려면OR을 쓴다
	List<Pet> search(String keyword);
	
	//@Query(value = "select p from Pet p where p.dogname like %:keyword%")기본 이름찾기형태

}
