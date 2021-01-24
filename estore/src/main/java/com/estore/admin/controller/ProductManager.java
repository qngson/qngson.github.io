package com.estore.admin.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estore.dao.ProductDao;
import com.estore.entity.Product;

@Controller
public class ProductManager {

	@Autowired
	ProductDao	productDao;
	
	@Autowired
	ServletContext app;

	@RequestMapping("/admin/product/index")
	public String index(Model model) {
		Product entity = new Product();
		model.addAttribute("entity", entity);
		model.addAttribute("list", productDao.findAll());
		return "admin/product/index";
	}
	
	@RequestMapping("/admin/product/edit/{id}")
	public String edit(Model model,@PathVariable("id") Integer id) {
		Product entity = productDao.findById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("list", productDao.findAll());
		return "admin/product/index";
	}
	
	@RequestMapping("/admin/product/create")
	public String create(RedirectAttributes model, 
			@ModelAttribute("entity") Product entity,
			@RequestParam("image_file") MultipartFile file
			) throws IllegalStateException, IOException {
		if(file.isEmpty()) {
			entity.setImage("images.png");
		}else {
			entity.setImage(file.getOriginalFilename());
			String path = app.getRealPath("/static/images/"+entity.getImage());
			file.transferTo(new File(path));
		}
		System.out.println(entity.getImage());
		productDao.Create(entity);
		model.addAttribute("message", "Create success!");
		return "redirect:/admin/product/index";
	}
	
	@RequestMapping("/admin/product/update")
	public String update(RedirectAttributes model,Model md,
			@ModelAttribute("entity") Product entity,
			@RequestParam("image_file") MultipartFile file) throws IllegalStateException, IOException {
		
		if(!file.isEmpty()) {
			entity.setImage(file.getOriginalFilename());
			String path = app.getRealPath("/static/images/"+entity.getImage());
			file.transferTo(new File(path));
		}
		
		if(entity!=null) {
			
			productDao.update(entity);
			model.addAttribute("message", "Update success!");
			
		}
		else {
			md.addAttribute("message", "Choise Product want update!");	
		}
		return "redirect:/admin/product/edit/"+entity.getId();
	}
	
	@RequestMapping(value= {"/admin/product/delete","/admin/product/delete/{id}"})
	public String delete(RedirectAttributes model,
			@RequestParam(value="id", required=false) Integer id1, 
			@PathVariable(value="id", required=false) Integer id2) {
		
		if(id1 != null) {
			productDao.delete(id1);
		}else
		{
			productDao.delete(id2);
		}
		//Integer id =(id1 != null) ? id1 : id2;
		//productDao.delete(id);
		model.addAttribute("message", "Delete success!");
		return "redirect:/admin/product/index";
	}
}
