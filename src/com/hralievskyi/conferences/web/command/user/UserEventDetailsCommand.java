package com.hralievskyi.conferences.web.command.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.Path;
import com.hralievskyi.conferences.entity.Event;
import com.hralievskyi.conferences.entity.User;
import com.hralievskyi.conferences.exception.AppException;
import com.hralievskyi.conferences.exception.DBException;
import com.hralievskyi.conferences.service.EventService;
import com.hralievskyi.conferences.web.command.Command;

public class UserEventDetailsCommand extends Command {
	private static final long serialVersionUID = -880610023840624278L;
	private static final Logger LOG = Logger.getLogger(UserEventDetailsCommand.class);
	private EventService eventService;

	public UserEventDetailsCommand() {
		this.eventService = new EventService();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		try {
			User user = (User) request.getSession().getAttribute("user");
			Event event = eventService.getByIdAndUser(Long.parseLong(request.getParameter("id")), user.getId());
			request.setAttribute("event", event);
			boolean isSubscribed = event.getSubscribers().iterator().next().getId() != -1;
			request.setAttribute("subscribed", isSubscribed);
		} catch (Exception ex) {
			throw new DBException("user.event.error", ex);
		}
		LOG.debug("Command finished");
		return Path.PAGE_USER_EVENT;
	}

	@Override
	public String additionalParameters(HttpServletRequest request) {
		return "&id=" + request.getParameter("id");

	}

}
