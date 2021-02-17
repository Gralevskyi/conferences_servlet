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
import com.hralievskyi.conferences.entity.Event;
import com.hralievskyi.conferences.exception.Messages;

public class JDBCEventDao extends JDBCGenericDao<Event> implements EventDao {
	private static final Logger LOG = Logger.getLogger(JDBCEventDao.class);
	private Connection connection;

	public JDBCEventDao(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void create(Event entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Event findById(long id) {
		LOG.debug("Start retireving event by id " + id);
		Event event = new Event();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(
					"select e.*, r.topic_en, r.topic_uk, s.name_en as speaker_name_en, s.name_uk as speaker_name_uk from events e join event_reports er on e.id = er.event_id join reports r on r.id = er.report_id left join speaker_reports sr on sr.report_id = r.id left join speakers s on s.id = sr.speaker_id where e.id = ?");
			pstmt.setLong(1, id);
			pstmt.executeQuery();
			connection.commit();
			EventMapper eventMapper = new EventMapper();
			while (rs.next()) {
				event = eventMapper.extractFromResultSet(rs);

			}
			LOG.debug("Retrived all events");
			return event;
		} catch (SQLException ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_USERS, ex);
			return null;
		}
	}

	@Override
	public List<Event> findAll() {
		Map<Long, Event> events = new HashMap<>();
		LOG.debug("Start retireving all events");
		final String query = "select * from events";
		try (Statement st = connection.createStatement()) {
			ResultSet rs = st.executeQuery(query);
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

}
