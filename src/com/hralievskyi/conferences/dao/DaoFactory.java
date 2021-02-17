package com.hralievskyi.conferences.dao;

import com.hralievskyi.conferences.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
	private static DaoFactory daoFactory;

	public abstract UserDao createUserDao();

	public abstract EventDao createEventDao();

	public abstract ReportDao createReportDao();

	public abstract SpeakerDao createSpeakerDao();

	public static DaoFactory getInstance() {
		if (daoFactory == null) {
			synchronized (DaoFactory.class) {
				if (daoFactory == null) {
					DaoFactory temp = new JDBCDaoFactory();
					daoFactory = temp;
				}
			}
		}
		return daoFactory;
	}
}
