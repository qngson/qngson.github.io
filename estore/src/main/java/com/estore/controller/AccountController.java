package com.estore.controller;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.estore.bean.MailInfo;
import com.estore.dao.CustomerDao;
import com.estore.entity.Customer;
import com.estore.service.CookieService;
import com.estore.service.MailService;

@Controller
public class AccountController {

	@Autowired
	CustomerDao	customerDao;
	@Autowired
	HttpSession session;
	@Autowired
	CookieService cookie;
	
	@Autowired
	ServletContext app;
	
	@Autowired
	MailService mailService;
	
	@Autowired
	HttpServletRequest request;
	
	
	@GetMapping("/account/login")
	public String login(Model model) {
		Cookie ckid = cookie.read("userid");
		Cookie ckpw = cookie.read("pass");
		
		if(ckid!=null){
			
			String uid=ckid.getValue();
			String pwd=ckpw.getValue();
			model.addAttribute("uid",uid);
			model.addAttribute("pwd", pwd);
		}
		
		return "account/login";
	}
	
	@PostMapping("/account/login")
	public String login(Model model,

			@RequestParam("id") String id,
			@RequestParam("pw") String pw,
			@RequestParam(value="rm", defaultValue = "false") boolean  rm
			) {
		
		Customer user = customerDao.findById(id);
		if(user==null) {
			model.addAttribute("message", "invalid username");
		}
		else if(!pw.equals(user.getPassword())) {
			model.addAttribute("message", "invalid password");
		}else if(!user.getActivated()) {
			model.addAttribute("message", "Your account is inactivated");
		}else {// thành công
			model.addAttribute("message", "Login success!");
			session.setAttribute("user", user);
			if(user.getAdmin()==true) {
				return "admin/home/index";
			}
			// ghi nhớ tk
			if(rm == true) {
				cookie.create("userid", user.getId(), 30);
				cookie.create("pass", user.getPassword(), 30);
			}
			else {
				cookie.delete("userid");
				cookie.delete("pass");
			}
			// quay lại trang duoc bao ve
			String backUrl = (String) session.getAttribute("back-url");
			if(backUrl!=null) {
				return "redirect:"+backUrl;
			}
		}
		
		return"account/login";
	}

	@RequestMapping("/account/logoff")
	public String logoff(Model model) {
		session.removeAttribute("user");
		return "redirect:/home/index";
	}

	@GetMapping("/account/register")
	public String register(Model model) {
		Customer user = new Customer();
		model.addAttribute("form", user);
		return "account/register";
	}
	
	@PostMapping("/account/register")
	public String register(Model model,
			@Validated @ModelAttribute("form") Customer user,BindingResult errors ,@RequestParam("photo_file")MultipartFile file) 
					throws IllegalStateException, IOException, MessagingException {
		System.out.println(errors);
		if(errors.hasErrors()) {
			
				model.addAttribute("message", "please fix error");
				return "account/register";
		}else {
			Customer user2 = customerDao.findById(user.getId());
			if(user2 != null) {
				model.addAttribute("message", "User is in used");
				System.out.println(errors);
				return "account/register";
			}
		}
		if(file.isEmpty()) {
			user.setPhoto("user.png");
		}
		else {
			String dir = app.getRealPath("/static/images/customer");
			File f = new File(dir,file.getOriginalFilename());
			file.transferTo(f);
			
			user.setPhoto(f.getName());
		}
		user.setActivated(false);
		user.setAdmin(false);
		
		customerDao.Create(user);
		model.addAttribute("message", "Register successfully");
		
		String from= "sockjing012591@gmail.com";
		String to = user.getEmail();
		String subject = "Welcome";
		String url = request.getRequestURL().toString().replace("register", "activate/"+user.getId());
		String body ="Click <a href='"+url+"'>Activate</a>";
		MailInfo mail = new MailInfo(from,to,subject,body);
		mailService.send(mail);
		return "account/register";
	}
	

	@GetMapping("/account/activate/{id}")
	public String activate(Model model,@PathVariable("id")String id ) {
		Customer user = customerDao.findById(id);
		user.setActivated(true);
		customerDao.update(user);
		return "redirect:/account/login";
	}
	
	@GetMapping("/account/forgot")
	public String forgot(Model model) {
		
		return "account/forgot";
	}
	
	@PostMapping("/account/forgot")
	public String forgot(Model model,
			@RequestParam("id") String id,
			@RequestParam("email")String email) throws MessagingException {
		Customer user =customerDao.findById(id);
		if(user== null) {
			
			model.addAttribute("message", "Invalid User");
		}
		else if(!email.equals(user.getEmail())) {
			model.addAttribute("message", "Invalid email");
		}
		else {
			String from= "sockjing012591@gmail.com";
			String to = user.getEmail();
			String subject = "Forgot password";
			//String url = request.getRequestURL().toString().replace("register", "activate/"+user.getId());
			String body ="Your password is:"+user.getPassword();
			MailInfo mail = new MailInfo(from,to,subject,body);
			mailService.send(mail);
			model.addAttribute("message", "Your password was sent to your inbox");
		
		}
		
		return "redirect:/account/login";
	}
	
	@GetMapping("/account/change")
	public String change(Model model) {
		
		return "account/change";
	}
	
	@PostMapping("/account/change")
	public String change(Model model,
			@RequestParam("id")String id,
			@RequestParam("pw")String pw,
			@RequestParam("pw1")String pw1,
			@RequestParam("pw2")String pw2) {
		if(!pw1.equals(pw2)) {
			model.addAttribute("message", "Confirm password is not match!");
		}else		
		{
			Customer user =customerDao.findById(id);
			if(user== null) {
				
				model.addAttribute("message", "Invalid User");
			}else if(!pw.equals(user.getPassword())) {
				model.addAttribute("message", "Invalid password");
			}else
			{
				user.setPassword(pw1);
				customerDao.update(user);
				model.addAttribute("message", "Change password success");
			}
		}
		
		return "redirect:/account/login";
	}
	
	@GetMapping("/account/edit")
	public String edit(Model model) {
		Customer user = (Customer) session.getAttribute("user");
		model.addAttribute("form", user);
		return "account/edit";
	}
	
	@PostMapping("/account/edit")
	public String edit(Model model,@ModelAttribute("form") Customer user,@RequestParam("photo_file")MultipartFile file) throws IllegalStateException, IOException {
		
		if(!file.isEmpty()) {
		
			String dir = app.getRealPath("/static/images/customer");
			File f = new File(dir,file.getOriginalFilename());
			file.transferTo(f);
			user.setPhoto(f.getName());
		}
		
		
		customerDao.update(user);
		session.setAttribute("user", user);
		model.addAttribute("message", "Update successfully");
		return "account/edit";
	}
	
	
	
	
}
