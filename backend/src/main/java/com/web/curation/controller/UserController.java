package com.web.curation.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

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
import com.web.curation.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@EnableAutoConfiguration
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ExceptionHandler
	private ResponseEntity<Map<String, Object>> Success(Object data){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("state", "ok");
		resultMap.put("message", data);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	private ResponseEntity<Map<String, Object>> Success(Object data,String token){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("state", "ok");
		resultMap.put("message", data);
		resultMap.put("JWT",token);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	private ResponseEntity<Map<String, Object>> Fail(Object data, HttpStatus status) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("state",  "no");
		resultMap.put("message",  data);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation("회원가입 id,이메일 중복불가")
	@PostMapping("/user/join")
	public ResponseEntity<Map<String, Object>> signUp(@RequestBody MemberDto user){
		try {
			if(userService.create(user)) {
				return Success("회원가입에 성공하셨습니다.");
			}else {
				return Fail("시스템 오류로 회원가입이 불가능합니다.", HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return Fail(e.toString(), HttpStatus.OK);
		}
	}
	

	
	@ApiOperation("로그인")
	@PostMapping("/user/login")
	public ResponseEntity<MemberDto> Login(@RequestBody MemberDto dto) throws Exception {

	 	
	    System.out.println(dto.getEmail());
	    MemberDto result = userService.rlogin(dto.getEmail(), dto.getPassword());
	    return new ResponseEntity<MemberDto>(result, HttpStatus.OK);
	  }


	@ApiOperation("user 업데이트. ")
	@PutMapping("/user")
	public ResponseEntity<Map<String,Object>> update(@RequestBody MemberPwDto user){
		try {
			String msg =userService.update(user);
			if(msg.equals("OK")){
				return Success("회원정보 변경");
			}else {
				return Fail(msg,HttpStatus.OK);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return Fail("회원정보 변경 실패",HttpStatus.OK);
		}
		
	}
	
	@ApiOperation("닉네임 중복확인")
	@PutMapping("/user/checkNickname")
	public ResponseEntity<Map<String,Object>> checkNickname(@RequestBody String nickName){
		if(userService.ChecknickName(nickName) ){
			return Success("사용가능한 닉네임입니다.");
		}else {
			return Fail("이미 사용중인 닉네임 입니다.",HttpStatus.OK);
		}
	}
	
	@ApiOperation("회원 탈퇴")
	@PutMapping("/user/delete")
	public ResponseEntity<Map<String,Object>> signOut(@RequestBody MemberDto user){
		System.out.println(user);
		if(userService.signOut(user.getEmail(),user.getPassword()) ){
			return Success("회원 탈퇴 성공");
		}else {
			return Fail("회원 탈퇴 실패.",HttpStatus.OK);
		}
	}
	
	@ApiOperation("비밀번호 변경, ")
	@PutMapping("/user/changepw")
	public ResponseEntity<Map<String,Object>> changePW( @RequestBody MemberPwDto user){
		if(user.getNewpassword().equals(user.getPassword())) {
			return Fail("변경할 비밀번호와 바꿀 비밀번호가 같습니다.",HttpStatus.OK);
		}
		try {
			if(userService.changePw(user)) {
				return Success("비밀번호 변경에 성공하셨습니다.");
			}else {
				return Fail("이전 비밀번호를 다시 입력해주세요.",HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Fail("이전 비밀번호를 다시 입력해주세요.",HttpStatus.OK);
	}
	
    @ApiOperation(value = "id로 정보를 반환한다.", response = MemberDto.class)
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<MemberDto> findById(@PathVariable String id) throws Exception {
    	System.out.println(id);
    	MemberDto dto = userService.select(id);
		if (dto==null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		System.out.println(dto);
		return new ResponseEntity<MemberDto>(dto, HttpStatus.OK);
	}
    
	
}