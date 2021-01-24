package com.manager.service;

import java.util.List;

import com.manager.entity.EmployeeTeamProject;

public interface EmployeeTeamProjectService {

	List<EmployeeTeamProject> findAllEmployeeTeamProject();
	void saveEmployeeTeamProject(EmployeeTeamProject employeeTeamProject);
	EmployeeTeamProject getEmployeeTeamProjectById(long id);
	void deleteEmployeeTeamProjectById(long id);
}
