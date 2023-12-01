package net.transection.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		
		
		return "login";
		//return "fancy-login";
		
	}
	 
	
	// add request mapping for /access-denied
	
	@GetMapping("/access-denied")
	public String showAccessDenied(HttpServletRequest request) {
		
		return "access-denied";
		
	}
	
}









