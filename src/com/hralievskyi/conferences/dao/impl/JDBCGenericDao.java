package com.hralievskyi.conferences.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.exception.Messages;

public abstract class JDBCGenericDao<T> {

	private static final Logger LOG = Logger.getLogger(JDBCGenericDao.class);
	private Connection connection;

	public void rollback() {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				LOG.error("Cannot rollback transaction", ex);
			}
		}
	}

	public void closeConn() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
			}
		}

	}

	public void closeSt(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
			}
		}
	}

	public void closeRs(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
			}
		}
	}

	public void closeAll(Statement stmt, ResultSet rs) {
		closeRs(rs);
		closeSt(stmt);
		closeConn();
	}

}
