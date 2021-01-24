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
import com.manager.service.DepartmentService;
import com.manager.service.EmployeeService;
import com.manager.service.EmployeeTeamProjectService;
import com.manager.service.TeamProjectService;


@Controller
public class TeamProjectController {

	@Autowired
	TeamProjectService teamProjectService;

	@Autowired
	EmployeeService employeeService;
	@Autowired
	DepartmentService departmentService;
	@Autowired
	EmployeeTeamProjectService employeeteamprojectService;
	
	@RequestMapping("/teamproject")
	public String index(Model model) {
		model.addAttribute("listPro", teamProjectService.findAllTeamProject());
		return "/TeamProject/teamproject";
	}
	
	@GetMapping("/showNewTeamProjectForm")
	public String showNewTeamProject(Model model) {
		TeamProject teamProject = new TeamProject();
		model.addAttribute("teamProject", teamProject);
		
		List<Employee> listEmp = employeeService.findAllEmployee();
		System.out.println("listEmp "+listEmp.size());
		if(listEmp.size()>0) {
			model.addAttribute("listEmp", listEmp);
			
		}
		
		List<Department> listDepart = departmentService.findAllDepartment();
		System.out.println("listDepart "+listDepart.size());
		if(listDepart.size()>0) {
			model.addAttribute("listDepart", listDepart);	
		}
		
		return "/TeamProject/new_project";
	}
	@PostMapping("/saveTeamProject")
	public String saveTeamProject(@ModelAttribute("teamProject")TeamProject teamProject) {
		teamProjectService.saveTeamProject(teamProject);
		EmployeeTeamProject etp = new EmployeeTeamProject();
		TeamProject project = teamProjectService.getTeamProjectById(teamProject.getId());
		Employee emp = employeeService.getEmployeeById(teamProject.getEmployee().getId());
		if(etp !=null && emp !=null) {
			etp.setEmployee(emp);
			etp.setTeamProject(project);
			employeeteamprojectService.saveEmployeeTeamProject(etp);
		}
		
		return "redirect:/teamproject";
	}
	
	@GetMapping("/showTeamProjectUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id")long id, Model model) {
		
		TeamProject teamProject = teamProjectService.getTeamProjectById(id);
		
		model.addAttribute("teamProject", teamProject);
		return "/TeamProject/update_project";
	}
	@GetMapping("/deleteTeamProject/{id}")
	public String deleteTeamProject(@PathVariable(value="id")long id) {
		this.teamProjectService.deleteTeamProjectById(id);
		return "redirect:/teamproject";
	}
	
	
}
