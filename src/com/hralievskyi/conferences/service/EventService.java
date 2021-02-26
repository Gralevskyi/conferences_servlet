package com.hralievskyi.conferences.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.dao.DaoFactory;
import com.hralievskyi.conferences.dao.EventDao;
import com.hralievskyi.conferences.entity.Event;

public class EventService {
	private static final Logger LOG = Logger.getLogger(EventService.class);
	DaoFactory daoFactory = DaoFactory.getInstance();

	public List<Event> getAll() throws Exception {
		LOG.debug("Event service " + this + " starts");
		try (EventDao dao = daoFactory.createEventDao()) {
			return dao.findAll();
		}
	}

	public Event getByIdAndUser(long eventId, long userId) throws Exception {
		LOG.debug("Event service " + this + " starts");
		try (EventDao dao = daoFactory.createEventDao()) {
			return dao.findByIdAndUser(eventId, userId);
		}
	}

	public Event getById(long eventId) throws Exception {
		LOG.debug("Event service " + this + " starts");
		try (EventDao dao = daoFactory.createEventDao()) {
			return dao.findById(eventId);
		}
	}

	public void update(Event event) throws Exception {
		LOG.debug("Event service " + this + " starts");
		try (EventDao dao = daoFactory.createEventDao()) {
			dao.update(event);
		}
	}

	public void subscribeUser(long eventId, long userId) throws Exception {
		LOG.debug("starts");
		try (EventDao dao = daoFactory.createEventDao()) {
			dao.subscribe(eventId, userId);
		}
	}

	public void visit(long eventId, long userId) throws Exception {
		LOG.debug("starts");
		try (EventDao dao = daoFactory.createEventDao()) {
			dao.visit(eventId, userId);
		}
	}

	public List<Event> getAllUserEvents(Long id) throws Exception {
		LOG.debug("starts");
		try (EventDao dao = daoFactory.createEventDao()) {
			return dao.findAllUserEvents(id);
		}
	}

	public void createEvent(Event event) throws Exception {
		LOG.debug("starts");
		try (EventDao dao = daoFactory.createEventDao()) {
			dao.create(event);
		}
	}

	public void addNewReports(String[] reports, long eventId) throws Exception {
		LOG.debug("starts");
		try (EventDao dao = daoFactory.createEventDao()) {
			dao.addNewReports(reports, eventId);
		}
	}

	public List<Event> getAllForModerator(String orderBy, String whereDate) throws Exception {
		LOG.debug("Event service " + this + " starts");
		try (EventDao dao = daoFactory.createEventDao()) {
			return dao.findAllForModerator(createQueryForSorting(orderBy, whereDate));
		}
	}

	private String createQueryForSorting(String orderBy, String whereDate) {
		StringBuilder query = new StringBuilder("SELECT e.*, count(distinct es.user_id) AS subscribers, "
				+ "count(distinct ev.user_id) AS visitors, count(distinct er.report_id) as reports "
				+ "FROM events e LEFT JOIN event_subscribers es ON e.id = es.event_id "
				+ "LEFT JOIN event_visitors ev ON e.id = ev.event_id "
				+ "LEFT JOIN event_reports er ON e.id = er.event_id ");
		if (whereDate != null) {
			query.append(whereDate);
		}
		query.append(" GROUP BY e.id, e.name_en, e.name_uk, e.place_en, e.place_uk, e.date, e.time ORDER BY ");

		if (orderBy != null) {
			query = query.append(orderBy + " DESC;");
		} else {
			query = query.append(" e.date;");
		}
		return query.toString();
	}

}
