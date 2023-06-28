package net.config;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.model.master.MenuMaster;
import net.service.GbltOtpStudentRegTrnService;

@Controller
@RequestMapping("/home")
public class Home {
	@Autowired
	private GbltOtpStudentRegTrnService service;
	
	@GetMapping("/list") 
	public String menuList(MenuMaster menuMaster,Model model,HttpServletRequest request) throws UnsupportedEncodingException { 
		//service.getMemuList(user);
		Map<String, List<MenuMaster>> data = service.getMemuList(request);
		model.addAttribute("transections", data.get("transaction"));
		model.addAttribute("setups", data.get("setup"));
		model.addAttribute("reports", data.get("report"));
		return "transection/menu/menu_master";	
	}
}
