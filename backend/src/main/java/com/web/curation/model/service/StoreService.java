package com.web.curation.model.service;

import java.util.List;

import com.web.curation.model.dto.RestaurantsDto;
import com.web.curation.model.dto.ReviewDto;

public interface StoreService {
	public List<ReviewDto> searchAllreview();
	public List<RestaurantsDto> searchAll();
	public RestaurantsDto search(int no);
	public List<ReviewDto> searchreview(int no);
	public int register(RestaurantsDto dto);

}
