package com.hralievskyi.conferences.entity;

public class User extends Entity {

	private static final long serialVersionUID = -6889036256149495388L;

	private String username;

	private String password;

	private int roleId;

	public User() {

	}

	public User(String username, String password, int roleId) {
		this.username = username;
		this.password = password;
		this.roleId = roleId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String login) {
		this.username = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", roleId=" + roleId + "]";
	}

}
