package com.hralievskyi.conferences.dao;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
	private static final Logger LOG = Logger.getLogger(DaoFactory.class);
	private static DaoFactory daoFactory;

	public abstract UserDao createUserDao();

	public abstract EventDao createEventDao();

	public abstract ReportDao createReportDao();

	public abstract SpeakerDao createSpeakerDao();

	public static DaoFactory getInstance() {
		LOG.debug("DaoFactory getInstance starts");
		if (daoFactory == null) {
			synchronized (DaoFactory.class) {
				LOG.debug("if daoFactory == null starts");
				if (daoFactory == null) {
					LOG.debug("if daoFactory(second) == null starts");
					DaoFactory temp = new JDBCDaoFactory();
					daoFactory = temp;
				}
			}
		}
		return daoFactory;
	}
}
