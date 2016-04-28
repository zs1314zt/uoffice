package com.pre.team.uoffice.action;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.pre.team.uoffice.bean.FilterOfficeBean;
import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.domain.Office;
import com.pre.team.uoffice.domain.OfficeLocation;
import com.pre.team.uoffice.domain.QueryResult;
import com.pre.team.uoffice.num.OfficeTypeEnum;
import com.pre.team.uoffice.num.RentDateEnum;
import com.pre.team.uoffice.num.RentMoneyEnum;
import com.pre.team.uoffice.num.RentPerCntEnum;
import com.pre.team.uoffice.service.LocationService;
import com.pre.team.uoffice.service.OfficeDetailSelectService;
import com.pre.team.uoffice.util.SessionUtil;

/**
 * Description:查询action
 * @author xuejiahao  2015年7月18日
 * 
 */
@ParentPackage("struts-default")
public class OfficeSearchAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private OfficeDetailSelectService officeDetailSelectService;	
	@Autowired
	private LocationService LocationService;
	
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
	
	private Integer totalPage;
	
	private List<FilterOfficeBean> filterList = new ArrayList<FilterOfficeBean>();
	
	private List<Office> officeList;
	/**
	 * Description:办公室查询接口  2015年7月18日
	 * @author xuejiahao
	 * @return
	 */
	@Action(value = "search", results = { @Result(name = "success",location=Constants.FILTER_OFFICE)})
	public String search() throws Exception {
		//初始化
		initAction();
		CreatOrUpdateHrefUrl(initUrl());
		if(page!=null&&pagesize!=null){
			QueryResult result = officeDetailSelectService.Search(location_code, rentDateType, rentMoney, CompanySize, officeType, page, pagesize);
			translateList(result.getOfficeList());
			totalPage = result.getTotalPages();
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

	//根据参数创建url
	private String initUrl() {
		StringBuffer url = new StringBuffer();
		url.append("/uoffice/search?");
		
		if(location_code != null && !"".equals(location_code)){
			url.append("location_code=" + location_code);
		}else{
			url.append("location_code=0101");
		}
		
		if(rentDateType != null && !"".equals(rentDateType)){
			url.append("&rentDateType=" + rentDateType);
		}else{
			url.append("&rentDateType=00");
		}
		
		if(rentMoney != null && !"".equals(rentMoney)){
			url.append("&rentMoney=" + rentMoney);
		}else{
			url.append("&rentMoney=00");
		}
		
		if(CompanySize != null && !"".equals(CompanySize)){
				url.append("&CompanySize=" + CompanySize);
		}else{
			url.append("&CompanySize=00");
		}
		
		if(officeType != null && !"".equals(officeType)){
			url.append("&officeType=" + officeType);
		}else{
			url.append("&officeType=00");
		}

		return url.toString();
	}

	//默认为第一页，查询10条记录
	private void initAction() {
		if(page == null){
			page = 1;
		}
		if(pagesize == null){
			pagesize = 12;
		}
		if(location_code == null || "".equals(location_code)){
			location_code = SessionUtil.getCurrentLocationCode();
		}
		if(rentDateType == null || "".equals(rentDateType)){
			rentDateType = RentDateEnum.RentDateA.getCode();
		}
		if(rentMoney == null || "".equals(rentMoney)){
			rentMoney = RentMoneyEnum.RentMoneyA.getCode();
		}
		if(officeType == null || "".equals(officeType)){
			officeType = OfficeTypeEnum.OfficeTypeA.getCode();
		}
		//根据home页面传的公司人数重新赋值
		if(CompanySize == null || "".equals(CompanySize)){
			CompanySize = RentPerCntEnum.RentPerCntA.getCode();
		}
		else if(!CompanySize.startsWith("0")){
			int CompanyCount= Integer.parseInt(CompanySize);
			if(CompanyCount >= 1 && CompanyCount <= 3){
				CompanySize = RentPerCntEnum.RentPerCntB.getCode();
			}else if(CompanyCount >= 4 && CompanyCount <= 6){
				CompanySize = RentPerCntEnum.RentPerCntC.getCode();
			}else if(CompanyCount >= 7 && CompanyCount <= 9){
				CompanySize = RentPerCntEnum.RentPerCntD.getCode();
			}else if(CompanyCount >= 10 && CompanyCount <= 15){
				CompanySize = RentPerCntEnum.RentPerCntE.getCode();
			}else if(CompanyCount >= 16 && CompanyCount <= 20){
				CompanySize = RentPerCntEnum.RentPerCntF.getCode();
			}else if(CompanyCount >= 21){
				CompanySize = RentPerCntEnum.RentPerCntG.getCode();
			}else{
				CompanySize = RentPerCntEnum.RentPerCntA.getCode();
			}
		}
	}

	/**
	 * 创建或更新url
	 */
	private void CreatOrUpdateHrefUrl(String FILTER_OFFICE){
		
		//城市下所有区
		String secLoc = location_code.substring(0, 4);
		
		//遍历地区
		List<OfficeLocation> locationList = LocationService.getLocation(secLoc);
		
		//为地点信息单独加上不限
		FilterOfficeBean bean1 = new FilterOfficeBean();
		bean1.setType("02");
		bean1.setParamCode(secLoc);
		bean1.setParamValue("不限");
		String url1 = updateUrl("location_code", secLoc, FILTER_OFFICE);
		bean1.setHrefUrl(url1);
		if(secLoc.equals(location_code)){
			bean1.setLightFlag("z-active");
		}
		
		filterList.add(bean1);
		
		Iterator<OfficeLocation> it = locationList.iterator();
		while(it.hasNext()){
			FilterOfficeBean bean = new FilterOfficeBean();
			OfficeLocation loc = it.next();
			bean.setType("02");
			bean.setParamCode(loc.getLocationCode());
			bean.setParamValue(loc.getLocationName());
			String url = updateUrl("location_code", loc.getLocationCode(), FILTER_OFFICE);
			bean.setHrefUrl(url);
			if(location_code != null && location_code.equals(loc.getLocationCode())){
				bean.setLightFlag("z-active");
			}
			filterList.add(bean);
		}
		
		//遍历租赁时间枚举
		for(RentDateEnum rentDate :RentDateEnum.values()){
			FilterOfficeBean bean = new FilterOfficeBean();
			bean.setType("01");
			bean.setParamCode(rentDate.getCode());
			bean.setParamValue(rentDate.getValue());
			//更新FILTER_OFFICE
			String url = updateUrl("rentDateType", rentDate.getCode(), FILTER_OFFICE);
			bean.setHrefUrl(url);
			if(rentDateType != null && rentDateType.equals(rentDate.getCode())){
				bean.setLightFlag("z-active");
			}
			filterList.add(bean);
		}
		
		//遍历公司人数
		for(RentPerCntEnum rentPerCnt :RentPerCntEnum.values()){
			FilterOfficeBean bean = new FilterOfficeBean();
			bean.setType("03");
			bean.setParamCode(rentPerCnt.getCode());
			bean.setParamValue(rentPerCnt.getValue());
			//更新FILTER_OFFICE
			String url = updateUrl("CompanySize", rentPerCnt.getCode(), FILTER_OFFICE);
			bean.setHrefUrl(url);
			if(CompanySize != null && CompanySize.equals(rentPerCnt.getCode())){
				bean.setLightFlag("z-active");
			}
			filterList.add(bean);
		}
		
		//遍历租金
		for(RentMoneyEnum rentMoneyEnum :RentMoneyEnum.values()){
			FilterOfficeBean bean = new FilterOfficeBean();
			bean.setType("04");
			bean.setParamCode(rentMoneyEnum.getCode());
			bean.setParamValue(rentMoneyEnum.getValue());
			//更新FILTER_OFFICE
			String url = updateUrl("rentMoney", rentMoneyEnum.getCode(), FILTER_OFFICE);
			bean.setHrefUrl(url);
			if(rentMoney != null && rentMoney.equals(rentMoneyEnum.getCode())){
				bean.setLightFlag("z-active");
			}
			filterList.add(bean);
		}
		
		//遍历办公室类型
		for(OfficeTypeEnum officeTypeEnum :OfficeTypeEnum.values()){
			FilterOfficeBean bean = new FilterOfficeBean();
			bean.setType("05");
			bean.setParamCode(officeTypeEnum.getCode());
			bean.setParamValue(officeTypeEnum.getValue());
			//更新FILTER_OFFICE
			String url = updateUrl("officeType", officeTypeEnum.getCode(), FILTER_OFFICE);
			bean.setHrefUrl(url);
			if(officeType != null && officeType.equals(officeTypeEnum.getCode())){
				bean.setLightFlag("z-active");
			}
			filterList.add(bean);
		}
	}
	
	/**
	 * 根据不同的筛选项，替换不同的参数值
	 * @param str
	 * @param code
	 * @return
	 */
	private String updateUrl(String str, String code, String FILTER_OFFICE) {
		int startIndex = FILTER_OFFICE.indexOf(str) + str.length() + 1;
		int endIndex = 0;
		//对地点点击不限做特殊处理
		if("location_code".equals(str) && location_code != null){
			endIndex = startIndex + location_code.length();
		}else{
			endIndex = startIndex + code.length();
		}
		String startStr = FILTER_OFFICE.substring(0, startIndex);
		String endStr = FILTER_OFFICE.substring(endIndex);
		return startStr + code + endStr;
	}
	
	public OfficeDetailSelectService getOfficeDetailSelectService() {
		return officeDetailSelectService;
	}

	public void setOfficeDetailSelectService(
			OfficeDetailSelectService officeDetailSelectService) {
		this.officeDetailSelectService = officeDetailSelectService;
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

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<FilterOfficeBean> getFilterList() {
		return filterList;
	}

	public void setFilterList(List<FilterOfficeBean> filterList) {
		this.filterList = filterList;
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
