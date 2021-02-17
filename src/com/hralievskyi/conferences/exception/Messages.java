package com.hralievskyi.conferences.exception;

public class Messages {

	private Messages() {
		// no op
	}

	public static final String ERR_CANNOT_OBTAIN_CONNECTION = "Cannot obtain a connection from the pool";

	public static final String ERR_CANNOT_OBTAIN_ALL_USERS = "Cannot obtain all users";

	public static final String ERR_CANNOT_OBTAIN_USER_BY_LOGIN = "Cannot obtain a user by its login";

	public static final String ERR_CANNOT_UPDATE_USER = "Cannot update a user";

	public static final String ERR_CANNOT_CLOSE_CONNECTION = "Cannot close a connection";

	public static final String ERR_CANNOT_CLOSE_RESULTSET = "Cannot close a result set";

	public static final String ERR_CANNOT_CLOSE_STATEMENT = "Cannot close a statement";

	public static final String ERR_CANNOT_OBTAIN_DATA_SOURCE = "Cannot obtain the data source";

	public static final String ERR_NO_COMMAND = "Can not send response.";

	// register validation
	public static final String ERR_EMAIL_CAN_NOT_BE_EMPTY = "Email can't be empty.";

	public static final String ERR_EMAIL_MUST_BE_CORRECT = "Email must be correct.";

	public static final String ERR_PASSWORD_CAN_NOT_BE_EMPTY = "Password must be at least 5  and less then 25 characters long";

	public static final String ERR_PASSWORD_DO_NOT_MATCH = "Passwords must match.";

	public static final String ERR_INVALID_LOGIN = "Password or login are incorrect.";

	public static final String ERR_EMPTY_LOGIN = "Password or login can not be empty.";

	// User
	public static final String ERR_CAN_NOT_CREATE_USER = "Can't create user.";

	public static final String WARN_USER_ALREADY_EXISTS = "User already exists";

}
