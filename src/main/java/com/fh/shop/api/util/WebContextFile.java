package com.fh.shop.api.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Servlet Filter implementation class WebContextFile
 */
public class WebContextFile implements Filter {

    /**
     * Default constructor. 
     */
    public WebContextFile() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//开始绑定
		System.out.println("开始绑定");
		WebContext.setRequest((HttpServletRequest) request);
		try {
			System.out.println("执行dofilter");
			//执行dofilter
			chain.doFilter(request, response);
			//结束dofilter
			System.out.println("结束dofilter");
		} finally {
			//解除绑定
			System.out.println("解除绑定");
			WebContext.removeRequest();
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
