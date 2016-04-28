package com.pre.team.uoffice.filter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.domain.User;

public class ExtendStrutsFilter extends StrutsPrepareAndExecuteFilter {
    
	private String encoding="utf-8";
	
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    	request.setCharacterEncoding(encoding);//过滤请求的编码
    	
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;  
        HttpServletResponse httpServletResponse = (HttpServletResponse) response; 
        HttpSession session = httpServletRequest.getSession();
   
        User user = (User)session.getAttribute(Constants.CURRENT_USER);
        String url = httpServletRequest.getRequestURI();  
        //如果用户没有登录，或者用户登录了但是不为管理员则访问发布界面时跳转到首页
        if((user == null || "01".equals(user.getUserType())) && 
        		(url.endsWith("release.jsp") || url.endsWith("ModifyView"))){
        	String pubUrl = httpServletRequest.getContextPath() + "/public";
            httpServletResponse.sendRedirect(pubUrl);  
            return;  	
        }else{
        	super.doFilter(request, response, chain);
        }
    }
}
