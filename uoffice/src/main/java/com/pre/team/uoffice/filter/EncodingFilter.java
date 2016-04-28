
package com.pre.team.uoffice.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/**
 *  编码过滤器实体类
 * @author Administrator
 *
 */
public class EncodingFilter implements Filter {
	private FilterConfig config;
	private String encoding="utf-8";
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);//过滤请求的编码
		chain.doFilter(request, response);//继续过滤
		response.setContentType("text/html;charset=utf-8");//过滤响应的编码

	}

	public void init(FilterConfig config) throws ServletException {
		this.config=config;
		String s=config.getInitParameter("encoding");//获取初始化参数数，
		if(s!=null){
			this.encoding=s;
		}

	}

}

