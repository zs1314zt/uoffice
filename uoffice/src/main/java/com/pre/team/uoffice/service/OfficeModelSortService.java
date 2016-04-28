package com.pre.team.uoffice.service;

import java.util.List;

import com.pre.team.uoffice.domain.Office;

/**
 * 
 * @author zs1314zt
 * 办公类型分类：包括短期出租、长期出租、联合办公、孵化器
 */
public interface OfficeModelSortService {

	/**
	 * 根据officeType查询出某一个类型的办公室信息
	 */
	public List<Office> getOfficeByType(String officeType);
	
}
