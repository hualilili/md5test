package com.primeton.authentication;

public abstract interface IUserCache {
	public abstract String getPassword(String paramString);

	public abstract void setPassword(String paramString1, String paramString2);

	public abstract void removeUser(String paramString);
}