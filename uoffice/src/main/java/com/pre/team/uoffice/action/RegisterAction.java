package com.pre.team.uoffice.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.pre.team.uoffice.service.UserService;
/**
 * 
 * Description:注册Action
 * @author xuejiahao  2015年7月9日
 *
 */
@ParentPackage("json-default") 
public class RegisterAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;
	private Map<String,Object> dataMap;
	private String registerName;
	private String userPwd;
	private String verificationCode;
	
	@Action(value = "register", results = { @Result(name = "success", type="json",params={"root","dataMap"})})
	/**
	 * 
	 * Description:注册  2015年7月9日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	public String register()throws Exception{
		if(registerName!=null&&userPwd!=null&&verificationCode!=null){
			dataMap = userService.register(registerName,userPwd,verificationCode);
			return SUCCESS;
		}
		return NONE;
	}
	
	
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	
	public String getVerificationCode() {
		return verificationCode;
	}



	/**
	 * @return the registerName
	 */
	public String getRegisterName() {
		return registerName;
	}



	/**
	 * @param registerName the registerName to set
	 */
	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}
	
	
	
	
}
