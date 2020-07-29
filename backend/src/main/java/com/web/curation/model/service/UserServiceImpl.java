package com.web.curation.model.service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.curation.model.dto.FollowDto;
import com.web.curation.model.dto.MemberDto;
import com.web.curation.model.dto.MemberPwDto;
import com.web.curation.model.repository.FollowDao;
import com.web.curation.model.repository.RegisterDao;
import com.web.curation.util.MailUtil;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	RegisterDao userRepository;
	FollowDao followDao;

	@Override
	public void join(MemberDto member) throws Exception {
			userRepository.join(member);
	}
	
	@Override
	public MemberDto login(String id,String pw) throws Exception {
		MemberDto info =userRepository.select(id);
		System.out.println(info);
		System.out.println(pw);
		if (info != null && info.getPassword().equals(pw)) {
			return info;
		} else {
			return null;
		}
	}
	@Override
	public boolean signOut(String id, String pw) {
		try {
			MemberDto finduser = userRepository.select(id);
			if(pw.equals(finduser.getPassword())) {
				userRepository.delete(finduser);
				return true;
			}else {
				throw new SQLException("잘못된 비밀번호입니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String update(MemberPwDto user) throws UnsupportedEncodingException {
	try {
			
			MemberDto finduser = userRepository.select(user.getEmail());

			if(user.getNickname()!=null&&!user.getNickname().equals("")) {
				finduser.setNickname(user.getNickname());
			}
			if(user.getNewpassword()!=null&&!user.getNewpassword().equals("")) {
				if(user.getPassword().equals(user.getNewpassword())) {
					throw new Exception("password가 같습니다.");
				}else {
					finduser.setPassword(user.getNewpassword());
				}
			}
			System.out.println(finduser);
			userRepository.update(finduser);
			return "OK";
		} catch (Exception e) {
			e.printStackTrace();
			return "오류 발생";
		}
	}

	@Override
	public boolean ChecknickName(String nickName) {
		try {
			if(userRepository.selectonenickname(nickName)==null) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean changePw(MemberPwDto user) throws Exception {
		try {
			MemberDto finduser = userRepository.select(user.getEmail());

			if (user.getNickname() != null && !user.getNickname().equals("")) {
				finduser.setNickname(user.getNickname());
			}
			if (user.getNewpassword() != null && !user.getNewpassword().equals("")) {
				if (user.getPassword().equals(user.getNewpassword())) {
					throw new Exception("password가 같습니다.");
				} else {
					finduser.setPassword(user.getNewpassword());
				}
			}
			userRepository.update(finduser);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public MemberDto select(String userid) throws SQLException {
		return userRepository.select(userid);
	}

	@Override
	public String email(String id) throws Exception {
		MailUtil mu = new MailUtil();

		String code = mu.CreateAuthCode();// 이메일 인증 코드 생성부
		String subject = "[SSAFY SNS] 이메일 인증 코드 입니다. ";
		StringBuffer sbuff = new StringBuffer();
		sbuff.append("<div align='center' style='border:1px solid black; font-family:verdana'>")
				.append("<h3 style='font-size: 130%'> SSAFY SNS 이메일 인증 코드를 안내해 드리겠습니다.</h3>")
				.append("<div style='font-size: 130%'> SSAFY SNS 이메일 인증 코드는 <strong>").append(code)
				.append("</strong> 입니다.</div> <br/></div>");
		String msg = sbuff.toString();

		if (userRepository.select(id) != null) {
			try {
				mu.sendMail(id, subject, msg);
				return code;
			} catch (Exception e1) {
				e1.printStackTrace();
				throw new Exception();
			}
		}
		return null;
	}
	
	// follow

	@Override
	public void insertFollow(FollowDto follow) {
		followDao.insertFollow(follow);
	}

	@Override
	public void deleteFollow(FollowDto follow) {
		followDao.deleteFollow(follow);
	}

	@Override
	public boolean searchFollow(FollowDto follow) {
		FollowDto dto = followDao.searchFollow(follow);
		if (dto==null) {
			return false;
		} else {
			return true;
		}
	}

}
