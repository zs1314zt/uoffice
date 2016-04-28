/**
 * 
 */
package com.pre.team.uoffice.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:留言信息实体类
 * @author xuejiahao  2015年6月28日
 * 
 */
public class Message implements Serializable{
	private static SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd"); 
	/**
	 * 留言id
	 */
	private Integer messId;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 办公室id
	 */
	private Integer officeId;
	/**
	 * 留言内容
	 */
	private String messContent;
	/**
	 * 留言时间
	 */
	private Date messTime;
	/**
	 * 留言时间
	 */
	private String time;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 用户昵称
	 */
	private String userName;
	
	public Message(){
		
	}

	public Integer getMessId() {
		return messId;
	}

	public void setMessId(Integer messId) {
		this.messId = messId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}

	public String getMessContent() {
		return messContent;
	}

	public void setMessContent(String messContent) {
		this.messContent = messContent;
	}

	public Date getMessTime() {
		return messTime;
	}

	public void setMessTime(Date messTime) {
		this.messTime = messTime;
		if(messTime!=null){
			setTime(format.format(this.messTime));
		}
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setTime(String time) {
		this.time = time;
	}
	
	public String getTime() {
		return time;
	}
}
