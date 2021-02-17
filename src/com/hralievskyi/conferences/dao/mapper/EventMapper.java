package com.hralievskyi.conferences.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

import com.hralievskyi.conferences.entity.Event;

public class EventMapper implements ObjectMapper<Event> {

	@Override
	public Event extractFromResultSet(ResultSet rs) throws SQLException {
		Event event = new Event();
		event.setId(rs.getLong("id"));
		event.setNameEn(rs.getString("name_en"));
		event.setNameUk(rs.getString("name_uk"));
		event.setPlaceUk(rs.getString("place_uk"));
		event.setPlaceEn(rs.getString("place_en"));
		event.setDate(LocalDate.parse(rs.getString("date")));
		event.setTime(LocalTime.parse(rs.getString("time")));
		return event;
	}

	@Override
	public Event makeUnique(Map<Long, Event> cache, Event event) {
		cache.putIfAbsent(event.getId(), event);
		return cache.get(event.getId());
	}

}
