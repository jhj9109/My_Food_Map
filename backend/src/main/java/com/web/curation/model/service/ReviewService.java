package com.web.curation.model.service;

import java.util.List;

import com.web.curation.model.dto.LikeDto;
import com.web.curation.model.dto.ReviewDto;

public interface ReviewService {
	public List<ReviewDto> review_list();
	public List<ReviewDto> user_review(String email);
	public void insertLike(LikeDto like);
	public void deleteLike(LikeDto like);
	public boolean searchLike(LikeDto like);
}