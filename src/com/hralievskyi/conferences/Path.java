package com.hralievskyi.conferences;

public final class Path {

	// common
	public static final String PAGE_LOGIN = "/WEB-INF/jsp/login.jsp";
	public static final String PAGE_REGISTER = "/WEB-INF/jsp/register.jsp";
	public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";

	// user
	public static final String COMMAND_USER_MAIN = "/app?command=user";
	public static final String PAGE_USER_MAIN = "/WEB-INF/jsp/user/user_main.jsp";
	public static final String PAGE_USER_EVENT = "/WEB-INF/jsp/user/event_details.jsp";
	public static final String PAGE_USER_CABINET = "/WEB-INF/jsp/user/cabinet.jsp";
	// moderator
	public static final String COMMAND_MODERATOR_MAIN = "/app?command=moderator";
	public static final String PAGE_MODERATOR_MAIN = "/WEB-INF/jsp/moderator/moderator_main.jsp";
	public static final String PAGE_CREATE_EVENT = "/WEB-INF/jsp/moderator/create_event.jsp";
	public static final String PAGE_UPDATE_EVENT = "/WEB-INF/jsp/moderator/update_event.jsp";
	public static final String PAGE_MOD_CREATE_REPORT = "/WEB-INF/jsp/moderator/create_report.jsp";
	public static final String PAGE_MODERATOR_EVENT = "/WEB-INF/jsp/moderator/event_details.jsp";
	public static final String PAGE_ADD_REPORTS = "/WEB-INF/jsp/moderator/add_reports.jsp";
	public static final String PAGE_MODERATOR_SPEAKERS = "/WEB-INF/jsp/moderator/speakers.jsp";
	public static final String COMMAND_MODERATOR_SPEAKERS = "/app?command=moderator_speakers";
	// speaker
	public static final String PAGE_SPEAKER_CABINET = "/WEB-INF/jsp/speaker/speaker_cabinet.jsp";
	public static final String COMMAND_SPEAKER_CABINET = "/app?command=speaker_cabinet";
	public static final String PAGE_SP_CREATE_REPORT = "/WEB-INF/jsp/speaker/create_report.jsp";

}
