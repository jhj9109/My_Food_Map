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
import org.springframework.web.bind.annotation.RequestBody;
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
	@RequestMapping(value = "/review/list/{userId}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> ReviewList(@PathVariable String userId) throws Exception {
		try {
			List<ReviewDto> review_list = reviewService.review_list();
			for (int i=0; i<review_list.size();i++) {
				LikeDto dto = null;
				ReviewDto review = review_list.get(i);
				dto.setReviewid(review.getNo());
				dto.setUserid(userId);
				review.setLike(reviewService.searchLike(dto));
			}
			return Success(review_list);
		} catch (Exception e){
			e.printStackTrace();
		}
		return Fail("리뷰 조회 실패", HttpStatus.OK);
	}

	@ApiOperation(value="사용자별 리뷰 조회")
	@RequestMapping(value="/review/{writerId}/{userId}", method=RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> UserReview(@PathVariable("writerId") String writerId, @PathVariable("userId") String userId) throws Exception {
		try {
			List<ReviewDto> user_review = reviewService.user_review(writerId);
			for (int i=0; i<user_review.size();i++) {
				LikeDto dto = null;
				ReviewDto review = user_review.get(i);
				dto.setReviewid(review.getNo());
				dto.setUserid(userId);
				review.setLike(reviewService.searchLike(dto));
			}
			return Success(user_review);
		} catch (Exception e){
			e.printStackTrace();
		}
		return Fail("리뷰 조회 실패", HttpStatus.OK);
	}

	@ApiOperation(value="좋아요")
	@RequestMapping(value="/review/like", method=RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> insertUser(@RequestBody LikeDto like) throws Exception {
		LikeDto dto = new LikeDto();
		
		System.out.println(like);
		boolean check = reviewService.searchLike(like);
		if (check) {
			reviewService.deleteLike(like);
			return Success("Like -1");
		} else {
			reviewService.insertLike(like);
			return Success("Like +1");
		}
	}
}