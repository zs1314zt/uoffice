package com.pre.team.uoffice.domain;

import java.io.Serializable;

/**
 * Description:系统相关参数
 * @author zs1314zt 
 * 
 */
public class SystemCode implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer sysId;
	/**
	 * 参数编号
	 */
	private String parameterCode;
	/**
	 * 参数名称
	 */
	private String parameterName;
	/**
	 * 参数值
	 */
	private String parameterValue;
	
	public SystemCode(){
		
	}
	public Integer getSysId() {
		return sysId;
	}
	public void setSysId(Integer sysId) {
		this.sysId = sysId;
	}
	public String getParameterCode() {
		return parameterCode;
	}
	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getParameterValue() {
		return parameterValue;
	}
	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}
	
	
}
