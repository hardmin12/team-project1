package com.oraclejava.repository;



import org.springframework.data.repository.PagingAndSortingRepository;

import com.oraclejava.model.DetailComment;




public interface DetailCommentRepository extends PagingAndSortingRepository<DetailComment, Long> {

}
