package com.pre.team.uoffice.bean;

/**
 * 显示在页面中的筛选条件
 * @author zs1314zt
 *
 */
public class FilterOfficeBean {

	/**
	 * 类型
	 * 01，租赁时间
	 * 02，租赁区域
	 * 03，公司人数
	 * 04，月租金
	 * 05,办公室类型
	 */
	private String type;
	
	/**
	 * 参数code
	 */
	private String paramCode; 
	
	/**
	 * 参数描述
	 */
	private String paramValue;
	/**
	 * 参数url
	 */
	private String hrefUrl;
	
	/**
	 * 添加高亮属性
	 * @return
	 */
	private String lightFlag;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getParamCode() {
		return paramCode;
	}
	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}
	public String getParamValue() {
		return paramValue;
	}
	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	public String getHrefUrl() {
		return hrefUrl;
	}
	public void setHrefUrl(String hrefUrl) {
		this.hrefUrl = hrefUrl;
	}
	public String getLightFlag() {
		return lightFlag;
	}
	public void setLightFlag(String lightFlag) {
		this.lightFlag = lightFlag;
	}
	
	
}
