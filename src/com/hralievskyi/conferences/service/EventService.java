package com.hralievskyi.conferences.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.dao.DaoFactory;
import com.hralievskyi.conferences.dao.EventDao;
import com.hralievskyi.conferences.entity.Event;
import com.hralievskyi.conferences.web.command.user.UserMainPageCommand;

public class EventService {
	private static final Logger LOG = Logger.getLogger(UserMainPageCommand.class);
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

	public void subscribeUser(long eventId, long userId) throws Exception {
		LOG.debug("starts");
		try (EventDao dao = daoFactory.createEventDao()) {
			dao.subscribe(eventId, userId);
		}
	}

	public List<Event> getAllUserEvents(Long id) throws Exception {
		LOG.debug("starts");
		try (EventDao dao = daoFactory.createEventDao()) {
			return dao.findAllUserEvents(id);
		}
	}

}
