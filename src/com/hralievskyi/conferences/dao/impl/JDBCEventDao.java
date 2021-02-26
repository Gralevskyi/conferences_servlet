package com.hralievskyi.conferences.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.dao.EventDao;
import com.hralievskyi.conferences.dao.mapper.EventMapper;
import com.hralievskyi.conferences.dao.mapper.ReportMapper;
import com.hralievskyi.conferences.entity.Event;
import com.hralievskyi.conferences.entity.Report;
import com.hralievskyi.conferences.entity.User;
import com.hralievskyi.conferences.exception.DBException;
import com.hralievskyi.conferences.exception.Messages;

public class JDBCEventDao extends JDBCGenericDao<Event> implements EventDao {
	private static final Logger LOG = Logger.getLogger(JDBCEventDao.class);

	public JDBCEventDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void create(Event event) throws DBException {
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement("INSERT INTO events VALUES(DEFAULT, ?, ?, ?, ?, ?, ?);");
			pstmt.setString(1, event.getNameEn());
			pstmt.setString(2, event.getNameUk());
			pstmt.setString(3, event.getPlaceEn());
			pstmt.setString(4, event.getPlaceUk());
			pstmt.setDate(5, java.sql.Date.valueOf(event.getDate()));
			pstmt.setTime(6, java.sql.Time.valueOf(event.getTime()));
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			rollback();
			throw new DBException(Messages.ERR_CAN_NOT_CREATE_EVENT, ex);
		} finally {
			closeSt(pstmt);
			closeConn();
		}
	}

	@Override
	public Event findByIdAndUser(long eventId, long userId) {
		LOG.debug("Start retireving event by id " + eventId);
		Event event = new Event();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement("SELECT e.*, ifnull(es.user_id, -1) as user_id, r.topic_en, r.topic_uk, "
					+ "s.name_en as speaker_name_en, s.name_uk as speaker_name_uk "
					+ "FROM events e LEFT JOIN event_reports er ON e.id = er.event_id "
					+ "LEFT JOIN reports r ON r.id = er.report_id "
					+ "LEFT JOIN speaker_reports sr ON sr.report_id = r.id "
					+ "LEFT JOIN speakers s ON s.id = sr.speaker_id "
					+ "LEFT JOIN event_subscribers es ON e.id = es.event_id AND es.user_id = ? WHERE e.id = ?");
			pstmt.setLong(1, userId);
			pstmt.setLong(2, eventId);
			rs = pstmt.executeQuery();
			EventMapper eventMapper = new EventMapper();
			ReportMapper reportMapper = new ReportMapper();
			while (rs.next()) {
				if (event.getNameEn() == null) {
					event = eventMapper.extractFromResultSet(rs);
					User user = new User();
					user.setId(rs.getLong("user_id"));
					event.getSubscribers().add(user);
				}
				Report report = reportMapper.extractFromRsForEvent(rs);
				event.getReports().add(report);
			}
			LOG.debug("Retrived by id " + eventId);
			return event;
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_USERS, ex);
			return null;
		} finally {
			closeAll(pstmt, rs);
		}
	}

	@Override
	public List<Event> findAll() {
		Map<Long, Event> events = new HashMap<>();
		ResultSet rs = null;
		Statement st = null;
		LOG.debug("Start retireving all events");
		try {
			LOG.debug("connection isAutocommit: " + connection.getAutoCommit());
			st = connection.createStatement();
			rs = st.executeQuery("SELECT * FROM events");
			EventMapper eventMapper = new EventMapper();
			while (rs.next()) {
				Event event = eventMapper.extractFromResultSet(rs);
				event = eventMapper.makeUnique(events, event);
			}
			LOG.debug("Retrived all events");
			return new ArrayList<>(events.values());
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_USERS, ex);
			return null;
		} finally {
			closeAll(st, rs);

		}
	}

	@Override
	public List<Event> findAllForModerator(String query) {
		List<Event> events = new LinkedList<>();
		ResultSet rs = null;
		Statement st = null;
		LOG.debug("Start retrieving all events");
		try {
			st = connection.createStatement();
			rs = st.executeQuery(query);
			EventMapper eventMapper = new EventMapper();
			while (rs.next()) {
				Event event = eventMapper.extractFromResultSet(rs);
				event.setSubscribersNumber(rs.getInt("subscribers"));
				event.setVisitors(rs.getInt("visitors"));
				event.setReportsNumber(rs.getInt("reports"));
				events.add(event);
			}
			LOG.debug("Retrieved all events");
			return events;
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_USERS, ex);
			return null;
		} finally {
			closeAll(st, rs);

		}
	}

	@Override
	public void update(Event event) throws DBException {
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement("INSERT INTO conferencedb.events(id, name_en, name_uk, place_en, place_uk, date, time) VALUES (?, ?, ?, ?, ?, ?, ?) "
					+ " ON DUPLICATE KEY UPDATE id=VALUES(id), name_en=VALUES(name_en), name_uk=VALUES(name_uk), place_en=VALUES(place_en), place_uk=VALUES(place_uk), date=VALUES(date), time=VALUES(time);");
			pstmt.setLong(1, event.getId());
			pstmt.setString(2, event.getNameEn());
			pstmt.setString(3, event.getNameUk());
			pstmt.setString(4, event.getPlaceEn());
			pstmt.setString(5, event.getPlaceUk());
			pstmt.setDate(6, java.sql.Date.valueOf(event.getDate()));
			pstmt.setTime(7, java.sql.Time.valueOf(event.getTime()));
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			rollback();
			throw new DBException(Messages.ERR_CAN_NOT_UPDATE_EVENT, ex);
		} finally {
			closeSt(pstmt);
			closeConn();
		}
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void subscribe(long eventId, long userId) throws DBException {
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement("INSERT INTO event_subscribers VALUES(?, ?);");
			pstmt.setLong(1, eventId);
			pstmt.setLong(2, userId);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			rollback();
			throw new DBException(Messages.ERR_CANNOT_SUBSCRIBE, ex);
		} finally {
			closeSt(pstmt);
			closeConn();
		}
	}

	@Override
	public void visit(long eventId, long userId) throws DBException {
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement("INSERT INTO event_visitors VALUES(?, ?);");
			pstmt.setLong(1, eventId);
			pstmt.setLong(2, userId);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			rollback();
			throw new DBException(Messages.ERR_CANNOT_VISIT, ex);
		} finally {
			closeSt(pstmt);
			closeConn();
		}
	}

	@Override
	public Event findById(long eventId) {
		LOG.debug("Start retireving event by id " + eventId);
		Event event = new Event();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement("SELECT e.*, r.topic_en, r.topic_uk, "
					+ "s.name_en as speaker_name_en, s.name_uk as speaker_name_uk "
					+ "FROM events e LEFT JOIN event_reports er ON e.id = er.event_id "
					+ "LEFT JOIN reports r ON r.id = er.report_id "
					+ "LEFT JOIN speaker_reports sr ON sr.report_id = r.id "
					+ "LEFT JOIN speakers s ON s.id = sr.speaker_id WHERE e.id = ?");
			pstmt.setLong(1, eventId);
			rs = pstmt.executeQuery();
			EventMapper eventMapper = new EventMapper();
			ReportMapper reportMapper = new ReportMapper();
			long reportId = 0;
			while (rs.next()) {
				if (event.getNameEn() == null) {
					event = eventMapper.extractFromResultSet(rs);
				}
				Report report = reportMapper.extractFromRsForEvent(rs);
				report.setId(reportId++);
				event.addReport(report);
			}
			LOG.debug("Retrived by id " + eventId);
			return event;
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_EVENTS, ex);
			return null;
		} finally {
			closeAll(pstmt, rs);
		}
	}

	@Override
	public List<Event> findAllUserEvents(Long userId) throws DBException {
		LOG.debug("Start retireving events of userId " + userId);
		List<Event> events = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement("SELECT e.*, r.topic_en, r.topic_uk, ifnull(ev.user_id, -1) as visited,"
					+ "s.name_en as speaker_name_en, s.name_uk as speaker_name_uk "
					+ "FROM events e JOIN event_reports er ON e.id = er.event_id "
					+ "LEFT JOIN reports r ON r.id = er.report_id "
					+ "LEFT JOIN speaker_reports sr ON sr.report_id = r.id "
					+ "LEFT JOIN speakers s ON s.id = sr.speaker_id "
					+ "LEFT JOIN event_visitors ev ON e.id = ev.event_id AND ev.user_id = ? "
					+ "LEFT JOIN event_subscribers es ON e.id = es.event_id WHERE es.user_id = ?");
			pstmt.setLong(1, userId);
			pstmt.setLong(2, userId);
			rs = pstmt.executeQuery();
			EventMapper eventMapper = new EventMapper();
			ReportMapper reportMapper = new ReportMapper();
			while (rs.next()) {
				long currId = rs.getLong("id");
				Event event = events.stream().filter(ev -> ev.getId() == currId).findAny().orElse(eventMapper.extractFromResultSet(rs));
				if (!events.contains(event)) {
					events.add(event);
					event.setVisited((event.getDate().isBefore(LocalDate.now()) && rs.getLong("visited") != -1) || event.getDate().isAfter(LocalDate.now()));
				}
				Report report = reportMapper.extractFromRsForEvent(rs);
				event.getReports().add(report);
			}
			LOG.debug("Retrived events of userId" + userId);
			return events;
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_EVENTS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_EVENTS, ex);
		} finally {
			closeAll(pstmt, rs);
		}
	}

	@Override
	public void addNewReports(String[] reports, long eventId) throws DBException {
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement("INSERT INTO event_reports VALUES(?, ?);");
			connection.setAutoCommit(false);
			for (String reportId : reports) {
				pstmt.setLong(1, eventId);
				pstmt.setLong(2, Long.parseLong(reportId));
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException ex) {
			rollback();
			throw new DBException(Messages.ERR_CAN_NOT_UPDATE_EVENT, ex);
		} finally {
			closeSt(pstmt);
			closeConn();
		}
	}

}
