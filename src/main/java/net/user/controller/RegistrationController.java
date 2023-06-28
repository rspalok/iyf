package net.user.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.com.service.utilityService;
import net.model.GbltOtpStudentRegTrn;
import net.model.GbltRolMst;
import net.model.GbltUserMst;
import net.model.bean.CrmUser;
import net.model.master.pojo.GbltOrgMst;
import net.user.service.UserService; 

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
    @Autowired
    private UserService userService;

    @Autowired
    private utilityService uitilService;
    
    private Logger logger = Logger.getLogger(getClass().getName());
    
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showRegistrationForm")
	public String showRegistrationForm(Model theModel) {
		
		theModel.addAttribute("crmUser", new CrmUser());
		
		List<GbltOrgMst> orgs=uitilService.getAllOrgDetails(); 
		theModel.addAttribute("orgs", orgs);
		
		List<GbltRolMst> roles=uitilService.getAllRoleDetails(); 
		theModel.addAttribute("roles", roles);
		
		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("crmUser") CrmUser theCrmUser, 
				BindingResult theBindingResult, 
				Model theModel) {
		
		String userName = theCrmUser.getUserName();
		logger.info("Processing registration form for: " + theCrmUser);
		
		// form validation
		if (theBindingResult.hasErrors()) {
			System.out.println("====theBindingResult.hasErrors()===="+theBindingResult.hasErrors());
        	List<GbltOrgMst> orgs=uitilService.getAllOrgDetails(); 
    		theModel.addAttribute("orgs", orgs);
    		
    		List<GbltRolMst> roles=uitilService.getAllRoleDetails(); 
    		theModel.addAttribute("roles", roles);
			return "registration-form";
		}
		String registrationId = theCrmUser.getRegistrationId();
		// check the database if user already exists
		GbltOtpStudentRegTrn user = userService.findByregistrationId(registrationId);
		System.out.println("==========user===="+user);
		GbltUserMst existing = userService.findByUserName(userName);
        if(user == null) {
        	theModel.addAttribute("crmUser", new CrmUser());
        	List<GbltOrgMst> orgs=uitilService.getAllOrgDetails(); 
    		theModel.addAttribute("orgs", orgs);
    		
    		List<GbltRolMst> roles=uitilService.getAllRoleDetails(); 
    		theModel.addAttribute("roles", roles);
			theModel.addAttribute("registrationError", "User with registration Id: "+registrationId+", is not exists.");

			logger.warning("User name already exists.");
        	return "registration-form";
        }
        if (existing != null){
        	theModel.addAttribute("crmUser", new CrmUser());
        	List<GbltOrgMst> orgs=uitilService.getAllOrgDetails(); 
    		theModel.addAttribute("orgs", orgs);
    		
    		List<GbltRolMst> roles=uitilService.getAllRoleDetails(); 
    		theModel.addAttribute("roles", roles);
			theModel.addAttribute("registrationError", "User name already exists.");

			logger.warning("User name already exists.");
        	return "registration-form";
        }
        
        System.out.println(user+"=== == \n=== === "+theCrmUser);
        // create user account        						
        userService.save(theCrmUser,user);
        
        logger.info("Successfully created user: " + userName);
        
        return "registration-confirmation";		
	}
}
