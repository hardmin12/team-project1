package com.oraclejava.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.oraclejava.util.Authority;
import com.oraclejava.validator.UniqueLogin;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DogUser {
	@Id
	@GeneratedValue(generator = "doguser_generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "doguser_generator", allocationSize = 1, sequenceName = "doguser_seq")
	private Long id;   // 사용자id
	
	@Size(min = 2, max = 20, message = "사용자아이디는 2자 이상 20자 이내로 입력해 주세요")
	@UniqueLogin
	private String username;  //사용자이름
	
	@NotBlank(message = "패스워드는 필수입력입니다.")
	private String password;  //패스워드
	
	@NotBlank(message = "이메일은 필수입력입니다.")
	@Email(message = "이메일 형식이 잘못 되었습니다.")
	private String email; // 사용자 이메일
	
	@NotBlank(message = "주소는 필수입력입니다.")
	private String addr; // 사용자 주소
	
	private boolean admin; // 관리자인지 아닌지(기본값 false, 관리자 true)
	
	private Authority authority;  // 회원 등급
	
	@OneToMany(mappedBy = "doguser")
	private List<Profile> profiles;

}
