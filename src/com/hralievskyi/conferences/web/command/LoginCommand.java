package com.hralievskyi.conferences.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.Path;
import com.hralievskyi.conferences.dao.constants.Role;
import com.hralievskyi.conferences.entity.User;
import com.hralievskyi.conferences.exception.AppException;
import com.hralievskyi.conferences.exception.DBException;
import com.hralievskyi.conferences.exception.Messages;
import com.hralievskyi.conferences.service.UserService;
import com.hralievskyi.conferences.util.Localizator;

public class LoginCommand extends Command {

	private static final long serialVersionUID = -3071536593627692473L;
	private static final Logger LOG = Logger.getLogger(LoginCommand.class);
	private UserService userService;

	public LoginCommand() {
		this.userService = new UserService();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("Command starts");

		HttpSession session = request.getSession();

		// obtain login and password from a request
		String username = request.getParameter("login");
		LOG.trace("Request parameter: login --> " + username);

		String password = request.getParameter("password");
		if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
			request.setAttribute("errors", true);
			return Path.PAGE_LOGIN;
		}

		User user = null;
		try {
			user = userService.findUserByUsername(username);
			LOG.trace("Found in DB: user --> " + user);
		} catch (Exception ex) {
			throw new DBException(Localizator.convert(request, "login.error"), ex);
		}

		if (user == null || !password.equals(user.getPassword())) {
			request.setAttribute("errors", Messages.ERR_INVALID_LOGIN);
			return Path.PAGE_LOGIN;
		}

		Role userRole = Role.getRole(user);
		LOG.trace("userRole --> " + userRole);

		String forward = Path.PAGE_ERROR_PAGE;

		if (userRole == Role.MODERATOR) {
			forward = Path.PAGE_MODERATOR_CABINET;
		}

		if (userRole == Role.SPEAKER) {
			forward = Path.PAGE_SPEAKER_CABINET;
		}

		if (userRole == Role.USER) {
			forward = Path.COMMAND_USER_MAIN;
		}

		session.setAttribute("user", user);
		LOG.trace("Set the session attribute: user --> " + user);

		session.setAttribute("userRole", userRole);
		LOG.trace("Set the session attribute: userRole --> " + userRole);

		LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());

		LOG.debug("Command finished");
		return forward;
	}

}