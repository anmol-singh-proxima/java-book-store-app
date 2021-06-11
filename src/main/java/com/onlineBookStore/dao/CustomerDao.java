package com.onlineBookStore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.onlineBookStore.dbConnection.DatabaseConnection;

public class CustomerDao {
	
	private DatabaseConnection dbConnection = null;
	private Connection connection = null;

	public CustomerDao(DatabaseConnection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public Customer loginCheck(String email, String password) throws SQLException {
		
		this.dbConnection.connect();
		this.connection = this.dbConnection.getConnection();
		
		String query = "SELECT * FROM customer WHERE email = '" + email + "' AND password = '" + password + "' ";
		Customer customer = null;
		
		try {
			
			Statement stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				customer = new Customer();
				customer.setCustomerId(rs.getInt("customerId"));
				customer.setName(rs.getString("name"));
				customer.setEmail(rs.getString("email"));
				customer.setPhone(rs.getString("phone"));
				customer.setAddress(rs.getString("address"));
				customer.setPassword(rs.getString("password"));
			}
			stmt.close();
			this.dbConnection.disconnect();
			
			System.out.println("Customer: " + customer);
			
			if(customer != null) {
				return customer;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Customer getUserData(int customerId) throws SQLException {

		this.dbConnection.connect();
		this.connection = this.dbConnection.getConnection();
		
		String query = "SELECT * FROM customer WHERE customerId = '" + customerId + "' ";
		Customer customer = null;
		
		try {
			
			Statement stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				customer = new Customer();
				customer.setCustomerId(rs.getInt("customerId"));
				customer.setName(rs.getString("name"));
				customer.setEmail(rs.getString("email"));
				customer.setPhone(rs.getString("phone"));
				customer.setAddress(rs.getString("address"));
				customer.setPassword(rs.getString("password"));
			}
			stmt.close();
			this.dbConnection.disconnect();
			
			if(customer != null) {
				return customer;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public boolean registerCustomer(Customer customer) throws SQLException {
		
		this.dbConnection.connect();
		this.connection = this.dbConnection.getConnection();
		String query = "INSERT INTO `customer` (`name`, `email`, `phone`, `address`, `password`) VALUES (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement prst = this.connection.prepareStatement(query);
			prst.setString(1, customer.getName());
			prst.setString(2, customer.getEmail());
			prst.setString(3, customer.getPhone());
			prst.setString(4, customer.getAddress());
			prst.setString(5, customer.getPassword());
			
			int count = prst.executeUpdate();
			if(count == 1) {
				return true;
			}
			
			prst.close();
			this.dbConnection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
