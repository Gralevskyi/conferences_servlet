package com.hralievskyi.conferences.dao;

import java.util.List;

import com.hralievskyi.conferences.entity.Event;
import com.hralievskyi.conferences.exception.DBException;

public interface EventDao extends GenericDao<Event> {

	void subscribe(long eventId, long userId) throws DBException;

	Event findByIdAndUser(long eventId, long userId);

	List<Event> findAllUserEvents(Long id) throws DBException;

	void visit(long eventId, long userId) throws DBException;

	List<Event> findAllForModerator(String query);

	void addNewReports(String[] reports, long eventId) throws DBException;

}
