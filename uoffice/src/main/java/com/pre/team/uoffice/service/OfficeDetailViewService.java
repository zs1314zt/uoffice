package com.pre.team.uoffice.service;

import java.util.List;

import com.pre.team.uoffice.domain.Office;
import com.pre.team.uoffice.domain.OfficeDetail;

/**
 * 
 * @author zs1314zt
 * 办公室详情页面
 */
public interface OfficeDetailViewService {
	
	
	/**
	 * 根据officeId加载办公室详情
	 */
	public OfficeDetail getOfficeDetailInfoById(Integer officeId);
	/**
	 * 
	 * Description:获取相似的办公室信息  2015年8月3日
	 * 
	 * @author xuejiahao
	 * @param officeMoney
	 * @param locationId
	 * @return
	 */
	public List<Office> getOffices(int officeMoney,String locationId,int officeId);
}
