package com.web.curation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.RequestBody;
>>>>>>> 383c6138f0bede7017bc4921ff9861d0bd31cee0
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.dto.LikeDto;
import com.web.curation.model.dto.ReviewDto;
import com.web.curation.model.service.ReviewService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@EnableAutoConfiguration
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
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
	
	@ApiOperation(value = "전체 리뷰 조회")
	@RequestMapping(value = "/review/list", method = RequestMethod.POST)
	public ResponseEntity<List<ReviewDto>> ReviewList() throws Exception {
		List<ReviewDto> review_list = reviewService.review_list();
		return new ResponseEntity(review_list, HttpStatus.OK);
	}

	@ApiOperation(value="사용자별 리뷰 조회")
	@RequestMapping(value="/review/{email}", method=RequestMethod.GET)
	public ResponseEntity<List<ReviewDto>> UserReview(@PathVariable String email) throws Exception {
		List<ReviewDto> user_review = reviewService.user_review(email);
		return new ResponseEntity(user_review, HttpStatus.OK);
	}

	@ApiOperation(value="좋아요")
	@RequestMapping(value="/review/like", method=RequestMethod.POST)
<<<<<<< HEAD
	public ResponseEntity<Map<String, Object>> insertUser(int reviewid, int userid) throws Exception {
		LikeDto dto = new LikeDto();
		dto.setReviewid(reviewid);
		dto.setUserid(userid);
		boolean check = reviewService.searchLike(dto);
		if (check) {
			reviewService.deleteLike(dto);
			return Success("Like -1");
		} else {
			reviewService.insertLike(dto);
=======
	public ResponseEntity<Map<String, Object>> insertUser(@RequestBody LikeDto like) throws Exception {
		LikeDto dto = new LikeDto();

		
		System.out.println(like);
		boolean check = reviewService.searchLike(like);
		if (check) {
			reviewService.deleteLike(like);
			return Success("Like -1");
		} else {
			reviewService.insertLike(like);
>>>>>>> 383c6138f0bede7017bc4921ff9861d0bd31cee0
			return Success("Like +1");
		}
	}
}