/**
 * 
 */
package com.pre.team.uoffice.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.domain.OfficeLocation;
import com.pre.team.uoffice.service.LocationService;
import com.pre.team.uoffice.util.SessionUtil;

/**
 * Description:获取location的action
 * @author xuejiahao  2015年7月12日
 * 
 */
@ParentPackage("json-default") 
public class LocationAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<OfficeLocation> locationList;
	
	private OfficeLocation offLocation;
	
	private Map<String,Object> map;
	
	@Autowired
	private LocationService LocationService;
	
	private String location_code;
	/**
	 * 
	 * Description:获取地址信息  2015年7月15日
	 * 
	 * @author xuejiahao
	 * @return
	 * @throws Exception
	 */
	@Action(value = "getLocation", results = { @Result(name = "success", type="json",params={"root","locationList"})})
	public String getLocation() throws Exception{
		if(location_code!=null){
			locationList = LocationService.getLocation(location_code);
			return SUCCESS;
		}
		return NONE;
	}
	/**
	 * 
	 * Description:获取所有城市  2015年8月17日
	 * 
	 * @author xuejiahao
	 * @return
	 * @throws Exception
	 */
	@Action(value = "getAllCity", results = { @Result(name = "success", type="json",params={"root","locationList"})})
	public String getAllCity() throws Exception{
			locationList = LocationService.getAllCity();
			return SUCCESS;
	}
	
	/**
	 * 
	 * Description:更改城市  2015年8月17日
	 * 
	 * @author xuejiahao
	 * @return
	 * @throws Exception
	 */
	@Action(value = "changeCity", results = { @Result(name = "success", type="json",params={"root","map"})})
	public String changeCity() throws Exception{
//		map = new HashMap<String,Object>();
		System.out.println(offLocation.getLocationCode()+offLocation.getLocationName());
		if(offLocation.getLocationCode()!=null&&offLocation.getLocationName()!=null){
			SessionUtil.addOfficeLocationToSession(offLocation);
		}
//			map.put("code", Constants.OPERATE_SUCCESS_CODE);
//			map.put("officeLocation", offLocation);
//		}else {
//			map.put("code", Constants.OPERATE_FAIL_CODE);
//		}
		return NONE;
	}
	
	
	/**
	 * @return the cityList
	 */
	public List<OfficeLocation> getLocationList() {
		return locationList;
	}
	/**
	 * @param cityList the cityList to set
	 */
	public void setLocationList(List<OfficeLocation> locationList) {
		this.locationList = locationList;
	}
	/**
	 * @return the location_code
	 */
	public String getLocation_code() {
		return location_code;
	}
	/**
	 * @param location_code the location_code to set
	 */
	public void setLocation_code(String location_code) {
		this.location_code = location_code;
	}
	/**
	 * @return the offLocation
	 */
	public OfficeLocation getOffLocation() {
		return offLocation;
	}
	/**
	 * @param offLocation the offLocation to set
	 */
	public void setOffLocation(OfficeLocation offLocation) {
		this.offLocation = offLocation;
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
