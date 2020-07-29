package com.web.curation.model.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.web.curation.model.dto.FollowDto;

@Repository
public class FollowDaoImpl implements FollowDao {
	
	@Autowired
	SqlSession session;
	
	@Override
	public void insertFollow(FollowDto follow) {
		session.insert("user.insertFollow", follow);
	}

	@Override
	public void deleteFollow(FollowDto follow) {
		session.delete("user.deleteFollow", follow);
	}
	
	@Override
	public FollowDto searchFollow(FollowDto follow) {
		return session.selectOne("user.searchFollow", follow);
	}
}