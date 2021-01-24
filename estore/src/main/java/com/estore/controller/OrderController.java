package com.estore.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.estore.dao.OrderDao;
import com.estore.dao.OrderDetailDao;
import com.estore.entity.Customer;
import com.estore.entity.Order;
import com.estore.entity.OrderDetail;
import com.estore.entity.Product;
import com.estore.service.CartService;

@Controller
public class OrderController {

	@Autowired
	HttpSession session;
	@Autowired
	CartService cartService;
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	OrderDetailDao orderDetailDao;

	@GetMapping("/order/checkout")
	public String showForm(@ModelAttribute("order") Order order) {
		Customer user = (Customer) session.getAttribute("user");
		order.setOrderDate(new Date());
		order.setCustomer(user);
		order.setAmount(cartService.getAmount());
		return "order/checkout";
	}
	
	@PostMapping("/order/checkout")
	public String Purchase(Model model,@ModelAttribute("order") Order order) {
		
		Collection<Product> list = cartService.getItems();
		List<OrderDetail> details = new ArrayList<>();
		for(Product product: list) {
			OrderDetail detail = new OrderDetail();
			detail.setOrder(order);
			detail.setProduct(product);
			detail.setUnitPrice(product.getUnitPrice());
			detail.setQuantity(product.getQuantity());
			detail.setDiscount(product.getDiscount());
			details.add(detail);
		}
		 orderDao.create(order, details);
		 cartService.clear();
		return "redirect:/order/list";
	}
	
	@GetMapping("/order/list")
	public String list(Model model) {
		Customer user = (Customer) session.getAttribute("user");
		List<Order> orders = orderDao.findByUser(user);
		model.addAttribute("orders", orders);
		return "order/list";
	}
	
	@GetMapping("/order/detail/{id}")
	public String detail(Model model,
		@PathVariable("id") Integer id) {
		Customer user = (Customer) session.getAttribute("user");
		Order order = orderDao.findById(id);
		List<OrderDetail> details = orderDetailDao.findByOrder(order);
		model.addAttribute("order", order);
		model.addAttribute("details", details);
		return "order/detail";
	}
	

	@GetMapping("/order/items")
	public String items(Model model) {
		Customer user = (Customer) session.getAttribute("user");
		List<Product> list = orderDao.findItemsByUser(user);
		model.addAttribute("list", list);
		return "product/list";
	}
}
