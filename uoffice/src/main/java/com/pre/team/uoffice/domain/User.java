package com.pre.team.uoffice.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Description:用户实体类
 * @author xuejiahao  2015年6月28日
 *
 */
public class User implements Serializable {
	/**
	 * 主键
	 */
	private Integer userId;
	/**
	 * 用户昵称
	 */
	private String userName;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 手机号
	 */
	private String userPhone;
	/**
	 * 邮箱
	 */
	private String userEmail;
	/**
	 * 密码
	 */
	private String userPwd;
	/**
	 * 用户权限,01普通用户,02管理员
	 */
	private String userType;
	/**
	 * ip
	 */
	private String ip;
	/**
	 * 注册时间
	 */
	private Date registerTime;
	/**
	 * 备注
	 */
	private String remark;
	
	public User(){
		
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the registerTime
	 */
	public Date getRegisterTime() {
		return registerTime;
	}

	/**
	 * @param registerTime the registerTime to set
	 */
	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}
	
	
	
	
}
