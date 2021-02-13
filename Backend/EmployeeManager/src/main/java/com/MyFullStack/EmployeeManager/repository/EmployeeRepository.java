package com.MyFullStack.EmployeeManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.MyFullStack.EmployeeManager.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
