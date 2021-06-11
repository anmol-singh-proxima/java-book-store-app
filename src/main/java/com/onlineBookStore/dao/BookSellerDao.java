package com.onlineBookStore.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.onlineBookStore.dbConnection.DatabaseConnection;

public class BookSellerDao {

	private DatabaseConnection dbConnection = null;
	private Connection connection = null;

	public BookSellerDao(DatabaseConnection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public BookSeller getSellerDetails(int sellerId) throws SQLException {
		this.dbConnection.connect();
		this.connection = this.dbConnection.getConnection();
		
		String query = "SELECT * FROM bookseller WHERE sellerId = '" + sellerId + "' ";
		BookSeller seller = null;
		
		try {
			Statement stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				seller = new BookSeller();
				seller.setSellerId(rs.getInt("sellerId"));
				seller.setOwnerName(rs.getString("ownerName"));
				seller.setOwnerEmail(rs.getString("ownerEmail"));
				seller.setOwnerPhone(rs.getString("ownerPhone"));
				seller.setBookShopName(rs.getString("bookShopName"));
				seller.setAddress(rs.getString("address"));
				seller.setAadharNumber(rs.getString("aadharNumber"));
				seller.setPanNumber(rs.getString("panNumber"));
				seller.setPassword(rs.getString("password"));
			}
			stmt.close();
			this.dbConnection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seller;
	}

}
