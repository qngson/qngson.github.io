package com.manager.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.entity.Department;
import com.manager.repository.DepartmentRepository;
import com.manager.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public List<Department> findAllDepartment() {
		return departmentRepository.findAll();
	}

	@Override
	public void saveDepartment(Department department) {
		this.departmentRepository.save(department);
		
	}

	@Override
	public Department getDepartmentById(long id) {
		Optional<Department> optional = departmentRepository.findById(id);
		Department department = null;
		if(optional.isPresent()) {
			department = optional.get();
		}else {
			throw new RuntimeException(" Department not found for id::" +id);
		}
		return department;
		
	}

	@Override
	public void deleteDepartmentById(long id) {
		this.departmentRepository.deleteById(id);
	}

}
