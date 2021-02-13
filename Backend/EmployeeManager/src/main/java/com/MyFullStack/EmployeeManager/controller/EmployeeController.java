package com.MyFullStack.EmployeeManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MyFullStack.EmployeeManager.model.Employee;
import com.MyFullStack.EmployeeManager.service.EmployeeService;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
	
	@Autowired
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		List<Employee> employeees = employeeService.getAllEmployee();
		return new ResponseEntity<>(employeees, HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<Employee> getOneEmployee(@PathVariable Long id){
		Employee employeee = employeeService.getEmployee(id);
		return new ResponseEntity<>(employeee, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		Employee addEmployee = employeeService.addEmployee(employee);
		return new ResponseEntity<>(addEmployee, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		Employee updateEmployee = employeeService.updateEmployee(employee);
		return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
