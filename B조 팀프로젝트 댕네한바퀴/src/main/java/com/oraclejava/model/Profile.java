package com.oraclejava.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Profile implements Serializable{ //Pet 클래스의 Table끌고오기
	@Id
	@GeneratedValue(generator = "profile_generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "profile_generator", allocationSize = 1, sequenceName = "profile_seq")
	private Long id; // 프로필 id

	private String heart; //하트
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pet_id")//그냥Column은 컬럼을 만들고 JoinColumn은 조인도하고 컬럼도 만듬
	private Pet pet;
	
	@ManyToOne
	@JoinColumn(name = "doguser_id")
	private DogUser doguser;
	
}
