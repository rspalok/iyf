package net.user.config;

import java.io.IOException;

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
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain fChain) throws IOException, ServletException {
		
		HttpServletRequest hReq = (HttpServletRequest) req; 
		
		fChain.doFilter(hReq, res);
	}

}
