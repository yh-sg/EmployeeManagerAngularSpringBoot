package com.MyFullStack.EmployeeManager.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MyFullStack.EmployeeManager.exception.UserNotFoundException;
import com.MyFullStack.EmployeeManager.model.Employee;
import com.MyFullStack.EmployeeManager.repository.EmployeeRepository;

@Service
public class EmployeeService {
	private final EmployeeRepository employeeRepo;
	
	@Autowired
	public EmployeeService(EmployeeRepository employeeRepo) {
		this.employeeRepo = employeeRepo;
	}
	
	public List<Employee> getAllEmployee(){
		return employeeRepo.findAll();
	}
	
	public Employee getEmployee(Long id) {
		return employeeRepo.findById(id)
				.orElseThrow(()-> new UserNotFoundException("User id" + id + " was not found"));
	}
	
	public Employee addEmployee(Employee employee) {
		employee.setEmployeeCode(UUID.randomUUID().toString());
		return employeeRepo.save(employee);
	}
	
	public Employee updateEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	public void deleteEmployee(Long id) {
		employeeRepo.deleteById(id);
	}
}
