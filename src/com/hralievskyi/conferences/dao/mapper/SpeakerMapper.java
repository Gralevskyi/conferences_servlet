package com.hralievskyi.conferences.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.hralievskyi.conferences.entity.Speaker;

public class SpeakerMapper implements ObjectMapper<Speaker> {

	@Override
	public Speaker extractFromResultSet(ResultSet rs) throws SQLException {
		Speaker speaker = new Speaker();
		speaker.setId(rs.getLong("id"));
		speaker.setNameEn(rs.getString("name_en"));
		speaker.setNameUk(rs.getString("name_uk"));
		return speaker;
	}

	@Override
	public Speaker makeUnique(Map<Long, Speaker> cache, Speaker speaker) {
		cache.putIfAbsent(speaker.getId(), speaker);
		return cache.get(speaker.getId());
	}

}
