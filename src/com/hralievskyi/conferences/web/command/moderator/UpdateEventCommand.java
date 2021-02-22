package com.hralievskyi.conferences.web.command.moderator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.Path;
import com.hralievskyi.conferences.entity.Event;
import com.hralievskyi.conferences.exception.AppException;
import com.hralievskyi.conferences.exception.DBException;
import com.hralievskyi.conferences.exception.Messages;
import com.hralievskyi.conferences.service.EventService;
import com.hralievskyi.conferences.web.command.Command;

public class UpdateEventCommand extends Command {
	private static final long serialVersionUID = -2128237956222984960L;
	private static final Logger LOG = Logger.getLogger(UpdateEventCommand.class);
	private EventService eventService;
	private String[] parameters = { "nameUk", "nameEn", "placeUk", "placeEn", "date", "time" };

	public UpdateEventCommand() {
		eventService = new EventService();
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		if ("GET".equals(request.getMethod())) {
			try {
				Event event = eventService.getById(Long.parseLong(request.getParameter("id")));
				request.setAttribute("id", event.getId());
				request.setAttribute("nameUk", event.getNameUk());
				request.setAttribute("nameEn", event.getNameEn());
				request.setAttribute("placeUk", event.getPlaceUk());
				request.setAttribute("placeEn", event.getPlaceEn());
				request.setAttribute("date", event.getDate());
				request.setAttribute("time", event.getTime());
			} catch (Exception ex) {
				LOG.error(Messages.ERR_CAN_NOT_UPDATE_EVENT, ex);
				throw new DBException(Messages.ERR_CAN_NOT_UPDATE_EVENT, ex);
			}
			LOG.debug("Command finished");
			return Path.PAGE_UPDATE_EVENT;
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
			return Path.PAGE_UPDATE_EVENT;
		} else {
			try {
				LOG.trace("Try to update: " + request.getParameter("nameEn"));
				Event event = new Event(Long.parseLong(request.getParameter("id")),
						request.getParameter("nameEn"),
						request.getParameter("nameUk"),
						request.getParameter("placeEn"),
						request.getParameter("placeUk"),
						LocalDate.parse(request.getParameter("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
						LocalTime.parse(request.getParameter("time")));
				eventService.update(event);
				LOG.trace("Succesfully update event: " + request.getParameter("nameEn"));
				return Path.COMMAND_MODERATOR_MAIN;
			} catch (Exception ex) {
				throw new DBException(Messages.ERR_CAN_NOT_CREATE_EVENT, ex);
			}
		}
	}

	private Map<String, String> validateRequest(HttpServletRequest request) {
		LOG.trace("Start validation event form with name --> " + request.getParameter("nameEn"));
		Map<String, String> errors = new HashMap<>();
		for (int i = 0; i < parameters.length; i++) {
			if ("data".contentEquals(parameters[i]) || "time".contentEquals(parameters[i])) {
				if (validateDateTime(request.getParameter(parameters[i]), parameters[i])) {
					errors.put(parameters[i], Messages.ERR_WRANG_DATE_FORMAT + parameters[i]);
				}
			} else {
				if (validate(request.getParameter(parameters[i]))) {
					errors.put(parameters[i], Messages.ERR_CAN_NOT_BE_EMPTY);
				}

			}
		}
		LOG.trace("Finish validation event form with name --> " + request.getParameter("nameEn"));
		return errors;
	}

	private boolean validateDateTime(String parameterValue, String parameterName) {
		if ("date".equals(parameterName)) {
			try {
				LocalDate.parse(parameterValue);
			} catch (Exception ex) {
				return true;
			}
		} else {
			try {
				LocalTime.parse(parameterValue);
			} catch (Exception ex) {
				return true;
			}
		}
		return false;
	}

	private boolean validate(String parameter) {
		if (parameter == null || parameter.length() == 0)
			return true;
		return false;
	}

}
