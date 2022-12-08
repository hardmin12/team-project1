package com.oraclejava.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oraclejava.repository.DetailCommentRepository;
import com.oraclejava.model.DetailComment;








@Controller
public class DetailController {


	private DetailCommentRepository detailCommentRepository;
	
	
	
	public DetailController(DetailCommentRepository detailCommentRepository) {
		super();
		this.detailCommentRepository = detailCommentRepository;
	}


	//리뷰 보기
	//누르면 상세페이지로 이동하
	@GetMapping("/a")
	public String getDetail(@ModelAttribute DetailComment detailcomment) {
		return "detail";
		
	}
	
	
	//리뷰쓰기
	@RequestMapping(value="/comment_ok", method=RequestMethod.POST)
	public String postComment(@Validated DetailComment detailComment, BindingResult result, Model model) {
		
		//DetailComment detailcomment = new DetailComment();
		
		if (result.hasErrors()) {
			return getDetail(detailComment);
		}
		
		
		detailCommentRepository.save(detailComment);
		return "detail";
		
	}
}