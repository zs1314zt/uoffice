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
public class Visit implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 访问id
	 */
	private Integer visitId;
	/**
	 * 访问IP地址
	 */
	private String visitIp;
	/**
	 * 访问时间
	 */
	private Date visitTime;
	
	public Visit(){
		
	}
	
	public Visit(String visitIp,Date visitTime){
		this.visitIp = visitIp;
		this.visitTime = visitTime;
	}

	/**
	 * @return the visitId
	 */
	public Integer getVisitId() {
		return visitId;
	}

	/**
	 * @param visitId the visitId to set
	 */
	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	/**
	 * @return the visitIp
	 */
	public String getVisitIp() {
		return visitIp;
	}

	/**
	 * @param visitIp the visitIp to set
	 */
	public void setVisitIp(String visitIp) {
		this.visitIp = visitIp;
	}

	/**
	 * @return the visitTime
	 */
	public Date getVisitTime() {
		return visitTime;
	}

	/**
	 * @param visitTime the visitTime to set
	 */
	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}
	
	

}
