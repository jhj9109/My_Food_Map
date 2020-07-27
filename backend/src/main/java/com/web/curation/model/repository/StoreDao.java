package com.web.curation.model.repository;

import java.util.List;

import com.web.curation.model.dto.ReviewDto;


public interface StoreDao {
	public List<ReviewDto> searchAll();
}
