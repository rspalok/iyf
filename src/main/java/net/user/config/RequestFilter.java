package net.user.config;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = { "/*" })
public class RequestFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fChain)
			throws IOException, ServletException {
		HttpServletRequest hReq = (HttpServletRequest) req;
		// p is not null anymore
		Principal p = hReq.getUserPrincipal();
		System.out.println("777777777777  " + p);
		fChain.doFilter(hReq, res);
	}

}
