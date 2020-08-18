package com.web.curation.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.model.dto.CommentDto;
import com.web.curation.model.repository.CommentDao;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;
	
	@Override
	public List<CommentDto> comment_list(int reviewId) {
		return commentDao.list(reviewId);
	}

	@Override
	public boolean create_comment(CommentDto comment) throws Exception {
		try {
			commentDao.create(comment);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("리뷰 작성 오류");
		}
	}

	@Override
	public boolean delete_comment(CommentDto comment) throws Exception {
		try {
			commentDao.delete(comment);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			throw new Exception("리뷰 삭제 오류");
		}
	}
}
