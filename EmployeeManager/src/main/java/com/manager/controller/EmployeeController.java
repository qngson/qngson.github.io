package com.manager.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.manager.entity.Department;
import com.manager.entity.Employee;
import com.manager.service.DepartmentService;
import com.manager.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	DepartmentService departmentService;
	
	@RequestMapping("/employee")
	public String index(Model model) {
		model.addAttribute("listEmp", employeeService.findAllEmployee());
		
		return "Employee/employee";
	}

	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		List<Department> listDepart = departmentService.findAllDepartment();
		System.out.println(listDepart.size());
		if(listDepart.size()>0) {
			model.addAttribute("listDepart", listDepart);
			
		}
		return "Employee/new_employee";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee( @ModelAttribute("employee") @Valid Employee employee,BindingResult result, Errors errors) {
		
		
		System.out.println("Erorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
		System.out.println(errors);
		System.out.println(result);
		if(result.hasErrors()) {
			return "Employee/new_employee";
		}
		employeeService.saveEmployee(employee);
		return "redirect:/employee";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		List<Department> listDepart = departmentService.findAllDepartment();
		System.out.println(listDepart.size());
		if(listDepart.size()>0) {
			model.addAttribute("listDepart", listDepart);
			
		}
		return "Employee/update_employee";
	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id) {
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/employee";
	}

}
