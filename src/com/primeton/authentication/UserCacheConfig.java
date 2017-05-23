package com.primeton.authentication;

import javax.sql.DataSource;

public class UserCacheConfig {
	private DataSource datasource = null;

	private String sql = null;
	private int interval;

	public DataSource getDatasource() {
		return this.datasource;
	}

	public void setDatasource(DataSource datasource) {
		this.datasource = datasource;
	}

	public int getInterval() {
		return this.interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public String getSql() {
		return this.sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}