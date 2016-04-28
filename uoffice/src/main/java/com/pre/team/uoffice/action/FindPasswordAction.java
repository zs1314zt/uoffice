/**
 * 
 */
package com.pre.team.uoffice.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.pre.team.uoffice.service.UserService;

/**
 * Description:
 * @author xuejiahao  2015年7月14日
 * 
 */
@ParentPackage("json-default")
public class FindPasswordAction  extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private UserService userService;
	private Map<String,Object> map;
	
	private String loginName;
	private String verificationCode;
	private String newPassword;
	/**
	 * 
	 * Description:密码找回  2015年7月15日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	@Action(value = "findPassword", results = { @Result(name = "success", type="json",params={"root","map"})})
	public String findPassword()throws Exception{
		if(loginName!=null&&verificationCode!=null&&newPassword!=null){
			map = userService.ForgetPwd(loginName, verificationCode, newPassword);
			return SUCCESS;
		}
		return NONE;
	}
	
	public Map<String, Object> getMap() {
		return map;
	}
	
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}

	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
	 * @return the verificationCode
	 */
	public String getVerificationCode() {
		return verificationCode;
	}

	/**
	 * @param verificationCode the verificationCode to set
	 */
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	/**
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
