package com.oraclejava.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pet implements Serializable{
	@Id
	@GeneratedValue(generator = "pet_generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "pet_generator", allocationSize = 1, sequenceName = "pet_seq")
	private Long id; //펫 id
	
	private String dogname; //개 이름
	private String dogage; // 나이
	private String doggender; //  성별
	private String kind; // 종
	private String dogsize; //크기
	private String per; // 성격

	@OneToMany(mappedBy = "pet", cascade = CascadeType.ALL)
	private List<Profile> profiles;
	
}
