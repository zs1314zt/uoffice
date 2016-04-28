package com.pre.team.uoffice.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.domain.Office;
import com.pre.team.uoffice.service.OfficeModelSortService;

@ParentPackage("struts-default") 
public class OfficeModelSortAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	@Autowired
	private OfficeModelSortService officeModelSortService;
	
	private List<Office> officeList = null;
	
	private Office office = null;
	
	private String officeType;
	
	/**
	 * 根据办公室类型获取办公室列表信息
	 * @return
	 * @throws Exception
	 */
	@Action(value = "getOfficeByType", results={ @Result(name = "success",location=Constants.SORT_OFFICE)})
	public String getOfficeListByType() throws Exception{
		officeList = officeModelSortService.getOfficeByType(officeType);	
		return SUCCESS;
	}
	
	public String getOfficeType() {
		return officeType;
	}
	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}
	public List<Office> getOfficeList() {
		return officeList;
	}
	public void setOfficeList(List<Office> officeList) {
		this.officeList = officeList;
	}
	public Office getOffice() {
		return office;
	}
	public void setOffice(Office office) {
		this.office = office;
	}
	
}
