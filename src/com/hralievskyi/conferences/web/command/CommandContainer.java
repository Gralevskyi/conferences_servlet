package com.hralievskyi.conferences.web.command;

import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.hralievskyi.conferences.web.command.moderator.AddReportsCommand;
import com.hralievskyi.conferences.web.command.moderator.CreateEventCommand;
import com.hralievskyi.conferences.web.command.moderator.ManageSpeakersCommand;
import com.hralievskyi.conferences.web.command.moderator.ModeratorCreateReportCommand;
import com.hralievskyi.conferences.web.command.moderator.ModeratorEventDetailsCommand;
import com.hralievskyi.conferences.web.command.moderator.ModeratorMainPageCommand;
import com.hralievskyi.conferences.web.command.moderator.SuggestedCommand;
import com.hralievskyi.conferences.web.command.moderator.UpdateEventCommand;
import com.hralievskyi.conferences.web.command.speaker.SpeakerActionsCommand;
import com.hralievskyi.conferences.web.command.speaker.SpeakerCabinetCommand;
import com.hralievskyi.conferences.web.command.speaker.SpeakerCreateReportCommand;
import com.hralievskyi.conferences.web.command.user.SubscribeCommand;
import com.hralievskyi.conferences.web.command.user.UserCabinetCommand;
import com.hralievskyi.conferences.web.command.user.UserEventDetailsCommand;
import com.hralievskyi.conferences.web.command.user.UserMainPageCommand;
import com.hralievskyi.conferences.web.command.user.UserVisitCommand;

public class CommandContainer {
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);

	private static Map<String, Command> commands = new TreeMap<String, Command>();

	static {

		// common commands
		commands.put("register", new RegisterCommand());
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("viewSettings", new ViewSettingsCommand());
		// user commands
		commands.put("user", new UserMainPageCommand());
		commands.put("user_event", new UserEventDetailsCommand());
		commands.put("subscribe", new SubscribeCommand());
		commands.put("user_cabinet", new UserCabinetCommand());
		commands.put("visit", new UserVisitCommand());
		commands.put("moderator", new ModeratorMainPageCommand());
		commands.put("noCommand", new NoCommand());

		// moderator commands
		commands.put("moderator_cabinet", new ModeratorMainPageCommand());
		commands.put("moderator_event", new ModeratorEventDetailsCommand());
		commands.put("create_event", new CreateEventCommand());
		commands.put("moderator_create_report", new ModeratorCreateReportCommand());
		commands.put("update", new UpdateEventCommand());
		commands.put("add_reports", new AddReportsCommand());
		commands.put("moderator_speakers", new ManageSpeakersCommand());
		commands.put("suggested", new SuggestedCommand());

		// speaker commands
		commands.put("speaker_cabinet", new SpeakerCabinetCommand());
		commands.put("action", new SpeakerActionsCommand());
		commands.put("speaker_create", new SpeakerCreateReportCommand());

		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.trace("Command not found, name --> " + commandName);
			return commands.get("noCommand");
		}

		return commands.get(commandName);
	}

	/**
	 * Returns command object with the given name.
	 * 
	 * @param commandName Name of the command.
	 * @return Command object.
	 */
	/*
	 * public static Command get(HttpServletRequest request) { String commandName =
	 * getUri(request); // substring from servlet url - "/app" if (commandName ==
	 * null || !commands.containsKey(commandName)) {
	 * LOG.trace("Command not found, name --> " + commandName); return
	 * commands.get("noCommand"); }
	 * 
	 * return commands.get(commandName); }
	 */

	/**
	 * This is a helper method to get command mapping from uri.
	 *
	 * @param request http request from servlet.
	 * @return Command mapping.
	 */
	public static String getUri(HttpServletRequest request) {
		String mapping = request.getRequestURI().substring(request.getContextPath().length()).substring(4);
		if (mapping.endsWith("/")) {
			mapping = mapping.substring(0, mapping.length() - 1);
		}

		return mapping;
	}
}
