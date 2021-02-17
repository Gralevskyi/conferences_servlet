package com.hralievskyi.conferences.web.command;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.Path;
import com.hralievskyi.conferences.entity.User;
import com.hralievskyi.conferences.exception.AppException;
import com.hralievskyi.conferences.exception.DBException;
import com.hralievskyi.conferences.exception.Messages;
import com.hralievskyi.conferences.service.UserService;

public class RegisterCommand extends Command {
	private static final long serialVersionUID = 8589164311331573361L;

	private UserService userService;

	public RegisterCommand() {
		this.userService = new UserService();
	}

	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		if ("GET".equals(request.getMethod())) {
			LOG.debug("Command finished");
			return Path.PAGE_REGISTER;
		}
		return processPost(request, response);
	}

	private String processPost(HttpServletRequest request, HttpServletResponse response) throws DBException {
		Map<String, String> errors = validateRequest(request);
		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.setAttribute("email", request.getParameter("email"));
			LOG.debug("Command finished");
			return Path.PAGE_REGISTER;
		} else {
			try {
				// Role "USER" has id 2 in roles table
				userService.creteUser(new User(request.getParameter("email"), request.getParameter("password"), 2));
				LOG.trace("Succesfully crete user: " + request.getParameter("email"));
				return Path.PAGE_LOGIN;
			} catch (Exception ex) {
				throw new DBException(Messages.ERR_CAN_NOT_CREATE_USER, ex);
			}
		}
	}

	private Map<String, String> validateRequest(HttpServletRequest request) {
		LOG.trace("Start validation registr form with email --> " + request.getParameter("email"));
		Map<String, String> errors = new HashMap<>();
		validateUsername(request.getParameter("email"), errors);
		validatePassword(request.getParameter("password"), request.getParameter("passwordConfirm"), errors);
		if (!errors.containsKey("username")) {
			validateAlreadyExists(request.getParameter("email"), errors);
		}
		LOG.trace("Finish validation registr form with email --> " + request.getParameter("email"));
		return errors;
	}

	private void validateUsername(String username, Map<String, String> errors) {
		if (username == null) {
			LOG.warn("User entered empty email");
			errors.put("username", Messages.ERR_EMAIL_CAN_NOT_BE_EMPTY);
		} else if (!username.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")) {
			LOG.warn("User entered not correct email");
			errors.put("username", Messages.ERR_EMAIL_MUST_BE_CORRECT);
		}
	}

	private void validatePassword(String password, String passwordConfirm, Map<String, String> errors) {
		if (password.length() < 5 || password.length() > 25) {
			LOG.warn("User entered invalid password");
			errors.put("password", Messages.ERR_PASSWORD_CAN_NOT_BE_EMPTY);
		} else if (!password.equals(passwordConfirm)) {
			LOG.warn("User entered no correct password confirmation");
			errors.put("password", Messages.ERR_PASSWORD_DO_NOT_MATCH);
		}
	}

	private void validateAlreadyExists(String username, Map<String, String> errors) {
		if (userService.findUserByUsername(username) != null) {
			LOG.warn("User with email: " + username + " already exists");
			errors.put("username", Messages.WARN_USER_ALREADY_EXISTS);
		}
	}

}
