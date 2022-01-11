package com.bridgelabz.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTransaction {

	public static void main(String[] args) throws SQLException {
		Connection connection = null;
		try {
			connection = new EmployeePayrollRepository().getConnection();
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			System.out.println(e);
		}
		int empId = 0;
		try (Statement statement1 = connection.createStatement()) {
			String sql = String.format("insert into employee(name,gender,startDate,phone,address) " +
					"values ('%s','%s','%s',%d,'%s') ", "ABCCCC", "M", "2021-01-10", 5465656, "addresss");

			int rowsAffected = statement1.executeUpdate(sql, statement1.RETURN_GENERATED_KEYS);
			if (rowsAffected == 1) {
				System.out.println(" employee data added");
				System.out.println(statement1.getGeneratedKeys());
				ResultSet resultSet = statement1.getGeneratedKeys();
				if (resultSet.next()) {
					empId = resultSet.getInt(1);
				}
			}
			System.out.println(empId);
		} catch (SQLException e) {
			System.out.println(e);
			connection.rollback();
		}
		int salary = 20000;
		try (Statement statement1 = connection.createStatement()) {
			int deductions = (int) (salary * 0.2);
			int taxablePay = salary - deductions;
			int tax = (int) (taxablePay * 0.1);
			int netPay = salary - tax;
			String sql = String.format("insert into payroll (basic_pay,deductions,taxable_pay,income_tax,net_pay,emp_Id) " +
					"values (%d,%d,%d,%d,%d,%d)", salary,deductions, taxablePay, tax, netPay, empId);

			int rowAffected = statement1.executeUpdate(sql);
			if (rowAffected >= 1) {
				System.out.println("Payroll data added");
			}
		} catch (SQLException e) {
			System.out.println("exception" +e.getMessage());
			connection.rollback();
		}catch (Exception e){
			System.out.println("exception" +e.getMessage());
			connection.rollback();
		}
		try {
			connection.commit();
		} finally {
			connection.close();
		}
	}
}