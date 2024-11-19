package com.codewithsaurabh.crud_master_apis.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Wrapper;

public class OracleDBConn {
	public static void main(String[] args) {
		Connection con = null; //interface Connection  extends Wrapper, AutoCloseable
		Statement st = null;   //interface Statement extends Wrapper, AutoCloseable
		ResultSet rs = null;  //interface ResultSet extends Wrapper, AutoCloseable

		try {
			// Step 1: Load the Oracle JDBC driver
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Oracle JDBC driver loaded successfully");
		} catch (ClassNotFoundException ex) {
			System.err.println("Oracle JDBC driver not found");
			ex.printStackTrace();
		}

		try {
			// Step 2: Connect to the database
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String username = "hr";
			String password = "hr";

			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to Oracle database");

			// Step 3: Perform database operations here
			st = con.createStatement();

			// Step 4: Create a table (assuming it doesn't exist)
	 String createTableQuery = "CREATE TABLE users (userId NUMBER, name VARCHAR2(50), address VARCHAR2(100))";
			st.executeUpdate(createTableQuery);
			System.out.println("Table 'users' created successfully");

			// Step 5: Insert data into the table
			String insertDataQuery1 = "INSERT INTO users VALUES (1, 'Saurabh Kumar', '123 Kolkata')";
			String insertDataQuery2 = "INSERT INTO users VALUES (2, 'Kumar Saurabh', '456 India')";
			st.executeUpdate(insertDataQuery1);
			st.executeUpdate(insertDataQuery2);
			System.out.println("Data inserted into the 'users' table");

			// Step 6: Fetch and display data from the table
			rs = st.executeQuery("SELECT userId, name, address FROM users");
			while (rs.next()) {
				int userId = rs.getInt("userId");
				String name = rs.getString("name");
				String address = rs.getString("address");

				System.out.println("User ID: " + userId + ", Name: " + name + ", Address: " + address);
			}

			// Step 7: Close the resources (ResultSet, Statement, and Connection)
			if (rs != null) {
				rs.close();
				System.out.println("ResultSet closed");
			}

			if (st != null) {
				st.close();
				System.out.println("Statement closed");
			}

			if (con != null) {
				con.close();
				System.out.println("Connection closed");
			}
		} catch (SQLException e) {
			System.err.println("Error connecting to the database or performing operations");
			e.printStackTrace();
		}
	}
}



