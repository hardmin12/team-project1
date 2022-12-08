package com.oraclejava.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DetailComment implements Serializable{

	
	@Id
	@GeneratedValue(generator = "detail_comment_generator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "detail_comment_generator", allocationSize = 1, sequenceName = "detail_comment_seq")
	private Long id;
	
	@NotNull(message = "이름은 필수 입력입니다.")
	@Size(min = 1, message = "이름은 필수입니다")
	private String name;
	
	@NotNull(message = "코멘트는 필수 입력입니다.")
	@Size(min = 1, message = "코멘트는 필수 입력입니다.")
	private String review_con; 
	
//	@ManyToOne
//	@JoinColumn(name="detail_id")
//	private Detail detail;
}
