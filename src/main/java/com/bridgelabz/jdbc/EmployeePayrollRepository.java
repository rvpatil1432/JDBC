package com.bridgelabz.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class EmployeePayrollRepository {
	public Connection getConnection() {
		Connection connection = null;
		try {
			String JDBCURL = "jdbc:mysql://localhost:3306/emp_payroll_service";
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(JDBCURL,"root","Vishrohini@123");
		}catch(ClassNotFoundException | SQLException e) {

		}
		return connection;
	}
	public List<EmployeeInfo> retrieveData() {
		List<EmployeeInfo> employeeInfos = new ArrayList<EmployeeInfo>();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			String sqlQuery = "select * from employee";
			ResultSet resultSet = statement.executeQuery(sqlQuery);
			while(resultSet.next()) {
				EmployeeInfo info = new EmployeeInfo();
				info.setId(resultSet.getInt("id"));
				info.setName(resultSet.getString("name"));
				info.setGender(resultSet.getString("gender").charAt(0));
				info.setStartDate(resultSet.getDate("startDate").toLocalDate());
				info.setPhone(resultSet.getString("phone"));
				info.setAddress(resultSet.getString("address"));
				employeeInfos.add(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employeeInfos;	
	}
	public void updateSalary(String name, double salary) {
		try(Connection connection = getConnection()){
			Statement statement = connection.createStatement();
		//	String sqlQuery = "update employee set salary = "+salary+ " where name='"+name+"'";
			String sqlQuery = "update employee e, payroll p set p.basic_pay = "+salary+ " where e.name='"+name+"' and e.id = p.emp_id";
			System.out.println(sqlQuery);
			int result = statement.executeUpdate(sqlQuery);
			System.out.println(result);
			if(result >= 1) {
				System.out.println("Salary Updated..");
			}
		}catch(SQLException e) {
			
		}
	}
	public void updateSalaryUsingPreparedStatement(String name, double salary) {
		try(Connection connection = getConnection()){
			//String sqlQuery = "update employee set salary = ? where name = ?";
			String sqlQuery = "update employee e, payroll p set p.basic_pay = ? where e.name=? and e.id = p.emp_id";
			PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
			preparedStatement.setDouble(1, salary);
			preparedStatement.setString(2, name);
			int result = preparedStatement.executeUpdate();
			if(result >= 1) {
				System.out.println("Salary Updated..");
			}
		}catch(SQLException e) {
			
		}
	}

}
