package com.hralievskyi.conferences.web.command.speaker;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.Path;
import com.hralievskyi.conferences.entity.User;
import com.hralievskyi.conferences.exception.AppException;
import com.hralievskyi.conferences.exception.DBException;
import com.hralievskyi.conferences.exception.Messages;
import com.hralievskyi.conferences.service.ReportService;
import com.hralievskyi.conferences.web.command.Command;

public class SpeakerCabinetCommand extends Command {
	private static final long serialVersionUID = -1002371073540468768L;
	private static final Logger LOG = Logger.getLogger(SpeakerCabinetCommand.class);
	private ReportService reportService;

	public SpeakerCabinetCommand() {
		reportService = new ReportService();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		try {
			User user = (User) request.getSession().getAttribute("user");
			request.setAttribute("reports", reportService.findBySpeakerId(user.getId()));
		} catch (Exception ex) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_REPORTS, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_REPORTS, ex);
		}
		LOG.debug("Command finished");
		return Path.PAGE_SPEAKER_CABINET;
	}

}
