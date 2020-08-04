package com.web.curation.model.service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import com.web.curation.model.dto.FollowDto;
import com.web.curation.model.dto.MemberDto;
import com.web.curation.model.dto.MemberPwDto;

public interface UserService {

	public MemberDto login(String userid, String userpwd) throws Exception;
	public void join(MemberDto member) throws Exception;
	public boolean changePw(MemberPwDto user) throws Exception;
	public String update(MemberPwDto user) throws UnsupportedEncodingException;
	public boolean ChecknickName(String nickName);
	public boolean signOut(String id, String pw);
	public MemberDto select(String userid) throws SQLException;
	public String email(String id) throws Exception;
	
	// follow
	public void insertFollow(FollowDto follow);
	public void deleteFollow(FollowDto follow);
	public boolean searchFollow(FollowDto follow) throws Exception;
}
