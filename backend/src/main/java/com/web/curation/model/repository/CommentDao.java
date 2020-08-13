package com.web.curation.model.repository;

import java.util.List;

import com.web.curation.model.dto.CommentDto;

public interface CommentDao {
	public List<CommentDto> list(int reviewId);
	public void create(CommentDto comment);
	public void delete(CommentDto comment);
}
