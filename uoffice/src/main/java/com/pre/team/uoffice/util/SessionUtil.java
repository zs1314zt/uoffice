/**
 * 
 */
package com.pre.team.uoffice.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.domain.OfficeLocation;
import com.pre.team.uoffice.domain.User;
import com.pre.team.uoffice.service.impl.UserManager;

/**
 * Description:Session操做类
 * @author xuejiahao  2015年7月11日
 * 
 */
public class SessionUtil {
	/**
	 * 
	 * Description:获取登录用户的id  2015年7月11日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	public static int getLoginUserId(){
		Object obj = ServletActionContext.getRequest().getSession().getAttribute(Constants.CURRENT_USER);
		return obj==null?0:((User)obj).getUserId();
	}
	/**
	 * 
	 * Description:向session中存放User  2015年7月11日
	 * 
	 * @author xuejiahao
	 * @param user
	 */
	public static void addUserToSession(User user){
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute(Constants.CURRENT_USER, user);
		UserManager.getSingleInstance().addSessionID(session.getId(), user.getUserId());
	}
	/**
	 * 
	 * Description:获取当前用户对象  2015年8月3日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	public static User getCurrentUser(){
		Object obj = ServletActionContext.getRequest().getSession().getAttribute(Constants.CURRENT_USER);
		return obj==null?null:((User)obj);
	}
	/**
	 * 
	 * Description:获取当前城市对象  2015年8月16日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	public static OfficeLocation getCurrentLocation(){
		Object obj = ServletActionContext.getRequest().getSession().getAttribute(Constants.CURRENT_CITY);
		return obj==null?new OfficeLocation(Constants.HANGZHOU_CODE, "杭州"):((OfficeLocation)obj);
	}
	/**
	 * 
	 * Description:获取当前城市的locationCode  2015年8月16日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	public static String getCurrentLocationCode(){
		Object obj = ServletActionContext.getRequest().getSession().getAttribute(Constants.CURRENT_CITY);
		return obj==null?Constants.HANGZHOU_CODE:((OfficeLocation)obj).getLocationCode();
	}
	
	/**
	 * 
	 * Description:向session中存放officeLocation对象 2015年8月16日
	 * 
	 * @author xuejiahao
	 * @param user
	 */
	public static void addOfficeLocationToSession(OfficeLocation office){
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute(Constants.CURRENT_CITY,office);
	}
	/**
	 * 
	 * Description:获取当前用户的发表次数  2015年8月30日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	public static Integer getCurrentMessageSize(){
		Object obj = ServletActionContext.getRequest().getSession().getAttribute(Constants.PUBLISH_MESSAGE_SIZE);
		return obj==null?0:(Integer)obj;
	}
	/**
	 * 
	 * Description:增加当前用户发表次数  2015年8月30日
	 * 
	 * @author xuejiahao
	 */
	public static void increaseCurrentMessageSize(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute(Constants.PUBLISH_MESSAGE_SIZE,getCurrentMessageSize()+1);
	}
	/**
	 * 
	 * Description:重置当前用户发表次数  2015年8月30日
	 * 
	 * @author xuejiahao
	 */
	public static void resetCurrentMessageSize(){
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute(Constants.PUBLISH_MESSAGE_SIZE,0);
	}
	/**
	 * 
	 * Description:获取验证码  2015年8月30日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	public static String getCurrentMessageCode(){
		Object obj = ServletActionContext.getRequest().getSession().getAttribute(Constants.PUBLISH_MESSAGE_CODE);
		return obj==null?"":(String)obj;
	}
	/**
	 * 
	 * Description:向session中添加验证码  2015年8月30日
	 * 
	 * @author xuejiahao
	 * @param verifyCode
	 */
	public static void addCurrentMessageCodeToSession(String verifyCode){
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute(Constants.PUBLISH_MESSAGE_CODE,verifyCode);
	}
	/**
	 * 
	 * Description:获取用户IP地址  2015年9月14日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	public static String getClientIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if("0:0:0:0:0:0:0:1".equals(ip)){
			ip = "127.0.0.1";
		}
		return ip;
	}
}
