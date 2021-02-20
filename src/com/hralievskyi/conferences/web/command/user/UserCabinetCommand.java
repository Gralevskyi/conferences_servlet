package com.hralievskyi.conferences.web.command.user;

import java.io.IOException;
import java.util.List;

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

public class UserCabinetCommand extends Command {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(UserCabinetCommand.class);
	private EventService eventService;

	public UserCabinetCommand() {
		this.eventService = new EventService();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		try {
			User user = (User) request.getSession().getAttribute("user");
			List<Event> events = eventService.getAllUserEvents(user.getId());
			request.setAttribute("events", events);
		} catch (Exception ex) {
			throw new DBException("user.event.error", ex);
		}
		LOG.debug("Command finished");
		return Path.PAGE_USER_CABINET;
	}

}
