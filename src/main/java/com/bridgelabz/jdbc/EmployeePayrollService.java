package com.bridgelabz.jdbc;

public class EmployeePayrollService {
	EmployeePayrollRepository repository = new EmployeePayrollRepository();
	public static void main(String[] args) {
		EmployeePayrollService service = new EmployeePayrollService();
		service.retrieveData();
		//service.updateSalary("ABCCCC",500000);
		service.updateSalaryUsingPreparedStatement("ABCCCC",400000);
	}

	private void updateSalaryUsingPreparedStatement(String name, double salary) {
		repository.updateSalaryUsingPreparedStatement(name,salary);
	}

	private void updateSalary(String name, double salary) {
		repository.updateSalary(name,salary);
	}

	private void retrieveData() {
	System.out.println(repository.retrieveData());
	}
}
