/**
 * 
 */
package com.pre.team.uoffice.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.pre.team.uoffice.service.UserService;

/**
 * Description:
 * @author xuejiahao  2015年7月11日
 * 
 */
@ParentPackage("authority") 
public class ModifyUserPwdAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String, Object> map;
	private String oldPwd;
	private String newPwd;
	@Autowired
	private UserService userService;
	/**
	 * 
	 * Description:修改密码  2015年7月15日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	@Action(value = "modifyPwd", results = { @Result(name = "success", type="json",params={"root","map"})})
//	@Action(value = "modifyPwd", results = { @Result(name = "success", type="json",params={"root","map"})} )
	public String modifyUserPwd()throws Exception{
		if(oldPwd!=null&&newPwd!=null){
			map = userService.modifyUserPwd(oldPwd, newPwd);
			return SUCCESS;
		}
		return NONE;
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

	/**
	 * @return the oldPwd
	 */
	public String getOldPwd() {
		return oldPwd;
	}

	/**
	 * @param oldPwd the oldPwd to set
	 */
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	/**
	 * @return the newPwd
	 */
	public String getNewPwd() {
		return newPwd;
	}

	/**
	 * @param newPwd the newPwd to set
	 */
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
	
	
}
