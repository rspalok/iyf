package net.user.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import net.model.bean.GbltUserBean;
import net.model.master.GbltOrgMst;
import net.model.master.GbltUserMst;
import net.user.service.UserService;
 

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;
    
   
   // GbltUserBean bean;
	GbltUserMst gbltUserMst;
	GbltOrgMst theOrgDetails;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		
		String userName = authentication.getName();
		//createSessionBeam(request,userName);
		gbltUserMst = userService.findByUserNames(userName);
		theOrgDetails = userService.allOrgDetails(gbltUserMst);
		// now place in the session
		GbltUserBean bean=new GbltUserBean();
		bean.setIUserId(gbltUserMst.getIUserId());
		bean.setStUserName(gbltUserMst.getStUserName());
		bean.setStFirstName(gbltUserMst.getStFirstName());
		bean.setStLastName(gbltUserMst.getStLastName());
		bean.setStOrgId(gbltUserMst.getStOrgId());
		bean.setStOrgNameId(theOrgDetails.getStrName());
		
		HttpSession session = request.getSession();
		session.setAttribute("user", bean);

		System.out.println(" customAuthenticationSuccessHandler theUser: 123 "+session.getAttribute("user"));
		response.sendRedirect(request.getContextPath() + "/home");
	}
	//private void createSessionBeam(HttpServletRequest request, String userName) {
		
		
		

		// now place in the session 
		//System.out.println(" customAuthenticationSuccessHandler theUser: 123 "+session.getAttribute("user")); 
	//}
	

}
