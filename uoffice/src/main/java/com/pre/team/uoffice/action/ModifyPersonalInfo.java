/**
 * 
 */
package com.pre.team.uoffice.action;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.domain.User;
import com.pre.team.uoffice.service.UserService;
import com.pre.team.uoffice.service.impl.UserManager;
import com.pre.team.uoffice.util.SessionUtil;

/**
 * Description:个人信息维护操作
 * @author xuejiahao  2015年8月3日
 * 
 */
@ParentPackage("authority") 
public class ModifyPersonalInfo extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserService userService;
	
	private User user;
	
	private Map<String,Object> map;
	/**
	 * 
	 * Description:修改个人信息  2015年8月3日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	@Action(value = "modifyPersonalInfo", results = { @Result(name = "success", type="json",params={"root","map"})})
	public String modifyPersonalInfo() throws Exception{
		if(user!=null){
			map = userService.modifyPersonalInfo(user);
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * Description:跳转到个人信息界面  2015年8月3日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	@Action(value = "showPersonalInfo", results = { @Result(name = "success",location=Constants.PERSONAL_INFO)})
	public String showPersonalInfo() throws Exception{
		user = SessionUtil.getCurrentUser();
		return SUCCESS;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the map
	 */
	public Map<String, Object> getMap() {
		return map;
	}
	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	
}
