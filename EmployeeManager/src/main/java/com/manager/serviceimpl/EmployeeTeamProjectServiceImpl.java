package com.manager.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.entity.EmployeeTeamProject;
import com.manager.repository.EmployeeTeamProjectRepository;
import com.manager.service.EmployeeTeamProjectService;

@Service
public class EmployeeTeamProjectServiceImpl implements EmployeeTeamProjectService {

	@Autowired
	EmployeeTeamProjectRepository employeeTeamProjectRepository;

	@Override
	public List<EmployeeTeamProject> findAllEmployeeTeamProject() {
		return employeeTeamProjectRepository.findAll();
	}

	@Override
	public void saveEmployeeTeamProject(EmployeeTeamProject employeeTeamProject) {
		this.employeeTeamProjectRepository.save(employeeTeamProject);
		
	}

	@Override
	public EmployeeTeamProject getEmployeeTeamProjectById(long id) {
		Optional<EmployeeTeamProject> optional = employeeTeamProjectRepository.findById(id);
		EmployeeTeamProject employeeTeamProject = null;
		if(optional.isPresent()) {
			employeeTeamProject = optional.get();
		}else {
			throw new RuntimeException(" EmployeeTeamProject not found for id::" +id);
		}
		return employeeTeamProject;
		
	}

	@Override
	public void deleteEmployeeTeamProjectById(long id) {
		this.employeeTeamProjectRepository.deleteById(id);
	}

}
