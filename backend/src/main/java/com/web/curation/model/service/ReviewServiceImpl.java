package com.web.curation.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.model.dto.LikeDto;
import com.web.curation.model.dto.ReviewDto;
import com.web.curation.model.repository.ReviewDao;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDao reviewDao;
	
	@Override
	public List<ReviewDto> review_list() {
		return reviewDao.review_list();
	}

	@Override
	public List<ReviewDto> user_review(int userId) {
		return reviewDao.user_review(userId);
	}

	@Override
	public void insertLike(LikeDto like) {
		reviewDao.insertLike(like);
	}

	@Override
	public void deleteLike(LikeDto like) {
		reviewDao.deleteLike(like);
	}

	@Override
	public boolean searchLike(LikeDto like) {
		LikeDto dto = reviewDao.searchLike(like);
		if (dto==null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean register(ReviewDto dto) throws Exception {
		try {
			reviewDao.register(dto);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("리뷰 작성 오류");
		}
	}
	
}
