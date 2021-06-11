package com.onlineBookStore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.onlineBookStore.dbConnection.DatabaseConnection;

public class BookDao {
	
	private DatabaseConnection dbConnection = null;
	private Connection connection = null;
	
	public BookDao(DatabaseConnection dbConnection) {
		this.dbConnection = dbConnection;
	}


	// Get the Category of a Book by using categoryId
	public String getCategory(int categoryId) throws SQLException {
		
		String query = "SELECT * FROM category WHERE categoryId = '" + categoryId + "' ";
		
		this.dbConnection.connect();
		this.connection = this.dbConnection.getConnection();
		
		String category = null;
		
		try {
			
			Statement stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				category = rs.getString("categoryName");
			}
			
			stmt.close();
			this.dbConnection.disconnect();
			
			return category;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	// Get a single book details based on the given result set of a query
	public Book getBook(ResultSet rs) throws SQLException {
		Book book = new Book();
		int categoryId = rs.getInt("categoryId");
		book.setBookId(rs.getInt("bookId"));
		book.setBookName(rs.getString("bookName"));
		book.setAuthorName(rs.getString("authorName"));
		book.setPublisherHouse(rs.getString("publisherHouse"));
		book.setYearOfPublishing(rs.getInt("yearOfPublishing"));
		book.setCategory(this.getCategory(categoryId));
		book.setNumOfPages(rs.getInt("numOfPages"));
		book.setPrice(rs.getInt("price"));
		book.setSellerId(rs.getInt("sellerId"));
		book.setStock(rs.getInt("stock"));
		book.setNumOfPurchases(rs.getInt("numOfPurchases"));
		return book;
	}
	
	
	//  Get the list of all the books
	public List<Book> getAllBooks() throws SQLException {
		
		String query = "SELECT * FROM book";
		this.dbConnection.connect();
		this.connection = this.dbConnection.getConnection();
		
		List<Book> listOfBooks = new ArrayList<Book>();
		Book book;
		
		try {
			
			Statement stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				book = this.getBook(rs);
				listOfBooks.add(book);
			}
			
			stmt.close();
			this.dbConnection.disconnect();
			
			return listOfBooks;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	// Get the list of the books based on the search criteria
	public List<Book> searchBooks(String searchedItem) throws SQLException {
		
		String query = "SELECT * FROM book WHERE "
				+ "bookName LIKE '%" + searchedItem + "%' OR "
				+ "authorName LIKE '%" + searchedItem + "%' OR "
				+ "publisherHouse LIKE '%" + searchedItem + "%' ";
		
		this.dbConnection.connect();
		this.connection = this.dbConnection.getConnection();
		
		List<Book> listOfBooks = new ArrayList<Book>();
		Book book;
		
		try {
			
			Statement stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				book = this.getBook(rs);
				listOfBooks.add(book);
			}
			
			stmt.close();
			this.dbConnection.disconnect();
			
			return listOfBooks;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	// Get the details of a book based on the bookId
	public Book getBookDetails(int bookId) throws SQLException {
		String query = "SELECT * FROM book WHERE bookId = '" + bookId + "' ";
		this.dbConnection.connect();
		this.connection = this.dbConnection.getConnection();
		Book book = null;
		
		try {
			
			Statement stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				book = this.getBook(rs);
			}
			
			stmt.close();
			this.dbConnection.disconnect();
			
			return book;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}


	// Update the stock of the books whenever a transaction happened
	public boolean updateStock(int bookId) throws SQLException {
		String query = "SELECT * FROM book WHERE bookId = '" + bookId + "' ";
		this.dbConnection.connect();
		this.connection = this.dbConnection.getConnection();
		
		try {	
			Statement stmt = this.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				int stock = rs.getInt("stock");
				int purchase = rs.getInt("numOfPurchases");
				
				query = "UPDATE book SET stock = ?, numOfPurchases = ? WHERE bookId = ?";
				PreparedStatement prst = this.connection.prepareStatement(query);
				prst.setInt(1, (stock - 1));
				prst.setInt(2, (purchase + 1));
				prst.setInt(3, bookId);
				
				int count = prst.executeUpdate();
				prst.close();
				if(count == 1) {
					return true;
				}
			}
			stmt.close();
			this.dbConnection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
}
