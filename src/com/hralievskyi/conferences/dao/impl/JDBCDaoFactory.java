package com.hralievskyi.conferences.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.dao.DaoFactory;
import com.hralievskyi.conferences.dao.EventDao;
import com.hralievskyi.conferences.dao.ReportDao;
import com.hralievskyi.conferences.dao.SpeakerDao;
import com.hralievskyi.conferences.dao.UserDao;
import com.hralievskyi.conferences.exception.DBException;
import com.hralievskyi.conferences.exception.Messages;

public class JDBCDaoFactory extends DaoFactory {
	private static final Logger LOG = Logger.getLogger(ConnectionPoolHolder.class);
	private DataSource dataSource;

	public JDBCDaoFactory() {
		try {
			dataSource = ConnectionPoolHolder.getDataSource();
		} catch (DBException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
			// TODO handle exception
		}

	}

	@Override
	public UserDao createUserDao() {
		return (UserDao) new JDBCUserDao(getConnection());
	}

	@Override
	public EventDao createEventDao() {
		return (EventDao) new JDBCEventDao(getConnection());
	}

	@Override
	public ReportDao createReportDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SpeakerDao createSpeakerDao() {
		// TODO Auto-generated method stub
		return null;
	}

	private Connection getConnection() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
