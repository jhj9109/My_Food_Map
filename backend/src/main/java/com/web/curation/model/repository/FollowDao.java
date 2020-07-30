package com.web.curation.model.repository;

import com.web.curation.model.dto.FollowDto;

public interface FollowDao {
	public void insertFollow(FollowDto follow);
	public void deleteFollow(FollowDto follow);
	public FollowDto searchFollow(FollowDto follow);
}
