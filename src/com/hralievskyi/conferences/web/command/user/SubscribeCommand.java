package com.hralievskyi.conferences.web.command.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.Path;
import com.hralievskyi.conferences.entity.User;
import com.hralievskyi.conferences.exception.AppException;
import com.hralievskyi.conferences.exception.DBException;
import com.hralievskyi.conferences.service.EventService;
import com.hralievskyi.conferences.web.command.Command;

public class SubscribeCommand extends Command {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(UserEventDetailsCommand.class);
	private EventService eventService;

	public SubscribeCommand() {
		eventService = new EventService();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		try {
			User user = (User) request.getSession().getAttribute("user");
			long eventId = Long.parseLong(request.getParameter("id"));
			long userId = user.getId();
			eventService.subscribeUser(eventId, userId);
		} catch (Exception ex) {
			throw new DBException("user.subscribe.error", ex);
		}
		LOG.debug("Command finished");
		return Path.PAGE_USER_MAIN;
	}

}
