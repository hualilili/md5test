package com.primeton.pageEncode;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Filter implements javax.servlet.Filter {
	private String charset = null;

	public void destroy() {
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		arg0.setCharacterEncoding(this.charset);
		arg1.setCharacterEncoding(this.charset);
		arg2.doFilter(arg0, arg1);
	}

	public void init(FilterConfig config) throws ServletException {
		this.charset = config.getInitParameter("charset");
	}
}