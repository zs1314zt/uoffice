package com.pre.team.uoffice.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户反馈信息表
 * @author zs1314zt
 *
 */
public class Replay implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer replayId;
	private String userName;
	private String phone;
	private String city;
	private Date pubDate;
	private String message;
	
	public Integer getReplayId() {
		return replayId;
	}
	public void setReplayId(Integer replayId) {
		this.replayId = replayId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getPubDate() {
		return pubDate;
	}
	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}
	
	
}
