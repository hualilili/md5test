package com.primeton.authentication;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import javax.sql.DataSource;

public class UserCacheUtil {
	private UserCacheConfig userCacheConfig = null;

	private IUserCache userCache = null;

	public void loadUserInfo() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.userCacheConfig.getDatasource().getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(this.userCacheConfig.getSql());
			while (rs.next()) {
				this.userCache.setPassword(rs.getString(1), rs.getString(2));
			}
			stmt.close();
		} catch (Throwable ex) {
			System.err.println("[sso-server][loadUserInfo][sql:"
					+ this.userCacheConfig.getSql() + "]" + ex.getMessage());
			ex.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateUserInfo() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.userCacheConfig.getDatasource().getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();

			rs = stmt
					.executeQuery("select username,password,op from portal_user_update for update order by TIME ");

			while (rs.next()) {
				String op = rs.getString(3);
				if (op.equals("update")) {
					this.userCache
							.setPassword(rs.getString(1), rs.getString(2));
				} else {
					this.userCache.removeUser(rs.getString(1));
				}
			}

			stmt.execute("delete from portal_user_update ");
			conn.commit();
		} catch (Throwable e) {
			System.err.println("[sso-server][updateUserInfo]" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void startUpdateTask() {
		Timer timer = new Timer(false);
		timer.schedule(new UserUpdateTask(this), 1000L,
				this.userCacheConfig.getInterval());
	}

	public UserCacheConfig getUserCacheConfig() {
		return this.userCacheConfig;
	}

	public void setUserCacheConfig(UserCacheConfig userCacheConfig) {
		this.userCacheConfig = userCacheConfig;
	}

	public IUserCache getUserCache() {
		return this.userCache;
	}

	public void setUserCache(IUserCache userCache) {
		this.userCache = userCache;
	}
}
