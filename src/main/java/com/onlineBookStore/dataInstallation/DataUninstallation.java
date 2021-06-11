package com.onlineBookStore.dataInstallation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.onlineBookStore.dbConnection.DatabaseConnection;

public class DataUninstallation {

	DatabaseConnection dbConnection = null;
	Connection connection = null;
	String[] deleteTableData = null;
	
	public DataUninstallation(DatabaseConnection dbConnection) {
		this.dbConnection = dbConnection;
		this.setQueries();
	}
	
	public void deleteDataFromTables() throws SQLException {
		this.dbConnection.connect();
		this.connection = this.dbConnection.getConnection();
		
		Statement stmt = this.connection.createStatement();
		
		for(int i=0; i<5; i++) {
			stmt.executeUpdate(this.deleteTableData[i]);
		}
		System.out.println("Entire Data Deleted");
		
		this.dbConnection.disconnect();
	}
	
	public void setQueries() {
		String deleteTableData[] = new String[] {
			"TRUNCATE TABLE book",
			"TRUNCATE TABLE bookOrder",
			"TRUNCATE TABLE bookSeller",
			"TRUNCATE TABLE category",
			"TRUNCATE TABLE customer"
		};
		this.deleteTableData = deleteTableData;
	}
	
}
