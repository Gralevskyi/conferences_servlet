package com.hralievskyi.conferences.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.dao.DaoFactory;
import com.hralievskyi.conferences.dao.UserDao;
import com.hralievskyi.conferences.dao.impl.JDBCUserDao;
import com.hralievskyi.conferences.entity.User;
import com.hralievskyi.conferences.exception.DBException;
import com.hralievskyi.conferences.exception.Messages;

public class UserService {
	private static final Logger LOG = Logger.getLogger(JDBCUserDao.class);
	private DaoFactory daoFactory = DaoFactory.getInstance();

	public boolean creteUser(User user) throws DBException {
		try (UserDao dao = daoFactory.createUserDao()) {
			dao.create(user);
			return true;
		} catch (Exception e) {
			LOG.error("Can not create user");
			throw new DBException(Messages.ERR_CAN_NOT_CREATE_USER, e);
		}
	}

	public List<User> getAllUsers() throws Exception {
		try (UserDao dao = daoFactory.createUserDao()) {
			return dao.findAll();
		}
	}

	public User findUserByUsername(String username) {
		User user = null;
		try (UserDao dao = daoFactory.createUserDao()) {
			return dao.findUserByUserName(username);
		} catch (Exception e) {
			LOG.error("can not find user by user name");
		}
		return user;
	}
}
