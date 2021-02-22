package com.hralievskyi.conferences.dao;

import java.util.List;

import com.hralievskyi.conferences.entity.Report;
import com.hralievskyi.conferences.exception.DBException;

public interface ReportDao extends GenericDao<Report> {

	List<Report> findNewEventsFor(long eventid);

	List<Report> findAllBySpeakerId(long speakerId);

	void setAccepted(long reportId) throws DBException;

	void clearSpeaker(long reportId) throws DBException;

}
