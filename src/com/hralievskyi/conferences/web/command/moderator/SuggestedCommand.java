package com.hralievskyi.conferences.web.command.moderator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.Path;
import com.hralievskyi.conferences.exception.AppException;
import com.hralievskyi.conferences.exception.DBException;
import com.hralievskyi.conferences.exception.Messages;
import com.hralievskyi.conferences.service.ReportService;
import com.hralievskyi.conferences.web.command.Command;

public class SuggestedCommand extends Command {
	private static final long serialVersionUID = 4877084602234225433L;
	private static final Logger LOG = Logger.getLogger(SuggestedCommand.class);
	private ReportService reportService;

	public SuggestedCommand() {
		reportService = new ReportService();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		try {
			LOG.trace("action: " + request.getParameter("act"));
			if ("accept".equals(request.getParameter("act"))) {
				reportService.acceptSuggested(Long.parseLong(request.getParameter("report_id")));
			} else {
				reportService.delete(Long.parseLong(request.getParameter("report_id")));
			}
			request.setAttribute("reload", "reload");
			return Path.COMMAND_MODERATOR_SPEAKERS;
		} catch (Exception ex) {
			throw new DBException(Messages.ERR_CANNOT_UPDATE_REPORT, ex);
		}
	}

}
