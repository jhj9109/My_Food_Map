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

	@ApiOperation(value = "모든 리뷰 조회")
	@RequestMapping(value ="review/list", method = RequestMethod.POST)
	public ResponseEntity<List<ReviewDto>> listReview() {
		List<ReviewDto> list = null;
		list = storeservice.searchAllreview();
		
		return new ResponseEntity(list,HttpStatus.OK);
		
	}
	@ApiOperation(value = "로그인.")
	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public ResponseEntity<MemberDto> Login(@RequestBody MemberDto dto) throws Exception {
		System.out.println(dto.getEmail());
		MemberDto result = userService.login(dto.getEmail(), dto.getPassword());
		System.out.println(result);
		loginid=result.getEmail();
		return new ResponseEntity<MemberDto>(result, HttpStatus.OK);
	}

	@ApiOperation("user 업데이트. ")
	@PutMapping("/user")
	public ResponseEntity<Map<String, Object>> update(@RequestBody MemberPwDto user) {
		try {
			String msg = userService.update(user);
			if (msg.equals("OK")) {
				return Success("회원정보 변경에 성공하셨습니다");
			} else {
				return Fail(msg, HttpStatus.OK);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return Fail("회원정보 변경 실패", HttpStatus.OK);
		}

	}

	@ApiOperation("닉네임 중복확인")
	@GetMapping("/user/checkNickname/{nickName}")
	public ResponseEntity<Map<String, Object>> checkNickname(@PathVariable String nickName) {
		System.out.println("in Controller: " + nickName);
		if (userService.ChecknickName(nickName)) {
			return Success("사용가능한 닉네임입니다.");
		} else {
			return Fail("이미 사용중인 닉네임 입니다.", HttpStatus.OK);
		}
	}

	@ApiOperation("이메일 인증 코드 생성")
	@GetMapping("/user/emailAuth/{id}")
	public ResponseEntity<Map<String, Object>> emailAuth(@PathVariable String id) {
		String code = null;
		try {
			code = userService.email(id);
			if (code == null) {
				return Fail("코드생성 실패", HttpStatus.OK);
			}
			return Success(code);

		} catch (Exception e) {
			return Fail("코드 생성 실패", HttpStatus.OK);
		}
	}

	@ApiOperation("회원 탈퇴. ")
	@PutMapping("/user/delete")
	public ResponseEntity<Map<String, Object>> signOut(@RequestBody MemberDto user) {
		if (userService.signOut(user.getEmail(), user.getPassword())) {
			return Success("회원 탈퇴 성공");
		} else {
			return Fail("비밀번호가 맞지 않습니다.", HttpStatus.OK);
		}
	}

	@ApiOperation("비밀번호 변경, ")
	@PutMapping("/user/changepw")
	public ResponseEntity<Map<String, Object>> changePW(@RequestBody MemberPwDto user) {
		if (user.getNewpassword().equals(user.getPassword())) {
			return Fail("변경할 비밀번호와 바꿀 비밀번호가 같습니다.", HttpStatus.OK);
		}
		try {
			if (userService.changePw(user)) {
				return Success("비밀번호 변경에 성공하셨습니다.");
			} else {
				return Fail("이전 비밀번호를 다시 입력해주세요.", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Fail("이전 비밀번호를 다시 입력해주세요.", HttpStatus.OK);
	}

	@ApiOperation(value = "id로 정보를 반환한다.", response = MemberDto.class)
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<MemberDto> findById(@PathVariable String id) throws Exception {
		System.out.println(id);
		MemberDto dto = userService.select(id);
		System.out.println(loginid+"로긴아디");
		if (dto == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		System.out.println(dto);
		return new ResponseEntity<MemberDto>(dto, HttpStatus.OK);
	}
	
	@ApiOperation("회원 정보 반환한다")
	@GetMapping("/user")
	public ResponseEntity<Map<String,Object>> MyInfo(){
		MemberDto user=null;
		try {
			user = userService.select(loginid);
		}catch (Exception e) {
			e.printStackTrace();
		}
		if(user==null) return Fail("오류발생", HttpStatus.OK);
		
		user.setPassword(null);
		return Success(user);
	}
	

}