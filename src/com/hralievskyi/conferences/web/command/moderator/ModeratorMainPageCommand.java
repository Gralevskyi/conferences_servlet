package com.hralievskyi.conferences.web.command.moderator;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.Path;
import com.hralievskyi.conferences.exception.AppException;
import com.hralievskyi.conferences.exception.DBException;
import com.hralievskyi.conferences.service.EventService;
import com.hralievskyi.conferences.web.command.Command;

public class ModeratorMainPageCommand extends Command {
	private static final long serialVersionUID = 8984719783235342045L;
	private static final Logger LOG = Logger.getLogger(ModeratorMainPageCommand.class);
	private EventService eventService;

	public ModeratorMainPageCommand() {
		this.eventService = new EventService();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		try {
			String whereClause = null;
			if ("where".equals(request.getParameter("act"))) {
				whereClause = " WHERE e.date " + request.getParameter("equality") + " '" + request.getParameter("date") + "' ";
			}
			request.setAttribute("events", eventService.getAllForModerator(request.getParameter("order_by"), whereClause));
		} catch (Exception ex) {
			throw new DBException("user.main.error", ex);
		}
		if (request.getParameter("date") == null) {
			request.setAttribute("date", LocalDate.now());
		} else {
			request.setAttribute("date", request.getParameter("date"));
		}
		request.setAttribute("orderBy", request.getParameter("order_by"));
		LOG.debug("Command finished");
		return Path.PAGE_MODERATOR_MAIN;
	}

}
