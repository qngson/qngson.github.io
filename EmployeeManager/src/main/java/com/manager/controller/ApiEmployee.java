package com.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manager.entity.Employee;
import com.manager.service.EmployeeService;

@RestController
@RequestMapping("/apiemployee")
public class ApiEmployee {

	@Autowired
	EmployeeService employeeService;
	


	@GetMapping("")
	public ResponseEntity<?> getListEmployee() {
		List<Employee> listEmp = employeeService.getEmp();
		System.out.println(listEmp.size());
		
		return ResponseEntity.ok(listEmp);
	}
}
