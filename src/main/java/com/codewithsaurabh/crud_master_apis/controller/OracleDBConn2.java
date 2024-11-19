package com.codewithsaurabh.crud_master_apis.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleDBConn2 {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// Step 1: Load the Oracle JDBC driver
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Oracle JDBC driver loaded successfully");
		} catch (ClassNotFoundException ex) {
			// Exception handling for Class.forName()
			System.err.println("Oracle JDBC driver not found");
			ex.printStackTrace();
		}

		try {
			// Step 2: Connect to the Oracle database
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String username = "hr";
			String password = "hr";

			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connected to Oracle database");

			// Step 3: Perform database operations here

			// Step 4: Fetch the data from database operations here

			// Step 4: Close the resource Connection
			if (rs != null) {
				rs.close();
				System.out.println("ResultSet closed");
			}

			if (st != null) {
				st.close();
				System.out.println("Statement closed");
			}

			if (con != null) {
				st.close();
				System.out.println("Statement closed");
			}

		} catch (SQLException e) {
			System.err.println("Error connecting to the database");
			e.printStackTrace();
		}
	}
}
