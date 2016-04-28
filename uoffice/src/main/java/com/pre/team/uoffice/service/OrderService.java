package com.pre.team.uoffice.service;

import java.util.Map;

import com.pre.team.uoffice.domain.Order;


/**
 * 
 * Description:预约看房操作
 * @author xuejiahao  2015年9月2日
 *
 */
public interface OrderService {
	/**
	 * 
	 * Description:处理保存预约看房信息操作  2015年9月2日
	 * 
	 * @author xuejiahao
	 * @param order
	 * @return
	 */
	public Map<String,Object> dealSaveOrder(Order order);
}
