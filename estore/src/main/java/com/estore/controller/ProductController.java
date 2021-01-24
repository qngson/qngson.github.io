package com.estore.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estore.bean.MailInfo;
import com.estore.dao.ProductDao;
import com.estore.entity.Product;
import com.estore.service.CookieService;
import com.estore.service.MailService;

@Controller
public class ProductController {

	@Autowired
	ProductDao productDao;
	
	@Autowired
	CookieService cookieService;
	
	@Autowired
	MailService mailService;
	
	
	@RequestMapping("/product/list-by-category/{cid}")
	public String listByCategory(Model model,@PathVariable("cid")Integer categoryId) {
		List<Product> list = productDao.findByCategoryId(categoryId);
		model.addAttribute("list", list);
		return"product/list";
	}
	
	@RequestMapping("/product/list-by-keywords")
	public String listByKeyWords(Model model,@RequestParam("keywords")String keywords) {
		List<Product> list = productDao.findByKeyWord(keywords);
		model.addAttribute("list", list);
		return"product/list";
	}
	
	@RequestMapping("/product/detail/{id}")
	public String detail(Model model,@PathVariable("id")Integer id) {
		Product prod = productDao.findById(id);
		model.addAttribute("prod", prod);
			
		//Tăng số lần xem sp 
		prod.setViewCount(prod.getViewCount()+1);
		productDao.update(prod);
		
		// Hàng Cùng loại
		List<Product> listTypeCate = productDao.findByCategoryId(prod.getCategory().getId());
		model.addAttribute("listTypeCate", listTypeCate);
		
		Cookie favo = cookieService.read("favo");
		if(favo != null) {
			String ids = favo.getValue();
			List<Product> favo_list = productDao.findByIds(ids);
			model.addAttribute("favo", favo_list);
		}
		
		// Hàng đã xem
		String value = id.toString();
		Cookie viewed =cookieService.read("viewed");
		if(viewed!=null) {
			 value = viewed.getValue();
			 value += ","+id.toString();
		}
		cookieService.create("viewed", value, 10);
		List<Product> viewed_list = productDao.findByIds(value);
		model.addAttribute("viewed",  viewed_list);
		return"product/detail";
	}
	
	@ResponseBody
	@RequestMapping("/product/add-to-favo/{id}")
	public Boolean addToFavorite(Model model,@PathVariable("id")Integer id) {
		Cookie favo =cookieService.read("favo");
		String value = id.toString();
		if(favo!=null) {
			 value = favo.getValue();
			if(!value.contains(id.toString())) {
				value +=","+id.toString();
			}
			else {
				
				return false;
			}
		}
		cookieService.create("favo", id.toString(), 30);
		return true;
	}
	
	@RequestMapping("/product/list-by-speacial/{id}")
	public String listBySpeacial(Model model,@PathVariable("id")Integer id) {
		List<Product> list = productDao.findBySpeacial(id);
		model.addAttribute("list", list);
		return"product/list";
	}
	
	
	@ResponseBody
	@RequestMapping("/product/send-to-friend")
	public Boolean sendToFriend(Model model,
//			@RequestParam("from") String from,
//			@RequestParam("id") String id,
//			@RequestParam("email") String email,
//			@RequestParam("comments") String comments
			MailInfo info, HttpServletRequest request
			) {
		info.setSubject("Thông Tin Hàng Hóa");
		try {
			String id = request.getParameter("id");
			String link = request.getRequestURL().toString().replace("send-to-friend", "detail/"+id);
			System.out.println(link);
			System.out.println(info.getBody()+"<hr><a href='"+link+"'> Xem chi Tiết..</a>");
			info.setBody(info.getBody()+"<hr><a href='"+link+"'>Xem chi Tiết..</a>");
			
			mailService.send(info);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
