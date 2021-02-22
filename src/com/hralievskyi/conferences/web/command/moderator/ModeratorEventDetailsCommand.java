package com.hralievskyi.conferences.web.command.moderator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.Path;
import com.hralievskyi.conferences.entity.Event;
import com.hralievskyi.conferences.exception.AppException;
import com.hralievskyi.conferences.exception.DBException;
import com.hralievskyi.conferences.service.EventService;
import com.hralievskyi.conferences.web.command.Command;

public class ModeratorEventDetailsCommand extends Command {
	private static final long serialVersionUID = -7834106713948733344L;

	private static final Logger LOG = Logger.getLogger(ModeratorEventDetailsCommand.class);
	private EventService eventService;

	public ModeratorEventDetailsCommand() {
		this.eventService = new EventService();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		try {
			Event event = eventService.getById(Long.parseLong(request.getParameter("id")));
			request.setAttribute("event", event);
		} catch (Exception ex) {
			throw new DBException("user.event.error", ex);
		}
		LOG.debug("Command finished");
		return Path.PAGE_MODERATOR_EVENT;
	}

	@Override
	public String additionalParameters(HttpServletRequest request) {
		return "&id=" + request.getParameter("id");

	}

}
