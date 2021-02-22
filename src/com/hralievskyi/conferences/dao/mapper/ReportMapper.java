package com.hralievskyi.conferences.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.hralievskyi.conferences.entity.Report;
import com.hralievskyi.conferences.entity.Speaker;
import com.hralievskyi.conferences.entity.User;

public class ReportMapper implements ObjectMapper<Report> {

	@Override
	public Report makeUnique(Map<Long, Report> cache, Report teacher) {
		return null;
	}

	@Override
	public Report extractFromResultSet(ResultSet rs) throws SQLException {
		Report report = new Report();
		report.setId(rs.getLong("id"));
		report.setTopicEn(rs.getString("topic_en"));
		report.setTopicUk(rs.getString("topic_uk"));
		report.setAuthor(new User(rs.getLong("author")));
		report.setAccepted(rs.getBoolean("accepted"));
		report.setSuggested(rs.getBoolean("suggested"));
		return report;
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

	public Report extractFromRsForModerator(ResultSet rs) throws SQLException {
		Report report = new Report();
		Speaker speaker = new Speaker();
		User author = new User();
		report.setId(rs.getLong("id"));
		report.setAccepted(rs.getBoolean("accepted"));
		report.setSuggested(rs.getBoolean("suggested"));
		report.setTopicEn(rs.getString("topic_en"));
		report.setTopicUk(rs.getString("topic_uk"));
		author.setId(rs.getLong("author"));
		author.setUsername(rs.getString("author_name"));
		speaker.setId(rs.getLong("speaker_id"));
		speaker.setNameEn(rs.getString("speaker_name_en"));
		speaker.setNameUk(rs.getString("speaker_name_uk"));
		report.setSpeaker(speaker);
		report.setAuthor(author);
		return report;
	}

}
