package com.hralievskyi.conferences.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.hralievskyi.conferences.entity.User;

public class UserMapper implements ObjectMapper<User> {

	@Override
	public User extractFromResultSet(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getLong("id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setRoleId(rs.getInt("role_id"));
		return user;
	}

	@Override
	public User makeUnique(Map<Long, User> cache, User user) {
		cache.putIfAbsent(user.getId(), user);
		return cache.get(user.getId());
	}

}
