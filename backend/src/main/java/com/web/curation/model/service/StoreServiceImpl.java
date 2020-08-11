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

	@Override
	public List<RestaurantsDto> image(List<RestaurantsDto> list) {
		System.out.println(list);
		int leng = list.size();
		boolean check = false;
		for (int i = 0; i < leng; i++) {
			String temp = list.get(i).getRes_type();
			switch (temp) {
			case "감성주점":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/bar1.jpg");
				else
					list.get(i).setImage("resimg/bar2.jpg");
				break;
			case "경양식":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/dishes1.jpg");
				else
					list.get(i).setImage("resimg/dishes2.jpg");
				break;
			case "과자점":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/bakery.jpg");
				else
					list.get(i).setImage("resimg/bakery2.jpg");
				break;
			case "기타":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/etc.png");
				else
					list.get(i).setImage("resimg/etc2.jpg");
				break;
			case "기타 휴게음식점":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/restingetc.jpg");
				else
					list.get(i).setImage("resimg/restingetc2.jpg");
				break;
			case "김밥(도시락)":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/gimbap.jpg");
				else
					list.get(i).setImage("resimg/gimbap2.png");
				break;
			case "카페":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/cafe.jpg");
				else
					list.get(i).setImage("resimg/cafe2.jpg");
				break;
			case "다방":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/dabang.jpg");
				else
					list.get(i).setImage("resimg/dabang2.jpg");
				break;
			case "라이브카페":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("esimg/livecafe.jpg");
				else
					list.get(i).setImage("resimg/livecafe2.jpg");
				break;
			case "백화점":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/department.jpg");
				else
					list.get(i).setImage("resimg/department2.jpg");
				break;
			case "분식":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/bunsick.jpg");
				else
					list.get(i).setImage("resimg/bunsick2.jpg");
				break;
			case "뷔페식":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/buffet.jpg");
				else
					list.get(i).setImage("resimg/buffet2.png");
				break;
			case "식육(숯불구이)":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/meat.jpg");
				else
					list.get(i).setImage("resimg/meat2.jpeg");
				break;
			case "외국음식전문점(인도태국등)":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/india.jpg");
				else
					list.get(i).setImage("resimg/india2.jpg");
				break;
			case "일반조리판매":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/common.jpg");
				else
					list.get(i).setImage("resimg/common2.jpg");
				break;
			case "일식":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/japanese.jpg");
				else
					list.get(i).setImage("resimg/japanese2.png");
				break;
			case "전통찻집":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/teashop.jpg");
				else
					list.get(i).setImage("resimg/teashop2.jpg");
				break;
			case "정종대포집소주방":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/soju.jpeg");
				else
					list.get(i).setImage("resimg/soju2.jpeg");
				break;
			case "중국식":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/china.jpg");
				else
					list.get(i).setImage("resimg/china2.jpg");
				break;
			case "커피숍":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/coffee.jpg");
				else
					list.get(i).setImage("resimg/coffee2.jpg");
				break;
			case "통닭(치킨)":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/chicken.jpg");
				else
					list.get(i).setImage("resimg/chicken2.png");
				break;
			case "패스트푸드":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/fastfood.jpg");
				else
					list.get(i).setImage("resimg/fastfood2.jpg");
				break;
			case "한식":
				if (list.get(i).getIdrestaurants() % 2 == 0)
					check = true;
				else
					check = false;

				if (check)
					list.get(i).setImage("resimg/korean.jpg");
				else
					list.get(i).setImage("resimg/korean2.png");
				break;
			default:
				list.get(i).setImage("resimg/korean.jpg");
			}
		}
		return list;
	}

}
