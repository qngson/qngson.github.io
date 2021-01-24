package com.estore.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.estore.dao.ReportDao;
import com.estore.entity.Category;

@Controller
public class InventoryReport {

	@Autowired
	ReportDao reportDao;
	
	@RequestMapping("/admin/inventory/index")
	public String index(Model model) {
		
		model.addAttribute("data", reportDao.invetory());	
		return "admin/report/inventory";
	}
			
	@RequestMapping("/admin/revenue/category")
	public String revenueByCategory(Model model) {
		
		model.addAttribute("data", reportDao.revenueByCategory());	
		return "admin/report/revenue-by-category";
	}
	
	@RequestMapping("/admin/revenue/customer")
	public String revenueByCustomer(Model model) {
		
		model.addAttribute("data", reportDao.revenueByCustomer());	
		return "admin/report/revenue-by-customer";
	}
	
	@RequestMapping("/admin/revenue/year")
	public String revenueYear(Model model) {
		
		model.addAttribute("data", reportDao.revenueByYear());	
		return "admin/report/revenue-by-year";
	}
	
	@RequestMapping("/admin/revenue/quarter")
	public String revenueByQuarter(Model model) {
		
		model.addAttribute("data", reportDao.revenueByQuarter());	
		return "admin/report/revenue-by-quarter";
	}
	
	@RequestMapping("/admin/revenue/month")
	public String revenueByMonth(Model model) {
		
		model.addAttribute("data", reportDao.revenueByMonth());	
		return "admin/report/revenue-by-month";
	}
}
