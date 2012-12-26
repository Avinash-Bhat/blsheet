package com.olam.blsheet.beans;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.olam.blsheet.database.ConnectionPool;

public class User implements Serializable{
	private static final long serialVersionUID = 6534391401726293642L;
	
	private String name;
	private String description;
	private int id;
	private String mobileNumber;
	private int status;
	List<User> userList;
	private String password;
	private boolean loggedIn;

	public User() {
	}

	public User(String name, String password) {
		super();
		this.name = name;
		setPassword(password);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		try {
			this.password = byteToString(MessageDigest.getInstance("SHA-1")
					.digest(password.getBytes()));
			System.out.println("password(excrypted): " + this.password);
		} catch (NoSuchAlgorithmException e) {
			System.err.println("FATAL: cannot load the algorithm");
			System.exit(1);
		}
	}

	public boolean isAuthenticated() {
		if (loggedIn) {
			return loggedIn;
		}
		System.out.println("name: " + name + ", password: " + password);
		String query = "Select * FROM login_details log where log.USER_NAME='"
				+ name + "' and log.PASSWORD='" + password + "'";
		System.out.println("SQL : " + query);
		Connection con = null;
		try {
			con = ConnectionPool.getConnection();
			Statement stmt;
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				System.out.println(rs.getString(1) + ", " + rs.getString(2)
						+ ", " + rs.getString(3) + ", " + rs.getString(4));
				id = rs.getInt(4);
				return (loggedIn = true);
			} else {
				throw new IllegalArgumentException("Not an authentic user");
			}
		} catch (SQLException e) {
			throw new IllegalArgumentException("Cannot connect to database");
		} finally {
			System.out.println("Connection closed : "
					+ ConnectionPool.closeConnection(con));
		}
	}

	private static String byteToString(byte[] src) {
		StringBuilder sb = new StringBuilder();
		for (byte b : src) {
			sb.append(String.format("%02x", b & 0xff));
		}
		return sb.toString();
	}

	@Override
	public String toString() {
		return "UserDetails [name=" + name + ", description=" + description
				+ ", userId=" + id + ", mobileNumber=" + mobileNumber
				+ ", status=" + status + ", userList=" + userList
				+ ", password=" + password + ", loggedIn=" + loggedIn + "]";
	}
}
