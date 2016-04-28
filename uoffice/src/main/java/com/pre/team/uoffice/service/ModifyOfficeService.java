package com.pre.team.uoffice.service;

import java.util.Date;
import java.util.Map;

import com.pre.team.uoffice.domain.Office;

/**
 * @author xuejiahao
 * 增、删、改办公室信息
 * */
public interface ModifyOfficeService {
	/**
	 * 
	 * Description:发布办公室信息  2015年7月15日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	public Map<String,Object> addOfficeInfo(Office office);
	/**
	 * 
	 * Description:更新办公室信息  2015年7月15日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	public Map<String,Object> updateOfficeInfo(Office office);
	/**
	 * 
	 * Description:删除办公室信息  2015年7月15日
	 * 
	 * @author xuejiahao
	 * @param officeId
	 * @return
	 */
	public Map<String,Object> deleteOfficeInfo(Integer officeId);
	/**
	 * 
	 * Description:根据办公室编号获取办公室信息  2015年7月21日
	 * 
	 * @author xuejiahao
	 * @param officeId
	 * @return
	 */
	public Office getOfficeByOfficeId(Integer officeId);
}
