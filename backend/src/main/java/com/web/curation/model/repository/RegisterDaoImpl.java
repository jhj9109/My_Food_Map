package com.web.curation.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.model.dto.MemberDto;


@Repository
public class RegisterDaoImpl implements RegisterDao {

	@Autowired
	SqlSession session;
	
	@Override
	public MemberDto select(int id) throws SQLException {
		MemberDto info = session.selectOne("user.select", id);
		return info;
	}

	@Override
	public void join(MemberDto info) {
		session.insert("user.insert", info);
	}

	@Override
	public MemberDto selectone(int userid) throws SQLException {
		MemberDto info = session.selectOne("user.selectone", userid);
		return info;
	}

	@Override
	public MemberDto selectByEmail(String email) throws SQLException {
		MemberDto info = session.selectOne("user.selectByEmail", email);
		return info;
	}

	@Override
	public void delete(MemberDto member) throws SQLException {
		session.delete("user.delete",member);
		
	}

	@Override
	public void update(MemberDto member) throws SQLException {
		session.update("user.update",member);
	}

	@Override
	public MemberDto selectonenickname(String nickname) throws SQLException {
		MemberDto user = session.selectOne("user.nickname",nickname);
		return user;
	}
	
}
