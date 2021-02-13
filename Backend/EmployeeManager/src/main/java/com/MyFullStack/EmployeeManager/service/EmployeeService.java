package com.MyFullStack.EmployeeManager.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.MyFullStack.EmployeeManager.model.Employee;
import com.MyFullStack.EmployeeManager.repository.EmployeeRepository;

public class EmployeeService {
	private final EmployeeRepository employeeRepo;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	
	public Employee addEmployee(Employee employee) {
		return null;
	}
}
