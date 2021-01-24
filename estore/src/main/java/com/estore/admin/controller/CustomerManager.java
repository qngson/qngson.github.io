package com.estore.admin.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estore.dao.CustomerDao;
import com.estore.entity.Customer;

@Controller
public class CustomerManager {

	@Autowired
	CustomerDao	customerDao;
	
	@Autowired
	ServletContext app;

	@RequestMapping("/admin/customer/index")
	public String index(Model model) {
		Customer entity = new Customer();
		model.addAttribute("entity", entity);
		model.addAttribute("list", customerDao.findAll());
		return "admin/customer/index";
	}
	
	@RequestMapping("/admin/customer/edit/{id}")
	public String edit(Model model,@PathVariable("id") String id) {
		Customer entity = customerDao.findById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("list", customerDao.findAll());
		return "admin/customer/index";
	}
	
	@RequestMapping("/admin/customer/create")
	public String create(RedirectAttributes model, 
			@ModelAttribute("entity") Customer entity,
			@RequestParam("photo_file") MultipartFile file
			) throws IllegalStateException, IOException {
		if(file.isEmpty()) {
			entity.setPhoto("images.png");
		}else {
			entity.setPhoto(file.getOriginalFilename());
			String path = app.getRealPath("/static/images/customer/"+entity.getPhoto());
			file.transferTo(new File(path));
		}
		customerDao.Create(entity);
		model.addAttribute("message", "Create success!");
		return "redirect:/admin/customer/index";
	}
	
	@RequestMapping("/admin/customer/update")
	public String update(RedirectAttributes model,Model md,
			@ModelAttribute("entity") Customer entity,
			@RequestParam("photo_file") MultipartFile file) throws IllegalStateException, IOException {
		
		if(!file.isEmpty()) {
			entity.setPhoto(file.getOriginalFilename());
			String path = app.getRealPath("/static/images/customer/"+entity.getPhoto());
			file.transferTo(new File(path));
		}
		
		if(entity!=null) {
			
			customerDao.update(entity);
			model.addAttribute("message", "Update success!");
			
		}
		else {
			md.addAttribute("message", "Choise Customer want update!");	
		}
		return "redirect:/admin/customer/edit/"+entity.getId();
	}
	
	@RequestMapping(value= {"/admin/customer/delete","/admin/customer/delete/{id}"})
	public String delete(RedirectAttributes model,
			@RequestParam(value="id", required=false) String id1, 
			@PathVariable(value="id", required=false) String id2) {
		
		if(id1 != null) {
			customerDao.delete(id1);
		}else
		{
			customerDao.delete(id2);
		}
		//Integer id =(id1 != null) ? id1 : id2;
		//customerDao.delete(id);
		model.addAttribute("message", "Delete success!");
		return "redirect:/admin/customer/index";
	}
	
	int pageSize=2;
	@ResponseBody
	@RequestMapping("/pager/customer/page-count")
	public long pageCount() {
		
		return customerDao.getPageCount(pageSize);
		
	}
	
	@ResponseBody
	@RequestMapping("/pager/customer/page/{pageNo}")
	public List<Customer> getPage(@PathVariable("pageNo")int pageNo) {
		
		List<Customer> list = customerDao.getPage(pageNo,pageSize);
		return list;
		
	}
}
