package com.pre.team.uoffice.action;

import java.util.Iterator;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionSupport;
import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.dao.SystemCodeDao;
import com.pre.team.uoffice.domain.Office;
import com.pre.team.uoffice.domain.OfficeLocation;
import com.pre.team.uoffice.domain.SystemCode;
import com.pre.team.uoffice.service.FirstViewService;
import com.pre.team.uoffice.service.LocationService;

@ParentPackage("struts-default") 
public class FirstViewAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private List<Office> moreViewOfficeList;
	private List<OfficeLocation> cityList;
	
	private String registUserCount;
	private String officeCount;
	private String successCount;
	
	@Autowired
	private FirstViewService firstViewService;
	
	@Autowired
	private LocationService LocationService;
	
	@Autowired
	private SystemCodeDao systemCodeDao;
	
	@Action(value = "public", results = { @Result(name = "success",location=Constants.HOME_JSP)})
	public String showPage() throws Exception{
		//查出所有的城市
		cityList = LocationService.getLocation(Constants.ZHEJIANG_CODE);
		moreViewOfficeList = firstViewService.getMoreViewOffice();
		List<SystemCode> systemList = systemCodeDao.getSystemCode();
		Iterator<SystemCode> it = systemList.iterator();
		while(it.hasNext()){
			SystemCode sys = it.next();
			if(sys.getParameterCode().equals(Constants.USER_TOTAL)){
				registUserCount = sys.getParameterValue();
			}else if(sys.getParameterCode().equals(Constants.OFFICE_TOTAL)){
				officeCount = sys.getParameterValue();
			}else if(sys.getParameterCode().equals(Constants.SUCCESS_TOTAL)){
				successCount = sys.getParameterValue();
			}
		}
		return SUCCESS;
	}

	public List<Office> getMoreViewOfficeList() {
		return moreViewOfficeList;
	}

	public void setMoreViewOfficeList(List<Office> moreViewOfficeList) {
		this.moreViewOfficeList = moreViewOfficeList;
	}

	public List<OfficeLocation> getCityList() {
		return cityList;
	}

	public void setCityList(List<OfficeLocation> cityList) {
		this.cityList = cityList;
	}

	public String getRegistUserCount() {
		return registUserCount;
	}

	public void setRegistUserCount(String registUserCount) {
		this.registUserCount = registUserCount;
	}

	public String getOfficeCount() {
		return officeCount;
	}

	public void setOfficeCount(String officeCount) {
		this.officeCount = officeCount;
	}

	public String getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(String successCount) {
		this.successCount = successCount;
	}



}
