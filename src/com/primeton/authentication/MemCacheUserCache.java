package com.primeton.authentication;

import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import net.spy.memcached.MemcachedClient;

public class MemCacheUserCache implements IUserCache {
	private final MemcachedClient client;

	public MemCacheUserCache(String[] hostnames) {
		List addresses = new ArrayList();

		for (String hostname : hostnames) {
			String[] hostPort = hostname.split(":");
			addresses.add(new InetSocketAddress(hostPort[0], Integer
					.parseInt(hostPort[1])));
		}
		try {
			this.client = new MemcachedClient(addresses);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

	public String getPassword(String username) {
		username = username.replaceAll(" ", "______");
		Object value = this.client.get(username);
		if (value != null)
			return (String) value;
		return null;
	}

	public void removeUser(String username) {
		username = username.replaceAll(" ", "______");
		this.client.delete(username);
	}

	public void setPassword(String username, String password) {
		try {
			username = username.replaceAll(" ", "______");
			this.client.set(username, 0, password);
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("[error][key:" + username + "][value:"
					+ password + "]");
		}
	}
}