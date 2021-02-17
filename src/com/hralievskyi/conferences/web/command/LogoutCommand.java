package com.hralievskyi.conferences.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.Path;
import com.hralievskyi.conferences.entity.User;
import com.hralievskyi.conferences.exception.AppException;

public class LogoutCommand extends Command {

	private static final long serialVersionUID = -4892424947357876488L;
	private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		User user = (User) request.getSession().getAttribute("user");
		LOG.info("Logging out user --> " + user.getUsername());
		request.getSession().invalidate();
		LOG.debug("Command finished");
		return Path.PAGE_LOGIN;
	}

}
