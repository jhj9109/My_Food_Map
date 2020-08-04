package com.web.curation.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.exception.MyException;
import com.web.curation.model.dto.MemberDto;
import com.web.curation.model.dto.MemberPwDto;
import com.web.curation.model.dto.RestaurantsDto;
import com.web.curation.model.dto.ReviewDto;
import com.web.curation.model.service.StoreService;
import com.web.curation.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@EnableAutoConfiguration
public class StoreController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private StoreService storeservice;
	
	private String loginid;

	@ExceptionHandler
	private ResponseEntity<Map<String, Object>> Success(Object data) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("state", "ok");
		resultMap.put("message", data);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	private ResponseEntity<Map<String, Object>> Fail(Object data, HttpStatus status) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("state", "no");
		resultMap.put("message", data);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
  	@ApiOperation(value="해당 음식점 모든 리뷰 조회 서비스", response=List.class)
	@RequestMapping(value = "/restaurants/{idrestarunts}/reviews", method = RequestMethod.GET)
  	public ResponseEntity<Map<String,Object>> listSearch(@PathVariable int idrestaurnts)  throws Exception {
  		
  		List<ReviewDto> list=null;
  		list=storeservice.searchreview(idrestaurnts);
  		
  		if (list==null || list.size()==0) {
  			return Fail("no",HttpStatus.NO_CONTENT);
  		}
  		return Success(list);
	}
    
  	
	 @ApiOperation(value = "restaurants 번호로 idrestaurants 의 정보를 찾는다.", response = List.class)
		@RequestMapping(value = "/restaurants/{idrestarunts}", method = RequestMethod.GET)
		public ResponseEntity<Map<String,Object>> findResByNo(@PathVariable int idrestaurnts) throws Exception {
		 	RestaurantsDto one = storeservice.search(idrestaurnts);
	    	System.out.println(one);
	    	
			if (one==null || one.getIdrestaurants()==0) {
				return Fail("no",HttpStatus.NO_CONTENT);
			}
			return Success(one);
		}
    
    
    @ApiOperation(value = "음식점 list를 받아온다 ", response = List.class)
   	@RequestMapping(value = "/restaurants", method = RequestMethod.POST)
   	public ResponseEntity<Map<String,Object>> listRes() throws Exception {
    	
   		List<RestaurantsDto> list = storeservice.searchAll();
   		System.out.println(list);
   		
   		return Success(list);
   	}
}