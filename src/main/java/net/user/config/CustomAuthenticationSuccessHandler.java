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
		System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n userName: "+userName);
 
		
		System.out.println("userName=" + userName);

		theUser = userService.findByUserName(userName);

		System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n theUser: "+theUser);
		System.out.println("\n\nIn customAuthenticationSuccessHandler\n\n request: "+request);
		// now place in the session
		HttpSession session = request.getSession();
		session.setAttribute("user", theUser);
		
		// forward to home page
		
		response.sendRedirect(request.getContextPath() + "/");
	}

}
