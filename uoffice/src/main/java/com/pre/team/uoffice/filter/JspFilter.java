package com.pre.team.uoffice.filter;
import java.io.IOException;  

import javax.servlet.Filter;  
import javax.servlet.FilterChain;  
import javax.servlet.FilterConfig;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRequest;  
import javax.servlet.ServletResponse;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;

import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.domain.User;
  
/** 
 * 不允许直接访问jsp 
 * 所有对jsp的直接访问，跳转到首页面 
 * @author zs1314zt
 * 
 */  
public class JspFilter implements Filter {  
      
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {  
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;  
        HttpServletResponse httpServletResponse = (HttpServletResponse) response; 
        HttpSession session = httpServletRequest.getSession();
        User user = (User)session.getAttribute(Constants.CURRENT_USER);
        String url = httpServletRequest.getRequestURI();  
        
        //如果用户没有登录，或者用户登录了但是不为管理员则访问发布界面时跳转到首页
        if((user == null || "01".equals(user.getUserType())) && 
        		url != null && (url.endsWith("release.jsp") || url.endsWith("ModifyView"))){
        	String pubUrl = httpServletRequest.getContextPath() + "/public";
            httpServletResponse.sendRedirect(pubUrl);  
            return;  	
        }  
        chain.doFilter(request, response);  
    }  
  
    @Override  
    public void destroy() {  
          
    }  
  
    @Override  
    public void init(FilterConfig arg0) throws ServletException {  
          
    }  
  
}  