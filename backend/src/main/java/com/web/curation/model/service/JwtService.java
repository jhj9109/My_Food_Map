package com.web.curation.model.service;

import java.util.HashMap;
import java.util.Map;


public interface JwtService {

	public <T> String create(int userid, String nickName, String auth);

	public boolean checkValid(String token) throws RuntimeException;

	String get(String key) throws RuntimeException;
	
	public Map<String, Object> getting(final String jwt);

//	HashMap<String, Object> getUserInfoGoogle(String id_token) throws UnauthorizedException;
}
