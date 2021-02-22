package com.hralievskyi.conferences.web.command.moderator;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.Path;
import com.hralievskyi.conferences.entity.Report;
import com.hralievskyi.conferences.entity.Speaker;
import com.hralievskyi.conferences.exception.AppException;
import com.hralievskyi.conferences.exception.DBException;
import com.hralievskyi.conferences.exception.Messages;
import com.hralievskyi.conferences.service.ReportService;
import com.hralievskyi.conferences.service.UserService;
import com.hralievskyi.conferences.web.command.Command;

public class ManageSpeakersCommand extends Command {
	private static final long serialVersionUID = 2846504759422586836L;
	private static final Logger LOG = Logger.getLogger(ManageSpeakersCommand.class);
	private ReportService reportService;
	private UserService userService;

	public ManageSpeakersCommand() {
		reportService = new ReportService();
		userService = new UserService();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		if ("GET".equals(request.getMethod())) {
			try {
				request.setAttribute("reports", reportService.findAll());
				request.setAttribute("speakers", userService.getAllSpeakers());
			} catch (Exception ex) {
				LOG.error(Messages.ERR_CANNOT_OBTAIN_REPORTS, ex);
				throw new DBException(Messages.ERR_CANNOT_OBTAIN_REPORTS, ex);
			}
			LOG.debug("Command finished");
			return Path.PAGE_MODERATOR_SPEAKERS;
		}
		return processPost(request, response);
	}

	private String processPost(HttpServletRequest request, HttpServletResponse response) throws DBException {
		try {
			LOG.trace("Post process: ");
			LOG.debug("report name: " + request.getParameter("reportId"));
			LOG.debug("speakerId: " + request.getParameter("speakerId"));
			LOG.debug("accepted: " + request.getParameter("accepted"));
			Report report = new Report();
			Speaker speaker = new Speaker(Long.parseLong(request.getParameter("speakerId")));
			report.setId(Long.parseLong(request.getParameter("reportId")));
			report.setAccepted("true".contentEquals((request.getParameter("accepted"))));
			report.setSpeaker(speaker);
			reportService.update(report);
			LOG.trace("Succesfully processed ");
			return Path.COMMAND_MODERATOR_MAIN;
		} catch (Exception ex) {
			throw new DBException(Messages.ERR_CAN_NOT_CREATE_EVENT, ex);
		}
	}
}
