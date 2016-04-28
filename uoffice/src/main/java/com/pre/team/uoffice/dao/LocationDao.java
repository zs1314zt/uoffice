/**
 * 
 */
package com.pre.team.uoffice.dao;

import java.util.List;

import com.pre.team.uoffice.domain.OfficeLocation;

/**
 * Description:区域表操作Dao
 * @author xuejiahao  2015年6月28日
 * 
 */
public interface LocationDao {
	/**
	 * 
	 * Description:获取该省下的城市  2015年7月12日
	 * 
	 * @author xuejiahao
	 * @param location_code
	 * @return
	 */
	public List<OfficeLocation> getCity(String location_code);
	/**
	 * 
	 * Description:获取区  2015年7月12日
	 * 
	 * @author xuejiahao
	 * @param location_code
	 * @return
	 */
	public List<OfficeLocation> getArea(String location_code);
	/**
	 * 
	 * Description:获取区域  2015年7月12日
	 * 
	 * @author xuejiahao
	 * @param location_code
	 * @return
	 */
	public List<OfficeLocation> getTown(String location_code);
	/**
	 * 
	 * Description:获取省  2015年7月12日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	public List<OfficeLocation> getProvince();
	/**
	 * 
	 * Description:获取地址对象  2015年8月3日
	 * 
	 * @author xuejiahao
	 * @param location_code
	 * @return
	 */
	public OfficeLocation getOfficeLocation(String location_code);
	/**
	 * 
	 * Description:获取所有城市对象  2015年8月17日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	public List<OfficeLocation> getAllCity();
	
}
