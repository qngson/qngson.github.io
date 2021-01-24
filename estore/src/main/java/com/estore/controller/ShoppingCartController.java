package com.estore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estore.service.CartService;

@Controller
public class ShoppingCartController {

	
	@Autowired
	CartService cartService;
	
	@ResponseBody
	@RequestMapping("/cart/add/{id}")
	public Object[] add(@PathVariable("id") Integer id) {
		
		cartService.add(id);
		System.out.println(cartService.getAmount());
		Object[] info = {cartService.getCount(),cartService.getAmount()};
		return info;
	}
	
	@RequestMapping("/cart/view")
	public String view() {
		return "cart/view";
	}
	
	@ResponseBody
	@RequestMapping("/cart/clear")
	public void clear() {	
		cartService.clear();
	}
	
	@ResponseBody
	@RequestMapping("/cart/remove/{id}")
	public Object[] remove(@PathVariable("id") Integer id) {
		
		cartService.remove(id);
		System.out.println(cartService.getAmount());
		Object[] info = {cartService.getCount(),cartService.getAmount()};
		return info;
	}
	
	@ResponseBody
	@RequestMapping("/cart/update/{id}/{qty}")
	public Object[] update(@PathVariable("id") Integer id,@PathVariable("qty") Integer qty) {
		
		cartService.update(id, qty);
		System.out.println(cartService.getAmount());
		Object[] info = {cartService.getCount(),cartService.getAmount()};
		return info;
	}
	
	
}
