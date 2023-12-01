package net.transection.login.controller;

import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.com.utilityService;
import net.model.bean.CrmUser;
import net.model.bean.GbltUserBean;
import net.model.master.pojo.org.GbltOrgMst;
import net.model.master.pojo.role.GbltRolMst;
import net.model.master.pojo.role.GbltUserMst;
import net.model.transection.pojo.registration.GbltOtpStudentRegTrn;
import net.transection.login.service.UserService; 

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
	//<script type="text/javascript">
	@GetMapping("/studentByMobileNo")
	@ResponseBody
	public String studentByMobileNo(HttpServletRequest objRequest_p,
			@RequestParam(value = "mobileNumber", required = true) Long mobileNumber) {

		return uitilService.studentByMobileNo(mobileNumber, objRequest_p);
	}
	
	@GetMapping("/resetPassword")
	public String ResetPassword(CrmUser theCrmUser,Model theMode,HttpServletRequest request) {
		
		HttpSession session = request.getSession(); 
		GbltUserBean theUser =(GbltUserBean) session.getAttribute("user");
		String Org=theUser.getStOrgId();
		//GbltOtpStudentRegTrn userDetails = userService.findByregistrationId(theUser.getIUserId());

		theMode.addAttribute("FirstName", theUser.getStFirstName());
		theMode.addAttribute("LastName", theUser.getStLastName());
		theMode.addAttribute("UserId", theUser.getIUserId());
		
		Principal principal = request.getUserPrincipal();
        theCrmUser.setUserName(principal.getName());
		theCrmUser.setRegistrationId(theUser.getIUserId());
		theCrmUser.setOrgName(Org);
		System.out.println("===== == =get == = ="+theCrmUser);
		
		return "/passwordResetForm";
	}

	@PostMapping("/resetPassword")
	public String processResetPasswordForm(CrmUser theCrmUser, Model theModel,HttpServletRequest request) {
		
		if(theCrmUser.getPassword()!= null && (theCrmUser.getPassword() == theCrmUser.getMatchingPassword() || 
				theCrmUser.getPassword().equals(theCrmUser.getMatchingPassword()))) {
			

			Principal principal = request.getUserPrincipal();
	        theCrmUser.setUserName(principal.getName());
	        
			userService.resetPassword(theCrmUser,request);

			System.out.println("===== == Post= == = ="+theCrmUser);
			return "registration-confirmation";	
		}
		
        return 	ResetPassword( theCrmUser, theModel, request) ;
	
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@Valid @ModelAttribute("crmUser") CrmUser theCrmUser, 
				BindingResult theBindingResult, 
				Model theModel,HttpServletRequest objRequest_p) {
		
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
		GbltOtpStudentRegTrn user = userService.findByregistrationId(registrationId,objRequest_p);
		System.out.println("==========user===="+user);
		GbltUserMst existing = userService.findByUserUserId(userName);
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
