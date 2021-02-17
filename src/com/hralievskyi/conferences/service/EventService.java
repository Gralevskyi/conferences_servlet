package com.hralievskyi.conferences.service;

import java.util.List;

import com.hralievskyi.conferences.dao.DaoFactory;
import com.hralievskyi.conferences.dao.EventDao;
import com.hralievskyi.conferences.entity.Event;

public class EventService {
	DaoFactory daoFactory = DaoFactory.getInstance();

	public List<Event> getAll() throws Exception {
		try (EventDao dao = daoFactory.createEventDao()) {
			return dao.findAll();
		}
	}
}
