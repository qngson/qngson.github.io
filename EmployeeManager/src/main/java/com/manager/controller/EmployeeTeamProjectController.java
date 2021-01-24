package com.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manager.entity.Department;
import com.manager.entity.Employee;
import com.manager.entity.EmployeeTeamProject;
import com.manager.entity.TeamProject;
import com.manager.service.EmployeeService;
import com.manager.service.EmployeeTeamProjectService;
import com.manager.service.TeamProjectService;


@Controller
public class EmployeeTeamProjectController {

	@Autowired
	EmployeeTeamProjectService employeeTeamProjectService;
	
	@Autowired
	EmployeeService employeeSerivce;
	
	@Autowired 
	TeamProjectService teamProjectService;

	@RequestMapping("/employeeTeamProject")
	public String index(Model model) {
		model.addAttribute("listETP", employeeTeamProjectService.findAllEmployeeTeamProject());
		return "/EmployeeTeamProject/employeeteamproject";
	}
	
	@GetMapping("/showNewEmployeeTeamProjectForm")
	public String showNewEmployeeTeamProject(Model model) {
		EmployeeTeamProject employeeTeamProject = new EmployeeTeamProject();
		model.addAttribute("employeeTeamProject", employeeTeamProject);
		
		List<Employee> listEmp = employeeSerivce.findAllEmployee();
		System.out.println("listEmp "+listEmp.size());
		if(listEmp.size()>0) {
			model.addAttribute("listEmp", listEmp);
			
		}
		
		List<TeamProject> listProject = teamProjectService.findAllTeamProject();
		System.out.println("listProject "+listProject.size());
		if(listProject.size()>0) {
			model.addAttribute("listProject", listProject);	
		}
		return "/EmployeeTeamProject/new_employeeTeamProject";
	}
	@PostMapping("/saveEmployeeTeamProject")
	public String saveEmployeeTeamProject(@ModelAttribute("employeeTeamProject")EmployeeTeamProject employeeTeamProject) {
		employeeTeamProjectService.saveEmployeeTeamProject(employeeTeamProject);
		return "redirect:/employeeTeamProject";
	}
	
	@GetMapping("/showEmployeeTeamProjectUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id")long id, Model model) {
		
		EmployeeTeamProject employeeTeamProject = employeeTeamProjectService.getEmployeeTeamProjectById(id);
		
		model.addAttribute("employeeTeamProject", employeeTeamProject);
		return "/EmployeeTeamProject/update_employeeteamproject";
	}
	@GetMapping("/deleteEmployeeTeamProject/{id}")
	public String deleteEmployeeTeamProject(@PathVariable(value="id")long id) {
		this.employeeTeamProjectService.deleteEmployeeTeamProjectById(id);
		return "redirect:/employeeTeamProject";
	}
	
	
}
