package com.manager.service;

import java.util.List;

import javax.persistence.Entity;

import com.manager.entity.Employee;


public interface EmployeeService {

	List<Employee> findAllEmployee();
	void saveEmployee(Employee employee);
	Employee getEmployeeById(long id);
	void deleteEmployeeById(long id);
	List<Employee> getEmp();

}
