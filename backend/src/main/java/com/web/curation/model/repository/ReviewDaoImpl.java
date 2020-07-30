package com.web.curation.model.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.model.dto.LikeDto;
import com.web.curation.model.dto.ReviewDto;

@Repository
public class ReviewDaoImpl implements ReviewDao {

	@Autowired
	SqlSession session;
	
	@Override
	public List<ReviewDto> review_list() {
		return session.selectList("review.selectAll");
	}
	
	@Override
	public List<ReviewDto> user_review(String email) {
		return session.selectList("review.selectUser", email);
	}

	@Override
	public void insertLike(LikeDto like) {
		session.insert("review.insertLike", like);
	}

	@Override
	public void deleteLike(LikeDto like) {
		session.delete("review.deleteLike", like);
	}
	
	@Override
	public LikeDto searchLike(LikeDto like) {
		like.setUid(Integer.parseInt(like.getUserid()));

		return session.selectOne("review.searchLike", like);
	}
}
