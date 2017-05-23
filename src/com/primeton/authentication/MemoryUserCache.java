package com.primeton.authentication;

import java.util.HashMap;

public class MemoryUserCache implements IUserCache {
	private static HashMap<String, String> users = new HashMap(5000);

	public String getPassword(String username) {
		return (String) users.get(username);
	}

	public void setPassword(String username, String password) {
		users.put(username, password);
	}

	public void removeUser(String username) {
		users.remove(username);
	}
}