/**
 * 
 */
package com.pre.team.uoffice.service;

import java.util.List;

import com.pre.team.uoffice.domain.OfficeLocation;

/**
 * Description:
 * @author xuejiahao  2015年7月12日
 * 
 */
public interface LocationService {
	
	/**
	 * 
	 * Description:根据location_code的长度来判断查询哪种Location  2015年7月12日
	 * 
	 * @author xuejiahao
	 * @param location_code
	 * @return
	 */
	public List<OfficeLocation> getLocation(String location_code);
	/**
	 * 
	 * Description:获取所有城市  2015年8月17日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	public List<OfficeLocation> getAllCity();
	
	
}
