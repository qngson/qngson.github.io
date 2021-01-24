package com.manager.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.manager.entity.Department;
import com.manager.service.DepartmentService;


@Controller
public class DepartmentController {

	@Autowired
	DepartmentService departmentService;

	@RequestMapping("/department")
	public String index(Model model) {
		model.addAttribute("listDepart", departmentService.findAllDepartment());
		List<Department> list=departmentService.findAllDepartment();
		
//		for(Department d :list) {
//			if(d.getEmployee().getId()!=null) {
//			System.out.println(d.getEmployee().getId());
//			System.out.println(d.getEmployee().getName());
//			}
//			
//		}
		return "/Department/department";
	}
	
	@GetMapping("/showNewDepartmentForm")
	public String showNewDepartment(Model model) {
		Department department = new Department();
		model.addAttribute("department", department);
		return "/Department/new_department";
	}
	@PostMapping("/saveDepartment")
	public String saveDepartment(@Valid @ModelAttribute("department")Department department,BindingResult binding) {
		System.out.println("Erorrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr");
		System.out.println(binding);
		if(binding.hasErrors()) {
			return "/Department/new_department";
		}else {
			departmentService.saveDepartment(department);
		}
		return "redirect:/department";
	}
	
	@GetMapping("/showDepartmentUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id")long id, Model model) {
		
		Department department = departmentService.getDepartmentById(id);
		
		model.addAttribute("department", department);
		return "/Department/update_department";
	}
	@GetMapping("/deleteDepartment/{id}")
	public String deleteDepartment(@PathVariable(value="id")long id) {
		this.departmentService.deleteDepartmentById(id);
		return "redirect:/department";
	}
	
	
}
