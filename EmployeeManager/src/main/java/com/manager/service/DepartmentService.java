package com.manager.service;

import java.util.List;

import com.manager.entity.Department;

public interface DepartmentService {

	List<Department> findAllDepartment();
	void saveDepartment(Department department);
	Department getDepartmentById(long id);
	void deleteDepartmentById(long id);
}
