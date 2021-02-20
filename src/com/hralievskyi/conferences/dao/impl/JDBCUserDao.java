package com.hralievskyi.conferences.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.dao.UserDao;
import com.hralievskyi.conferences.dao.mapper.UserMapper;
import com.hralievskyi.conferences.entity.User;
import com.hralievskyi.conferences.exception.DBException;
import com.hralievskyi.conferences.exception.Messages;

public class JDBCUserDao extends JDBCGenericDao<User> implements UserDao {
	private static final Logger LOG = Logger.getLogger(JDBCUserDao.class);

	public JDBCUserDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void create(User user) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement("INSERT INTO users VALUES(DEFAULT, ?, ?, ?);");
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getRoleId());
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			rollback();
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			closeAll(pstmt, rs);
		}
	}

	@Override
	public User findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		Map<Long, User> users = new HashMap<>();

		final String query = "SEKECT * FROM users";
		try (Statement st = connection.createStatement()) {
			ResultSet rs = st.executeQuery(query);
			UserMapper userMapper = new UserMapper();

			while (rs.next()) {
				User user = userMapper.extractFromResultSet(rs);
				user = userMapper.makeUnique(users, user);
			}
			return new ArrayList<>(users.values());
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_USERS, ex);
			return null;
		}
	}

	@Override
	public void update(User entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() throws Exception {
		closeConn();

	}

	@Override
	public User findUserByUserName(String username) throws DBException {
		User user = null;
		UserMapper userMapper = new UserMapper();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement("SELECT * FROM users WHERE username=?");
			pstmt.setString(1, username);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = userMapper.extractFromResultSet(rs);
			}
		} catch (SQLException ex) {
			rollback();
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			closeAll(pstmt, rs);
		}
		return user;
	}

}
