package com.onlineBookStore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.onlineBookStore.dbConnection.DatabaseConnection;

public class BookOrderDao {

	private DatabaseConnection dbConnection = null;
	private Connection connection = null;

	public BookOrderDao(DatabaseConnection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public boolean placeOrder(int bookId, int customerId, int sellerId) throws SQLException {
		this.dbConnection.connect();
		this.connection = this.dbConnection.getConnection();
		String query = "INSERT INTO bookorder(`bookId`, `customerId`, `sellerId`) VALUES(?, ?, ?)";
		
		try {
			PreparedStatement prst = this.connection.prepareStatement(query);
			prst.setInt(1, bookId);
			prst.setInt(2, customerId);
			prst.setInt(3, sellerId);
			
			int count = prst.executeUpdate();
			
			prst.close();
			this.dbConnection.disconnect();
			
			if(count == 1) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<BookOrder> getOrderDetails(int customerId) throws SQLException {

		this.dbConnection.connect();
		this.connection = this.dbConnection.getConnection();
		
		String query = "SELECT * FROM bookorder WHERE customerId = '" + customerId + "' ";
		List<BookOrder> listOfOrders = new ArrayList<BookOrder>();
		BookOrder order = null;
		BookDao bookDao = new BookDao(this.dbConnection);
		
		try {
			
			Statement stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				order = new BookOrder();
				order.setOrderId(rs.getInt("orderId"));
				order.setBookId(rs.getInt("bookId"));
				order.setCustomerId(rs.getInt("customerId"));
				order.setSellerId(rs.getInt("sellerId"));
				order.setDate(rs.getDate("timeOfPurchase"));
				order.setBook(bookDao.getBookDetails(order.getBookId()));
				
				listOfOrders.add(order);
			}
			stmt.close();
			this.dbConnection.disconnect();
			
			return listOfOrders;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
