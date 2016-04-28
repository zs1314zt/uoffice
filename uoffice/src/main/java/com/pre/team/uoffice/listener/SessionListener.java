/**
 * 
 */
package com.pre.team.uoffice.listener;

import java.util.Date;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.domain.OfficeLocation;
import com.pre.team.uoffice.domain.Visit;
import com.pre.team.uoffice.service.VisitService;
import com.pre.team.uoffice.service.impl.UserManager;
import com.pre.team.uoffice.service.impl.VisitServiceImpl;
import com.pre.team.uoffice.util.SessionUtil;

/**
 * Description:SessionListener监听
 * @author xuejiahao  2015年7月11日
 * 
 */
public class SessionListener implements HttpSessionListener,ServletRequestListener {
	
	private VisitService visitService = (VisitService) new ClassPathXmlApplicationContext("spring/ApplicationContext.xml").getBean("visitService");;
	
	private HttpServletRequest request;
	
	private Logger LOG = Logger.getLogger(SessionListener.class);
	
	public void sessionCreated(HttpSessionEvent arg0) {
		HttpSession ses = arg0.getSession();
		//单位为秒,30分钟无操做，那么session失效
		ses.setMaxInactiveInterval(30*60);
		ses.setAttribute(Constants.CURRENT_CITY, new OfficeLocation(Constants.HANGZHOU_CODE, "杭州"));
		ses.setAttribute(Constants.PUBLISH_MESSAGE_SIZE, 0);
		visitService.saveVisit(new Visit(SessionUtil.getClientIp(request), new Date()));
	}	
	
	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession ses = arg0.getSession();
		//移除失效的session会话
		UserManager.getSingleInstance().logout(ses.getId());
	}

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		 request = (HttpServletRequest)arg0.getServletRequest();
	}
}
