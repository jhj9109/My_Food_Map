package com.web.curation.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.web.curation.model.dto.ReviewDto;
import com.web.curation.model.repository.StoreDao;

@Service
public class StoreServiceImpl implements StoreService{

	@Autowired
	private StoreDao storeDao;
	
	@Override
	public List<ReviewDto> searchAllreview() {
		return storeDao.searchAll();
	}

}
