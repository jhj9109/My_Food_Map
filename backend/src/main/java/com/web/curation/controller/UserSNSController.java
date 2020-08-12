package com.web.curation.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.web.curation.api.KakaoAPI;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins="{*}", maxAge=6000)
@RestController
@Api(value="SSAFY SNS", description="SSAFY SNS")
@EnableAutoConfiguration
public class UserSNSController {

	//@Autowired
	//private UserSNSService userSNSService;
	
	@Autowired
	private KakaoAPI kakao;
	


	@ExceptionHandler
	public ResponseEntity<Map<String, Object>> handler(Exception e){
		return handleFail(e.getMessage(), HttpStatus.OK);
	}
	
	private ResponseEntity<Map<String, Object>> handleSuccess(Object data,String token){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("state", "ok");
		resultMap.put("message", data);
		resultMap.put("JWT",token);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}
	
	private ResponseEntity<Map<String, Object>> handleFail(Object data, HttpStatus status) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("state",  "fail");
		resultMap.put("message",  data);
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}


//	@ApiOperation("SNS로그인 시 회원가입할때, 기존이랑 같으나 userforSNS 객체엔 seq가 추가되어있다.")
//	@PostMapping("/user/signUpWithSeq")
//	public ResponseEntity<Map<String, Object>> signUpWithSeq(@RequestBody UserForSNS userForSNS){
//		
//		try {
//			// 회원가입 부분에서!
//			if(ssafyCertificationService.isPassed(userForSNS.getId())){
//				// user의 ssafy 인증 컬럼값을 true로 바꿔줌
//				
//				userForSNS.setApproval(1);
//			}
//			
//			userSNSService.signUpWithSeq(userForSNS);
//			
//			return handleSuccess("가입 성공");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return handleFail("가입 실패", HttpStatus.OK);
//		}
//		
//	}
//	
//	@ApiOperation("sns로그인 후 db에 있는 기존의 id와 연동/ 연동후 테스트도 가능할듯~")
//	@PostMapping("/user/IntegrateWithId")
//	public ResponseEntity<Map<String, Object>> IntegrateWithId(@RequestBody UserForSNS userForSNS){
//		
//		try {
//			userSNSService.IntegrateWithId(userForSNS);
//			return handleSuccess("연동 성공");
//		} catch (Exception e) {
//			e.printStackTrace();
//			return handleFail("연동 실패", HttpStatus.OK);
//		}
//		
//	}
//
//	
////  @RequestMapping(value="/KakaoLogin")
//    @ApiOperation("KakaoLogin 버튼")
//   	@GetMapping("/userSNS/kakaoLogin/{code}")
//    public ResponseEntity<Map<String, Object>> klogin(@PathVariable String code, HttpSession session) {
//    	System.out.println("====================login=====================");
//        String access_Token = kakao.getAccessToken(code);
//        HashMap<String, Object> userInfo = kakao.getUserInfo(access_Token);
//        System.out.println("login Controller : " + userInfo);
//        System.out.println("====================login=====================");
//        //    클라이언트의 이메일이 존재할 때 세션에 해당 이메일과 토큰 등록
//        if (userInfo.get("snsid") != null) {
//            session.setAttribute("userId", userInfo.get("snsid"));
//            session.setAttribute("access_Token", access_Token);
//        }
//        
//        String snsid = userInfo.get("snsid").toString();
//        System.out.println(userInfo.get("snsid").toString());
//        System.out.println(userInfo.get("nickname").toString());
//        
//        try {
//			Object valueForReturn = userSNSService.SNSLogin(snsid, "kakao");
//			
//			if(valueForReturn instanceof String) {
//				return handleSuccess("소셜 로그인 토큰 발급 완료.", valueForReturn.toString());
//			}else if(valueForReturn instanceof Integer) {
//				return handleFail("서비스를 이용하시려면 연동이 필요합니다.",Integer.parseInt(valueForReturn.toString()), HttpStatus.OK);
//			}else {
//				return handleFail("리턴값이 String이나 Integer가 아닙니다.", HttpStatus.OK);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//			return handleFail("소셜 로그인 중 오류 발생", HttpStatus.OK);
//		}
//        
//    }
//
}
