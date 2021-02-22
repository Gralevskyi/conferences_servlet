package com.hralievskyi.conferences.web.command.speaker;

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

public class SpeakerActionsCommand extends Command {
	private static final long serialVersionUID = 3602409467896148098L;
	private static final Logger LOG = Logger.getLogger(SpeakerActionsCommand.class);
	private ReportService reportService;

	public SpeakerActionsCommand() {
		reportService = new ReportService();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
		try {
			LOG.trace("command starts");
			long reportId = Long.parseLong(request.getParameter("reportId"));
			if ("accept".equals(request.getParameter("act"))) {
				reportService.setAccepted(reportId);
			} else {
				reportService.clearSpeaker(reportId);
			}
			LOG.trace("command finish");
			return Path.COMMAND_SPEAKER_CABINET;
		} catch (Exception ex) {
			throw new DBException(Messages.ERR_CANNOT_UPDATE_REPORT, ex);
		}
	}

}
