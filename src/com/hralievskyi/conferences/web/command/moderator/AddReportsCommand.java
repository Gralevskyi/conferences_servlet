package com.hralievskyi.conferences.web.command.moderator;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.Path;
import com.hralievskyi.conferences.entity.Report;
import com.hralievskyi.conferences.exception.AppException;
import com.hralievskyi.conferences.exception.DBException;
import com.hralievskyi.conferences.exception.Messages;
import com.hralievskyi.conferences.service.EventService;
import com.hralievskyi.conferences.service.ReportService;
import com.hralievskyi.conferences.web.command.Command;

public class AddReportsCommand extends Command {
	private static final long serialVersionUID = -6955067871201822836L;

	private static final Logger LOG = Logger.getLogger(AddReportsCommand.class);
	private EventService eventService;
	private ReportService reportService;

	public AddReportsCommand() {
		eventService = new EventService();
		reportService = new ReportService();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		if ("GET".equals(request.getMethod())) {
			try {
				List<Report> reports = reportService.findNewReportsFor(Long.parseLong(request.getParameter("id")));
				String[] selected_reports = new String[reports.size()];
				request.setAttribute("name", request.getParameter("name"));
				request.setAttribute("id", request.getParameter("id"));
				request.setAttribute("selected_reports", selected_reports);
				request.setAttribute("reports", reports);
			} catch (Exception ex) {
				LOG.error(Messages.ERR_CANNOT_OBTAIN_REPORTS, ex);
				throw new DBException(Messages.ERR_CANNOT_OBTAIN_REPORTS, ex);
			}
			LOG.debug("Command finished");
			return Path.PAGE_ADD_REPORTS;
		}
		return processPost(request, response);
	}

	private String processPost(HttpServletRequest request, HttpServletResponse response) throws DBException {
		try {
			LOG.trace("Try to add reports to eventId: " + request.getParameter("id"));
			String[] reports = request.getParameterValues("selected_reports");
			eventService.addNewReports(reports, Long.parseLong(request.getParameter("id")));
			return Path.COMMAND_MODERATOR_MAIN;
		} catch (Exception ex) {
			throw new DBException(Messages.ERR_CAN_NOT_UPDATE_EVENT, ex);
		}
	}
}
