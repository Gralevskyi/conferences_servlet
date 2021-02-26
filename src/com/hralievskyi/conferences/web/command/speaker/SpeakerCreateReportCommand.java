package com.hralievskyi.conferences.web.command.speaker;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.Path;
import com.hralievskyi.conferences.entity.Report;
import com.hralievskyi.conferences.entity.Speaker;
import com.hralievskyi.conferences.entity.User;
import com.hralievskyi.conferences.exception.AppException;
import com.hralievskyi.conferences.exception.DBException;
import com.hralievskyi.conferences.exception.Messages;
import com.hralievskyi.conferences.service.ReportService;
import com.hralievskyi.conferences.service.UserService;
import com.hralievskyi.conferences.web.command.Command;

public class SpeakerCreateReportCommand extends Command {
	private static final long serialVersionUID = -851898286768129178L;

	private static final Logger LOG = Logger.getLogger(SpeakerCreateReportCommand.class);
	private ReportService reportService;
	private UserService userService;
	private String[] parameters = { "topicUk", "topicEn" };

	public SpeakerCreateReportCommand() {
		reportService = new ReportService();
		userService = new UserService();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		if ("GET".equals(request.getMethod())) {
			LOG.debug("Command finished");
			return Path.PAGE_SP_CREATE_REPORT;
		}
		return processPost(request, response);
	}

	private String processPost(HttpServletRequest request, HttpServletResponse response) throws DBException {
		Map<String, String> errors = validateRequest(request);
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			for (int i = 0; i < parameters.length; i++) {
				request.setAttribute(parameters[i], request.getParameter(parameters[i]));
			}
			LOG.debug("Command finished");
			return Path.PAGE_SP_CREATE_REPORT;
		} else {
			try {
				LOG.trace("Try to create report: " + request.getParameter("topicEn"));
				User speaker = (User) request.getSession().getAttribute("user");
				Report report = new Report(request.getParameter("topicEn"),
						request.getParameter("topicUk"),
						new User(speaker.getId()),
						new Speaker(speaker.getId()));
				report.setSuggested(true);
				reportService.createReport(report);
				LOG.trace("Succesfully crete report: " + request.getParameter("nameEn"));
				return Path.COMMAND_USER_MAIN;
			} catch (Exception ex) {
				LOG.error(Messages.ERR_CAN_NOT_CREATE_REPORT, ex);
				throw new DBException(Messages.ERR_CAN_NOT_CREATE_REPORT, ex);
			}
		}
	}

	private Map<String, String> validateRequest(HttpServletRequest request) {
		LOG.trace("Start validation report form with topic name --> " + request.getParameter("topicEn"));
		Map<String, String> errors = new HashMap<>();
		for (int i = 0; i < parameters.length; i++) {
			if (request.getParameter(parameters[i]).isEmpty()) {
				errors.put(parameters[i], "event.create.error.empty");
			}
		}
		LOG.trace("Finish validation report form with topic name --> " + request.getParameter("topicEn"));
		return errors;
	}
}
