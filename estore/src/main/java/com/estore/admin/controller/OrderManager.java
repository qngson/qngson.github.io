package com.estore.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estore.dao.OrderDao;
import com.estore.dao.OrderDetailDao;
import com.estore.entity.Order;
import com.estore.entity.OrderDetail;

@Controller
public class OrderManager {

	@Autowired
	OrderDao	orderDao;
	@Autowired
	OrderDetailDao orderDetailDao;

	@RequestMapping("/admin/order/index")
	public String index(Model model) {
		Order entity = new Order();
		model.addAttribute("entity", entity);
		model.addAttribute("details", orderDetailDao.findByOrder(entity));
		model.addAttribute("list", orderDao.findAll());
		
		return "admin/order/index";
	}
	
	@RequestMapping("/admin/order/edit/{id}")
	public String edit(Model model,@PathVariable("id") Integer id) {
		Order entity = orderDao.findById(id);
		model.addAttribute("entity", entity);
		model.addAttribute("details", orderDetailDao.findByOrder(entity));
		model.addAttribute("list", orderDao.findAll());
		
		return "admin/order/index";
	}
	
	@RequestMapping("/admin/order/create")
	public String create(RedirectAttributes model, @ModelAttribute("entity") Order entity) {
		orderDao.Create(entity);
		model.addAttribute("message", "Create success!");
		return "redirect:/admin/order/index";
	}
	
	@RequestMapping("/admin/order/update")
	public String update(RedirectAttributes model,Model md,@ModelAttribute("entity") Order entity) {
		if(entity!=null) {
		orderDao.update(entity);
		model.addAttribute("message", "Update success!");
		
		}
		else {
			md.addAttribute("message", "Choise order want update!");	
		}
		return "redirect:/admin/order/edit/"+entity.getId();
	}
	
	@RequestMapping(value= {"/admin/order/delete","/admin/order/delete/{id}"})
	public String delete(RedirectAttributes model,
			@RequestParam(value="id", required=false) Integer id1, 
			@PathVariable(value="id", required=false) Integer id2) {
		
		if(id1 != null) {
			orderDao.delete(id1);
		}else
		{
			orderDao.delete(id2);
		}
		//Integer id =(id1 != null) ? id1 : id2;
		//orderDao.delete(id);
		model.addAttribute("message", "Delete success!");
		return "redirect:/admin/order/index";
	}
}
