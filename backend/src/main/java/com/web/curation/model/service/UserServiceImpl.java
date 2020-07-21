package com.web.curation.model.service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.curation.exception.MyException;
import com.web.curation.model.dto.MemberDto;
import com.web.curation.model.dto.MemberPwDto;
import com.web.curation.model.repository.RegisterDao;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	RegisterDao userRepository;


	
	@Override
	public boolean create(MemberDto user) throws Exception {
			userRepository.join(user);
			return true;
	}
	
	@Override
	public String login(String id,String pw) throws Exception {
		MemberDto user =userRepository.select(id);
			if(user==null) {
				throw new RuntimeException("등록되지 않은 회원입니다.");
			}else {
				if(user.getEmail()==null) {
					throw new EntityNotFoundException("등록되지 않은 회원입니다.");
				}else {
					if(pw.equals(user.getPassword())) {
						return user.getEmail();
					}else {
						throw new MyException("비밀번호 오류");
					}
				}
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
	public MemberDto rlogin(String id, String pw) throws Exception {
		MemberDto info =  userRepository.select(id);
		if (info != null && info.getPassword().equals(pw)) {
			return info;
		} else {
			return null;
		}
	}

	@Override
	public MemberDto select(String userid) throws SQLException {
		return userRepository.select(userid);
	}
}
