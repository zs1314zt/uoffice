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
import com.pre.team.uoffice.service.ModifyOfficeService;

/**
 * Description:
 * @author xuejiahao  2015年7月15日
 * 
 */
@ParentPackage("json-default")
public class DeleteOfficeInfoAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private ModifyOfficeService modifyOfficeService;
	
	private Integer officeId;
	
	private Map<String,Object> map;
	/**
	 * 
	 * Description:删除办公室信息  2015年7月15日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	@Action(value = "deleteOfficeInfo",results = {@Result(name = "success",type = "json",params = {"root","map"})})
	public String deleteOfficeInfo()throws Exception{
		if(officeId!=null){
			map = modifyOfficeService.deleteOfficeInfo(officeId);
			return SUCCESS;
		}
		return NONE;
	}

	/**
	 * @return the officeId
	 */
	public Integer getOfficeId() {
		return officeId;
	}

	/**
	 * @param officeId the officeId to set
	 */
	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}

	/**
	 * @return the map
	 */
	public Map<String, Object> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	
}
