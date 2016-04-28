/**
 * 
 */
package com.pre.team.uoffice.service.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:用户管理
 * @author xuejiahao  2015年7月11日
 * 
 */
public class UserManager {
	private static UserManager userManager = null;
	/**
	 * 存放用户登录sessionid与userid信息
	 */
	private static Map<String,Object> sessionUserMap = new HashMap<String, Object>();
	
	private UserManager(){
	};
	/**
	 * 
	 * Description:获取单例对象  2015年7月11日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	public synchronized static UserManager getSingleInstance(){
		if(userManager==null){
			userManager = new UserManager();
		}
		return userManager;
	}
	/**
	 * 
	 * Description:添加用户到session中  2015年7月11日
	 * 
	 * @author xuejiahao
	 * @param sessionId
	 * @param userId
	 */
	public void addSessionID(String sessionId, int userId) {
		if(sessionUserMap == null){
			sessionUserMap = new HashMap<String, Object>();
		}
		sessionUserMap.put(sessionId, userId);
	}
	
	/**
	 * 
	 * Description:注销登录  2015年7月11日
	 * 
	 * @author xuejiahao
	 * @param sessionId
	 */
	public void logout(String sessionId){
		if(sessionUserMap.containsKey(sessionId)){
			sessionUserMap.remove(sessionId);
		}
	}

	/**
	 * 
	 * Description:获取当前在线用户数  2015年7月11日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	public int getCurrentUserCount(){
		return sessionUserMap == null ? 0 : sessionUserMap.size();
	}
}
