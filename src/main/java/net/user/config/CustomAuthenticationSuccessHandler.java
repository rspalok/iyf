package net.user.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.google.common.net.HttpHeaders;

import net.model.GbltUserMst;
import net.user.service.UserService;
 

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserService userService;

	GbltUserMst theUser ;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String userName = authentication.getName();
		
		theUser = userService.findByUserNamess(userName);

		// now place in the session
		HttpSession session = request.getSession();
		session.setAttribute("user", theUser);
  
		// now place in the session 
		System.out.println(" customAuthenticationSuccessHandler theUser: 123 "+session.getAttribute("user")); 
		// forward to home page
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		System.out.println(authentication.getAuthorities()+"====url======"+request.getContextPath()
		+"-====="+authentication.getPrincipal());
		
		String result = request.getHeader("Referer");
		String result1 =request.getHeader(HttpHeaders.REFERER);
		System.out.println(result+"\n======\n"+result1);
		response.sendRedirect(request.getContextPath() + "/home");
	}
	

}
