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
	private static final Logger LOG = Logger.getLogger(JDBCDaoFactory.class);
	private DataSource dataSource;

	public JDBCDaoFactory() {
		LOG.debug("constructor");
		try {
			dataSource = ConnectionPoolHolder.getDataSource();
		} catch (DBException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
			// TODO handle exception
		}

	}

	@Override
	public UserDao createUserDao() {
		LOG.debug("starts");
		return (UserDao) new JDBCUserDao(getConnection());
	}

	@Override
	public EventDao createEventDao() {
		LOG.debug("starts");
		return (EventDao) new JDBCEventDao(getConnection());
	}

	@Override
	public ReportDao createReportDao() {
		LOG.debug("starts");
		return (ReportDao) new JDBCReportDao(getConnection());
	}

	@Override
	public SpeakerDao createSpeakerDao() {
		// TODO Auto-generated method stub
		return null;
	}

	private Connection getConnection() {
		try {
			LOG.debug("starts with dataSource: " + dataSource);
			return dataSource.getConnection();
		} catch (SQLException e) {
			LOG.error("getConnection() error", e);
			throw new RuntimeException(e);
		}
	}

}
