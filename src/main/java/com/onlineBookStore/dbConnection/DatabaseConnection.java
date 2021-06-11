package com.onlineBookStore.dbConnection;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	Connection connection = null;
	String dbUrl = null;
	String dbUsername = null;
	String dbPassword = null;
	
//	public DatabaseConnection(String dbUrl, String dbUsername, String dbPassword) {
//		this.dbUrl = dbUrl;
//		this.dbUsername = dbUsername;
//		this.dbPassword = dbPassword;
//	}
	
	public DatabaseConnection() {
		this.dbUrl = "jdbc:mysql://localhost:3306/online_book_store";
		this.dbUsername = "root";
		this.dbPassword = "";
	}
	
	public void connect() throws SQLException {
		if (connection == null || connection.isClosed()) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(this.dbUrl, this.dbUsername, this.dbPassword);
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
		}
	}
	
	public void disconnect() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			connection.close();
		}
	}
	
	public Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(this.dbUrl, this.dbUsername, this.dbPassword);
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}
		return this.connection;
	}
}
