/**
 * 
 */
package com.pre.team.uoffice.action;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.service.impl.UserManager;

/**
 * Description:登出Action
 * @author xuejiahao  2015年7月11日
 * 
 */
@ParentPackage("struts-default") 
public class LogoutAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * Description:退出登录  2015年7月15日
	 * 
	 * @author xuejiahao
	 * @return
	 * @throws Exception
	 */
	@Action(value = "logout", results = { @Result(name = SUCCESS,location=Constants.LOGIN_JSP)})
	public String logout() throws Exception{
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.removeAttribute(Constants.CURRENT_USER);
		UserManager.getSingleInstance().logout(session.getId());
		return SUCCESS;
	}
}
