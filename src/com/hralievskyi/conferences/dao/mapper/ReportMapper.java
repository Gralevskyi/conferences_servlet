package com.hralievskyi.conferences.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.hralievskyi.conferences.entity.Report;
import com.hralievskyi.conferences.entity.Speaker;

public class ReportMapper implements ObjectMapper<Report> {

	@Override
	public Report makeUnique(Map<Long, Report> cache, Report teacher) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Report extractFromResultSet(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public Report extractFromRsForEvent(ResultSet rs) throws SQLException {
		Report report = new Report();
		Speaker speaker = new Speaker();
		report.setTopicEn(rs.getString("topic_en"));
		report.setTopicUk(rs.getString("topic_uk"));
		speaker.setNameEn(rs.getString("speaker_name_en"));
		speaker.setNameUk(rs.getString("speaker_name_uk"));
		report.setSpeaker(speaker);
		return report;
	}

}
