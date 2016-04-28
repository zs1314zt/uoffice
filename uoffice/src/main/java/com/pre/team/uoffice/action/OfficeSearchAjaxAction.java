package com.pre.team.uoffice.action;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.pre.team.uoffice.domain.Office;
import com.pre.team.uoffice.domain.QueryResult;
import com.pre.team.uoffice.num.OfficeTypeEnum;
import com.pre.team.uoffice.service.LocationService;
import com.pre.team.uoffice.service.OfficeDetailSelectService;

/**
 * Description:查询action
 * @author xuejiahao  2015年7月18日
 * 
 */
@ParentPackage("json-default")
public class OfficeSearchAjaxAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private OfficeDetailSelectService officeDetailSelectService;
	
	@Autowired
	private LocationService LocationService;
	
	private Map<String,Object> map;
	
	/**
	 * 首页传入参数
	 */
	private Date selectDate;
	private String location_code;
	private String rentDateType;
	private String rentMoney;
	private String CompanySize;
	private String officeType;
	private Integer page;
	private Integer pagesize;
	
	private List<Office> officeList;
	
	/**
	 * Description:办公室查询接口  2015年7月18日
	 * @author xuejiahao
	 * @return
	 */
	@Action(value = "searchAjax", results = { @Result(name = "success", type="json",params={"root","map"})})
	public String search() throws Exception{
		//初始化
		initAction();
		map = new HashMap<String,Object>();
		if(page!=null&&pagesize!=null){
			QueryResult result = officeDetailSelectService.Search(location_code, rentDateType, rentMoney, CompanySize, officeType, page, pagesize);
			translateList(result.getOfficeList());
			map.put("officeList",officeList);
			map.put("page",page.intValue());
		}
		return SUCCESS;
	}
	
	private void translateList(List<Office> offTempList) {
		officeList = new ArrayList<Office>();
		Iterator<Office> it = offTempList.iterator();
		while(it.hasNext()){
			Office off = it.next();
			if(OfficeTypeEnum.OfficeTypeB.getCode().equals(off.getOfficeType())){
				off.setOfficeType(OfficeTypeEnum.OfficeTypeB.getValue());
			}else if(OfficeTypeEnum.OfficeTypeC.getCode().equals(off.getOfficeType())){
				off.setOfficeType(OfficeTypeEnum.OfficeTypeC.getValue());
			}else if(OfficeTypeEnum.OfficeTypeD.getCode().equals(off.getOfficeType())){
				off.setOfficeType(OfficeTypeEnum.OfficeTypeD.getValue());
			}else if(OfficeTypeEnum.OfficeTypeE.getCode().equals(off.getOfficeType())){
				off.setOfficeType(OfficeTypeEnum.OfficeTypeE.getValue());
			}
			
			if(off.getPhotoUrl() != null && off.getPhotoUrl().contains(";")){
				String[] photoUrls = off.getPhotoUrl().split(";");
				off.setPhotoUrl(photoUrls[0]);
			}
			officeList.add(off);
		}
	}

	//默认为第一页，查询10条记录
	private void initAction() {
		if(page == null){
			page = 1;
		}
		if(pagesize == null){
			pagesize = 12;
		}
		if(location_code == null){
			location_code = "0101";
		}
	}
	
	public OfficeDetailSelectService getOfficeDetailSelectService() {
		return officeDetailSelectService;
	}

	public void setOfficeDetailSelectService(
			OfficeDetailSelectService officeDetailSelectService) {
		this.officeDetailSelectService = officeDetailSelectService;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getLocation_code() {
		return location_code;
	}

	public void setLocation_code(String location_code) {
		this.location_code = location_code;
	}

	public String getRentDateType() {
		return rentDateType;
	}

	public void setRentDateType(String rentDateType) {
		this.rentDateType = rentDateType;
	}

	public String getRentMoney() {
		return rentMoney;
	}

	public void setRentMoney(String rentMoney) {
		this.rentMoney = rentMoney;
	}

	public String getCompanySize() {
		return CompanySize;
	}

	public void setCompanySize(String companySize) {
		CompanySize = companySize;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPagesize() {
		return pagesize;
	}
	
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	
	public String getOfficeType() {
		return officeType;
	}
	
	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}

	public Date getSelectDate() {
		return selectDate;
	}

	public void setSelectDate(Date selectDate) {
		this.selectDate = selectDate;
	}

	public List<Office> getOfficeList() {
		return officeList;
	}

	public void setOfficeList(List<Office> officeList) {
		this.officeList = officeList;
	}
	
}
