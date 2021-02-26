package com.hralievskyi.conferences.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.dao.DaoFactory;
import com.hralievskyi.conferences.dao.ReportDao;
import com.hralievskyi.conferences.entity.Report;

public class ReportService {
	private static final Logger LOG = Logger.getLogger(ReportService.class);
	DaoFactory daoFactory = DaoFactory.getInstance();

	public void createReport(Report report) throws Exception {
		LOG.debug("starts");
		try (ReportDao dao = daoFactory.createReportDao()) {
			dao.create(report);
		}
	}

	public List<Report> findNewReportsFor(long eventid) throws Exception {
		LOG.debug("starts");
		try (ReportDao dao = daoFactory.createReportDao()) {
			return dao.findNewReportsFor(eventid);
		}
	}

	public List<Report> findAll() throws Exception {
		LOG.debug("starts");
		try (ReportDao dao = daoFactory.createReportDao()) {
			return dao.findAll();
		}
	}

	public void update(Report report) throws Exception {
		LOG.debug("starts");
		try (ReportDao dao = daoFactory.createReportDao()) {
			dao.update(report);
		}
	}

	public List<Report> findBySpeakerId(long speakerId) throws Exception {
		LOG.debug("starts");
		try (ReportDao dao = daoFactory.createReportDao()) {
			return dao.findAllBySpeakerId(speakerId);
		}
	}

	public void setAccepted(long reportId) throws Exception {
		LOG.debug("starts");
		try (ReportDao dao = daoFactory.createReportDao()) {
			dao.setAccepted(reportId);
		}
	}

	public void clearSpeaker(long reportId) throws Exception {
		LOG.debug("starts");
		try (ReportDao dao = daoFactory.createReportDao()) {
			dao.clearSpeaker(reportId);
		}
	}

	public void acceptSuggested(long reportId) throws Exception {
		LOG.debug("starts");
		try (ReportDao dao = daoFactory.createReportDao()) {
			dao.acceptSuggested(reportId);
		}
	}

	public void delete(long reportId) throws Exception {
		LOG.debug("starts");
		try (ReportDao dao = daoFactory.createReportDao()) {
			dao.delete(reportId);
		}
	}

}
