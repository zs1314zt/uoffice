/**
 * 
 */
package com.pre.team.uoffice.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.domain.Office;
import com.pre.team.uoffice.domain.OfficeLocation;
import com.pre.team.uoffice.service.LocationService;
import com.pre.team.uoffice.service.ModifyOfficeService;
import com.pre.team.uoffice.util.SessionUtil;

/**
 * Description:
 * @author xuejiahao  2015年7月15日
 * 
 */
@ParentPackage("json-default")
public class PublishOrUpdateOfficeInfoAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ModifyOfficeService modifyOfficeService;
	
	@Autowired
	private LocationService locationService;
	
	private Map<String,Object> map;
	
	private Office office;
	private Integer officeId;
	private String areaCode;
	private String townCode;
	/**
	 * 区集合对象
	 */
	private List<OfficeLocation> arealist;
	/**
	 * 板块集合对象
	 */
	private List<OfficeLocation> townlist;
	/**
	 * 
	 * Description:发布或修改办公室信息  2015年7月15日
	 * 
	 * @author xuejiahao
	 * @param officeId
	 * @param officeName
	 * @param detailAddr
	 * @param locationId
	 * @param officeDesc
	 * @param officeContent
	 * @param officeType
	 * @param rentDate
	 * @param officeMoney
	 * @param photoUrl
	 * @param viewCount
	 * @param officeContact
	 * @param officeArea
	 * @param officeSize
	 * @return
	 */
	@Action(value = "publishOrUpdateOfficeInfo", results = { @Result(name = "success", type="json",params={"root","map"})})
	public String publishOrUpdate()throws Exception{
			if(office!=null){
				if(office.getOfficeId()!= null){
					//修改办公室信息
					map = modifyOfficeService.updateOfficeInfo(office);
				}else{
					//发布办公室信息
					map = modifyOfficeService.addOfficeInfo(office);
				}
				return SUCCESS;
			}
		return NONE;
	}
	/**
	 * 
	 * Description:获取修改办公司信息界面  2015年7月21日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	@Action(value = "ModifyView", results = { @Result(name = "success",location=Constants.RELEASE_JSP),@Result(name = Constants.ERROR_INFO,location=Constants.ERROR_INFO)})
	public String getModifyView() throws Exception{
		if(officeId!=null){
			//获取办公室详情
			office = modifyOfficeService.getOfficeByOfficeId(officeId);
			if(office!=null){
				//板块编号
				townCode = office.getLocationId();
				//区编号
				areaCode = office.getLocationId().substring(0, 6);
				//区集合
				arealist = locationService.getLocation(office.getLocationId().substring(0, 4));
				//板块集合
				townlist = locationService.getLocation(office.getLocationId().substring(0, 6));
			}else{
				return Constants.ERROR_INFO;
			}
			return SUCCESS;
		}
		return NONE;
	}
	
	/**
	 * 
	 * Description:获取修改办公司信息界面  2015年7月21日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	@Action(value = "publishOfficeView", results = { @Result(name = "success",location=Constants.RELEASE_JSP)})
//	@Action(value = "publishOfficeView", results = { @Result(name = "success", type="json",params={"root","arealist"})})
	public String publishOfficeView() throws Exception{
		//区集合
		arealist = locationService.getLocation(SessionUtil.getCurrentLocationCode());
		return SUCCESS;
	}
	
	public void setOffice(Office office) {
		this.office = office;
	}
	public Office getOffice() {
		return office;
	}
	
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	
	public Integer getOfficeId() {
		return officeId;
	}
	
	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}
	
	public void setArealist(List<OfficeLocation> arealist) {
		this.arealist = arealist;
	}
	
	public List<OfficeLocation> getArealist() {
		return arealist;
	}
	
	public void setTownlist(List<OfficeLocation> townlist) {
		this.townlist = townlist;
	}
	
	public List<OfficeLocation> getTownlist() {
		return townlist;
	}
	/**
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}
	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	/**
	 * @return the townCode
	 */
	public String getTownCode() {
		return townCode;
	}
	/**
	 * @param townCode the townCode to set
	 */
	public void setTownCode(String townCode) {
		this.townCode = townCode;
	}
	
	
}