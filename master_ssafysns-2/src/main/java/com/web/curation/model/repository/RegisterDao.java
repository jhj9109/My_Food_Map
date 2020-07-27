package com.web.curation.model.repository;

import java.sql.SQLException;
import java.util.List;

import com.web.curation.model.dto.MemberDto;


public interface RegisterDao {
	public MemberDto select(String userid) throws SQLException;
	public MemberDto selectone(String userid) throws SQLException;
	public MemberDto selectonenickname(String nickname) throws SQLException;
	public void join(MemberDto member) throws SQLException;
	public void delete(MemberDto member) throws SQLException;
	public void update(MemberDto member) throws SQLException;

}
