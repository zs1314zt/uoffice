/**
 * 
 */
package com.pre.team.uoffice.domain;

import java.io.Serializable;

/**
 * Description:区域实体类
 * @author xuejiahao  2015年6月28日
 * 
 */
public class OfficeLocation implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer locationId;
	/**
	 * 地点编号
	 */
	private String locationCode;
	/**
	 * 地点名
	 */
	private String locationName;
	/**
	 * 备注
	 */
	private String remark;
	
	public OfficeLocation(String locationCode,String locationName){
		this.locationCode = locationCode;
		this.locationName = locationName;
	}
	
	public OfficeLocation(){
		
	}

	public Integer getLocationId() {
		return locationId;
	}

	public void setLocationId(Integer locationId) {
		this.locationId = locationId;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
