package com.hralievskyi.conferences.web.command.moderator;

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

public class ModeratorCreateReportCommand extends Command {
	private static final long serialVersionUID = 4756737766234922999L;

	private static final Logger LOG = Logger.getLogger(ModeratorCreateReportCommand.class);
	private ReportService reportService;
	private UserService userService;
	private String[] parameters = { "topicUk", "topicEn" };

	public ModeratorCreateReportCommand() {
		reportService = new ReportService();
		userService = new UserService();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		if ("GET".equals(request.getMethod())) {
			try {
				request.setAttribute("speakers", userService.getAllSpeakers());
			} catch (Exception ex) {
				LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_SPEAKERS, ex);
				throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_SPEAKERS, ex);
			}
			LOG.debug("Command finished");
			return Path.PAGE_MOD_CREATE_REPORT;
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
			try {
				request.setAttribute("speakers", userService.getAllSpeakers());
			} catch (Exception ex) {
				LOG.error(Messages.ERR_CANNOT_OBTAIN_ALL_SPEAKERS, ex);
				throw new DBException(Messages.ERR_CANNOT_OBTAIN_ALL_SPEAKERS, ex);
			}
			LOG.debug("Command finished");
			return Path.PAGE_MOD_CREATE_REPORT;
		} else {
			try {
				LOG.trace("Try to create report: " + request.getParameter("topicEn"));
				// id of moderator is 1 and author of report is moderator so User is created
				// with id 1
				Report report = new Report(request.getParameter("topicEn"),
						request.getParameter("topicUk"),
						new User(1),
						new Speaker(Long.parseLong(request.getParameter("speaker_id"))));
				reportService.createReport(report);
				LOG.trace("Succesfully crete event: " + request.getParameter("nameEn"));
				return Path.COMMAND_MODERATOR_MAIN;
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
				errors.put(parameters[i], Messages.ERR_CAN_NOT_BE_EMPTY);
			}
		}
		LOG.trace("Finish validation report form with topic name --> " + request.getParameter("topicEn"));
		return errors;
	}
}
