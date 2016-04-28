package com.pre.team.uoffice.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.service.UserService;

/**
 * 
 * Description:登录Action
 * @author xuejiahao  2015年7月9日
 *
 */
@ParentPackage("json-default") 
public class LoginAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;
	
	private Map<String,Object> dataMap;
	private String password;
	private String username;
	@Action(value = "login", results = { @Result(name = "success", type="json",params={"root","dataMap"})})
	/**
	 * 
	 * Description:登录  2015年7月9日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	public String login()throws Exception{
		if(username!=null&&password!=null){
			dataMap = userService.login(username, password);
			return SUCCESS;
		}
		return NONE;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
}
