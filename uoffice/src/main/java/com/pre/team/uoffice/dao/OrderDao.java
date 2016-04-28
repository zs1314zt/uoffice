package com.pre.team.uoffice.dao;

import com.pre.team.uoffice.domain.Order;

/**
 * 
 * Description:预约看房表
 * @author xuejiahao  2015年9月2日
 *
 */
public interface OrderDao {
	/**
	 * 
	 * Description:保存预约看房信息  2015年9月2日
	 * 
	 * @author xuejiahao
	 * @param order
	 * @return
	 */
	public boolean saveOrder(Order order);
}
