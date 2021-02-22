package com.hralievskyi.conferences.dao;

import java.util.List;

import com.hralievskyi.conferences.entity.Speaker;
import com.hralievskyi.conferences.entity.User;
import com.hralievskyi.conferences.exception.DBException;

public interface UserDao extends GenericDao<User> {
	User findUserByUserName(String username) throws DBException;

	List<Speaker> findAllSpeakers();
}
