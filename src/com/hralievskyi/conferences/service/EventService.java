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

	public List<Event> getAllForModerator() throws Exception {
		LOG.debug("Event service " + this + " starts");
		try (EventDao dao = daoFactory.createEventDao()) {
			return dao.findAllForModerator();
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

}
