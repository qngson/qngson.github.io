package com.estore.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estore.dao.CategoryDao;
import com.estore.entity.Category;

@Controller
public class CategoryManager {

	@Autowired
	CategoryDao	categoryDao;

	@RequestMapping("/admin/category/index")
	public String index(Model model) {
		Category entity = new Category();
		model.addAttribute("entity", entity);
		model.addAttribute("list", categoryDao.findAll());
		return "admin/category/index";
	}
	
	@RequestMapping("/admin/category/edit/{id}")
	public String edit(Model model,@PathVariable("id") Integer id) {
		Category entity = categoryDao.findById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("list", categoryDao.findAll());
		return "admin/category/index";
	}
	
	@RequestMapping("/admin/category/create")
	public String create(RedirectAttributes model, @ModelAttribute("entity") Category entity) {
		categoryDao.Create(entity);
		model.addAttribute("message", "Create success!");
		return "redirect:/admin/category/index";
	}
	
	@RequestMapping("/admin/category/update")
	public String update(RedirectAttributes model,Model md,@ModelAttribute("entity") Category entity) {
		if(entity!=null) {
		categoryDao.update(entity);
		model.addAttribute("message", "Update success!");
		
		}
		else {
			md.addAttribute("message", "Choise category want update!");	
		}
		return "redirect:/admin/category/edit/"+entity.getId();
	}
	
	@RequestMapping(value= {"/admin/category/delete","/admin/category/delete/{id}"})
	public String delete(RedirectAttributes model,
			@RequestParam(value="id", required=false) Integer id1, 
			@PathVariable(value="id", required=false) Integer id2) {
		
		if(id1 != null) {
			categoryDao.delete(id1);
		}else
		{
			categoryDao.delete(id2);
		}
		//Integer id =(id1 != null) ? id1 : id2;
		//categoryDao.delete(id);
		model.addAttribute("message", "Delete success!");
		return "redirect:/admin/category/index";
	}
}
