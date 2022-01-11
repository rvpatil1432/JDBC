package com.bridgelabz.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBDemo {

	public static void main(String[] args) {
		// step1
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded successufully..");
		}catch(ClassNotFoundException e) {
			System.out.println("Driver not loaded..");
		}
		// step2
		
		String JDBCURL = "jdbc:mysql://localhost:3306/emp_payroll_service";
		System.out.println("Connection established..");
		try {
			DriverManager.getConnection(JDBCURL,"root","Vishrohini@123");
		} catch (SQLException e) {
			System.out.println("Connection not established..");
		}

	}

}
