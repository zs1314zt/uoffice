package com.pre.team.uoffice.dao;

import com.pre.team.uoffice.domain.Visit;


/**
 * 
 * Description:ip访问操作表Dao
 * @author xuejiahao  2015年9月14日
 *
 */
public interface VisitDao {
	/**
	 * 
	 * Description:存储访问ip信息  2015年9月14日
	 * 
	 * @author xuejiahao
	 * @param visit
	 */
	public void saveVisit(Visit visit);
}
