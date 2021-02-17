package com.hralievskyi.conferences.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.Path;
import com.hralievskyi.conferences.exception.AppException;

public class ModeratorCabinetCommand extends Command {

	private static final long serialVersionUID = 7732286214029478505L;

	private static final Logger LOG = Logger.getLogger(ModeratorCabinetCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		// put menu items list to the request
		request.setAttribute("role", "MODERATOR");
		LOG.trace("Set the request attribute: role --> " + "MODERSTOR");

		LOG.debug("Command finished");
		return Path.PAGE_MODERATOR_CABINET;
	}

}
