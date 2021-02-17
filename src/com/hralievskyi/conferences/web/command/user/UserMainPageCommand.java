package com.hralievskyi.conferences.web.command.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.Path;
import com.hralievskyi.conferences.exception.AppException;
import com.hralievskyi.conferences.exception.DBException;
import com.hralievskyi.conferences.service.EventService;
import com.hralievskyi.conferences.web.command.Command;

public class UserMainPageCommand extends Command {
	private static final long serialVersionUID = -880610023840624278L;
	private static final Logger LOG = Logger.getLogger(UserMainPageCommand.class);
	private EventService eventService;

	public UserMainPageCommand() {
		this.eventService = new EventService();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		try {
			request.setAttribute("events", eventService.getAll());
		} catch (Exception ex) {
			throw new DBException("user.main.error", ex);
		}
		LOG.debug("Command finished");
		return Path.PAGE_USER_CABINET;
	}

}
