package com.web.curation.model.service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import com.web.curation.model.dto.MemberDto;
import com.web.curation.model.dto.MemberPwDto;

public interface UserService {
	public String login(String id, String pw) throws Exception;
	public MemberDto rlogin(String id, String pw) throws Exception;
	public boolean create(MemberDto user) throws Exception;
	public boolean changePw(MemberPwDto user) throws Exception;
	public String update(MemberPwDto user) throws UnsupportedEncodingException;
	public boolean ChecknickName(String nickName);
	public boolean signOut(String id, String pw);
	public MemberDto select(String userid) throws SQLException;
	
}
