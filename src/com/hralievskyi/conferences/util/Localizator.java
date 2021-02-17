package com.hralievskyi.conferences.util;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

public class Localizator {

	public static void set(HttpServletRequest request) {
		String loc = (String) request.getParameter("lang") != null ? request.getParameter("lang") : request.getParameter("local");
		request.setAttribute("local", loc);
	}

	public static String convert(HttpServletRequest request, String message) {
		String loc = (String) request.getParameter("lang") != null ? request.getParameter("lang") : request.getParameter("local");
		request.setAttribute("local", loc);
		Locale locale = new Locale.Builder().setLanguage("en").build();
		ResourceBundle rb = ResourceBundle.getBundle("Messages", locale);
		return rb.getString(message);
	}
}
