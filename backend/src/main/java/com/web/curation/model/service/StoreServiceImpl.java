package com.web.curation.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.web.curation.model.dto.RestaurantsDto;
import com.web.curation.model.dto.ReviewDto;
import com.web.curation.model.repository.StoreDao;

@Service
public class StoreServiceImpl implements StoreService{

	@Autowired
	private StoreDao storeDao;
	
	@Override
	public List<ReviewDto> searchAllreview() {
		return storeDao.searchAllreview();
	}

	@Override
	public List<RestaurantsDto> searchAll() {
		return storeDao.searchAll();
	}

	@Override
	public RestaurantsDto search(int no) {
		return storeDao.search(no);
	}

	@Override
	public List<ReviewDto> searchreview(int no) {
		return storeDao.searchreview(no);
	}

	@Override
	public int register(RestaurantsDto dto) {
		return storeDao.register(dto);
	}

}
