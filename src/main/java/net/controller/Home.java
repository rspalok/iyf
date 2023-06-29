package net.controller;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.model.master.MenuMaster;
import net.service.transection.GbltOtpStudentRegTrnService; 

@Controller
@RequestMapping("/home")
public class Home {
	@Autowired
	private GbltOtpStudentRegTrnService service;
	
	@GetMapping("/list") 
	public String menuList(MenuMaster menuMaster,Model model,HttpServletRequest request ) throws UnsupportedEncodingException { 
		//service.getMemuList(user);
		Map<String, List<MenuMaster>> data = service.getMemuList(request);
		model.addAttribute("transections", data.get("transaction"));
		model.addAttribute("setups", data.get("setup"));
		model.addAttribute("reports", data.get("report"));
		
		Principal principal = request.getUserPrincipal();
		System.out.println(principal.getName());
		System.out.println(principal.getName());
		System.out.println(principal.getName());
		System.out.println(principal.getName());
		return "transection/menu/menu_master";	
	}
	@GetMapping("/hello") 
	public String base(MenuMaster menuMaster,Model model,HttpServletRequest request) throws UnsupportedEncodingException { 
		System.out.println("====== = == == = = == = = == = = = == =");
		return "com/menu";
	}
}
