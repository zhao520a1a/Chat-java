package com.xin.filter;

import org.junit.Test;

import javax.servlet.Filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;



/**
 * Servlet Filter implementation class EncodingFilter
 */
@WebFilter(
		urlPatterns={"/*"},
		initParams={
				@WebInitParam(name="encoder",value="utf-8")
		}
)
public class EncodingFilter implements Filter {
	private String encoder;
     
	 
	public void destroy() {
	}
	 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(this.encoder);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.encoder=fConfig.getInitParameter("encoder");		
		
	}
	
	@Test
	public void test() {
		System.out.print(new EncodingFilter().encoder);
	}
	
}
