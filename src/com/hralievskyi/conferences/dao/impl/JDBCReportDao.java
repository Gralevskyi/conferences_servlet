package com.hralievskyi.conferences.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.dao.ReportDao;
import com.hralievskyi.conferences.dao.mapper.ReportMapper;
import com.hralievskyi.conferences.entity.Report;
import com.hralievskyi.conferences.exception.DBException;
import com.hralievskyi.conferences.exception.Messages;

public class JDBCReportDao extends JDBCGenericDao<Report> implements ReportDao {
	private static final Logger LOG = Logger.getLogger(JDBCReportDao.class);

	public JDBCReportDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void create(Report report) throws DBException {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		try {
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement("INSERT INTO reports VALUES(DEFAULT, ?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, report.getTopicEn());
			pstmt.setString(2, report.getTopicUk());
			pstmt.setLong(3, report.getAuthor().getId());
			pstmt.setBoolean(4, report.isAccepted());
			pstmt.setBoolean(5, report.isSuggested());
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					report.setId(rs.getLong(1));
				}
			}
			pstmt2 = connection.prepareStatement("INSERT INTO speaker_reports (speaker_id, report_id) VALUES (?, ?) ");
			pstmt2.setLong(1, report.getSpeaker().getId());
			pstmt2.setLong(2, report.getId());
			pstmt2.executeUpdate();
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException ex) {
			rollback();
			throw new DBException(Messages.ERR_CAN_NOT_CREATE_REPORT, ex);
		} finally {
			closeSt(pstmt2);
			closeRs(rs);
			closeSt(pstmt);
			closeConn();
		}

	}

	@Override
	public List<Report> findNewReportsFor(long eventid) {
		List<Report> reports = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		LOG.debug("Start retrieving all reports");
		try {
			pstmt = connection.prepareStatement("SELECT r.* FROM reports r "
					+ "LEFT JOIN event_reports er ON r.id = er.report_id WHERE r.suggested = 0 AND (er.event_id <> ?  OR er.event_id IS NULL);");
			pstmt.setLong(1, eventid);
			rs = pstmt.executeQuery();
			ReportMapper reportMapper = new ReportMapper();
			while (rs.next()) {
				Report report = reportMapper.extractFromResultSet(rs);
				reports.add(report);
			}
			LOG.debug("Retrived reports");

		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_REPORTS, ex);
		} catch (Exception ex) {
			LOG.error("ex: ", ex);
		} finally {
			closeAll(pstmt, rs);
		}
		return reports;
	}

	@Override
	public Report findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Report> findAllBySpeakerId(long speakerId) {
		List<Report> reports = new ArrayList<>();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		LOG.debug("Start retireving all reports by speakerId: " + speakerId);
		try {
			pstmt = connection.prepareStatement("SELECT r.* FROM reports r JOIN speaker_reports sr ON sr.report_id = r.id AND sr.speaker_id = ?;");
			pstmt.setLong(1, speakerId);
			rs = pstmt.executeQuery();
			ReportMapper reportMapper = new ReportMapper();
			while (rs.next()) {
				Report report = reportMapper.extractFromResultSet(rs);
				reports.add(report);
			}
			LOG.debug("Retrived reports");
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_REPORTS, ex);
		} catch (Exception ex) {
			LOG.error("ex: ", ex);
		} finally {
			closeAll(pstmt, rs);
		}
		return reports;
	}

	@Override
	public List<Report> findAll() {
		List<Report> reports = new ArrayList<>();
		ResultSet rs = null;
		Statement st = null;
		LOG.debug("Start retireving all reports");
		try {
			st = connection.createStatement();
			rs = st.executeQuery("SELECT r.*, u.username as author_name, s.id as speaker_id, "
					+ "s.name_uk as speaker_name_uk, s.name_en as speaker_name_en "
					+ "FROM reports r "
					+ "LEFT JOIN users u on r.author = u.id "
					+ "LEFT JOIN speaker_reports sr on sr.report_id = r.id "
					+ "LEFT JOIN speakers s on s.id = sr.speaker_id ORDER BY r.id");
			ReportMapper reportMapper = new ReportMapper();
			while (rs.next()) {
				Report report = reportMapper.extractFromRsForModerator(rs);
				reports.add(report);
			}
			LOG.debug("Retrived all reports");
			return reports;
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_REPORTS, ex);
			return null;
		} finally {
			closeAll(st, rs);
		}
	}

	@Override
	public void update(Report report) throws DBException {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement("INSERT INTO speaker_reports (speaker_id, report_id) VALUES (?, ?) " +
					"ON DUPLICATE KEY UPDATE speaker_id = VALUES (speaker_id)");
			pstmt.setLong(1, report.getSpeaker().getId());
			pstmt.setLong(2, report.getId());
			pstmt.executeUpdate();
			pstmt2 = connection.prepareStatement("UPDATE reports SET accepted = ? WHERE id = ?");
			pstmt2.setBoolean(1, report.isAccepted());
			pstmt2.setLong(2, report.getId());
			pstmt2.executeUpdate();
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException ex) {
			rollback();
			throw new DBException(Messages.ERR_CANNOT_UPDATE_REPORT, ex);
		} finally {
			closeSt(pstmt2);
			closeSt(pstmt);
			closeConn();
		}
	}

	@Override
	public void setAccepted(long reportId) throws DBException {
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement("UPDATE reports SET accepted = 1 WHERE id = ?;");
			pstmt.setLong(1, reportId);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			rollback();
			throw new DBException(Messages.ERR_CANNOT_UPDATE_REPORT, ex);
		} finally {
			closeSt(pstmt);
			closeConn();
		}
	}

	@Override
	public void acceptSuggested(long reportId) throws DBException {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement("UPDATE reports SET accepted = 1 WHERE id = ?;");
			pstmt.setLong(1, reportId);
			pstmt.executeUpdate();
			pstmt2 = connection.prepareStatement("UPDATE reports SET suggested = 0 WHERE id = ?;");
			pstmt2.setLong(1, reportId);
			pstmt2.executeUpdate();
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException ex) {
			rollback();
			throw new DBException(Messages.ERR_CANNOT_UPDATE_REPORT, ex);
		} finally {
			closeSt(pstmt2);
			closeSt(pstmt);
			closeConn();
		}
	}

	@Override
	public void clearSpeaker(long reportId) throws DBException {
		PreparedStatement pstmt = null;
		try {
			// speaker_id = 1 means moderator so speaker is absent
			pstmt = connection.prepareStatement("UPDATE speaker_reports SET speaker_id = 1 WHERE report_id = ?;");
			pstmt.setLong(1, reportId);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			rollback();
			throw new DBException(Messages.ERR_CANNOT_UPDATE_REPORT, ex);
		} finally {
			closeSt(pstmt);
			closeConn();
		}
	}

	@Override
	public void delete(long reportId) throws DBException {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {
			connection.setAutoCommit(false);
			pstmt = connection.prepareStatement("DELETE FROM reports WHERE id = ?;");
			pstmt.setLong(1, reportId);
			pstmt.executeUpdate();
			pstmt2 = connection.prepareStatement("DELETE FROM speaker_reports WHERE report_id = ?;");
			pstmt2.setLong(1, reportId);
			pstmt2.executeUpdate();
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException ex) {
			rollback();
			throw new DBException(Messages.ERR_CANNOT_UPDATE_REPORT, ex);
		} finally {
			closeSt(pstmt2);
			closeSt(pstmt);
			closeConn();
		}

	}

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub

	}

}
