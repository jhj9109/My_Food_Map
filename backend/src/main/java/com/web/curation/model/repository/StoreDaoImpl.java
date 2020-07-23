package com.web.curation.model.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.web.curation.model.dto.ReviewDto;

public class StoreDaoImpl implements StoreDao {
	@Autowired
	SqlSession session;

	@Override
	public List<ReviewDto> searchAll() {
		return session.selectList("review.selectlist");
	}
	
	
}
