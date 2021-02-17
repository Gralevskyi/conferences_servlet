package com.hralievskyi.conferences.dao.constants;

import com.hralievskyi.conferences.entity.User;

public enum Role {
	MODERATOR, SPEAKER, USER;

	public static Role getRole(User user) {
		int roleId = user.getRoleId();
		return Role.values()[roleId];
	}

	public String getName() {
		return name().toLowerCase();
	}
}