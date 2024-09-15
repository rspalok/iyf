package net.transection.login.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.transection.login.service.UserService;


@RestController
@RequestMapping("/api")
public class login {

	@Autowired
	UserService ser;
	
	@GetMapping("/test")
	public String getMethodName() {
 
		System.out.println("===== == =get == = =");
		return "Success";
	}
	
}
