package com.hralievskyi.conferences.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hralievskyi.conferences.exception.AppException;
import com.hralievskyi.conferences.exception.Messages;

public class NoCommand extends Command {
	private static final long serialVersionUID = 6277985006255092065L;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
		throw new AppException(Messages.ERR_NO_COMMAND);
	}

}
