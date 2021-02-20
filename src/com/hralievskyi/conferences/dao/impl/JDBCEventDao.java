package com.hralievskyi.conferences.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
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
	public void create(Event entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Event findByIdAndUser(long eventId, long userId) {
		LOG.debug("Start retireving event by id " + eventId);
		Event event = new Event();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement("select e.*, ifnull(es.user_id, -1) as user_id, r.topic_en, r.topic_uk, "
					+ "s.name_en as speaker_name_en, s.name_uk as speaker_name_uk "
					+ "from events e join event_reports er on e.id = er.event_id "
					+ "join reports r on r.id = er.report_id left join speaker_reports sr on sr.report_id = r.id "
					+ "left join speakers s on s.id = sr.speaker_id "
					+ "left join event_subscribers es on e.id = es.event_id and es.user_id = ? where e.id = ?");
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
	public void update(Event entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void subscribe(long eventId, long userId) throws DBException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement("INSERT INTO event_subscribers VALUES(?, ?);");
			pstmt.setLong(1, eventId);
			pstmt.setLong(2, userId);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			rollback();
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			closeAll(pstmt, rs);
		}
	}

	@Override
	public Event findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> findAllUserEvents(Long userId) throws DBException {
		LOG.debug("Start retireving events of userId " + userId);
		List<Event> events = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement("select e.*, r.topic_en, r.topic_uk, "
					+ "s.name_en as speaker_name_en, s.name_uk as speaker_name_uk "
					+ "from events e join event_reports er on e.id = er.event_id "
					+ "left join reports r on r.id = er.report_id left join speaker_reports sr on sr.report_id = r.id "
					+ "left join speakers s on s.id = sr.speaker_id "
					+ "left join event_subscribers es on e.id = es.event_id where es.user_id = ?");
			pstmt.setLong(1, userId);
			rs = pstmt.executeQuery();
			EventMapper eventMapper = new EventMapper();
			ReportMapper reportMapper = new ReportMapper();
			while (rs.next()) {
				long currId = rs.getLong("id");
				Event event = events.stream().filter(ev -> ev.getId() == currId).findAny().orElse(eventMapper.extractFromResultSet(rs));
				if (!events.contains(event)) {
					events.add(event);
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

}
