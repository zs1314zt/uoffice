/**
 * 
 */
package com.pre.team.uoffice.dao.impl;

import org.springframework.stereotype.Repository;

import com.pre.team.uoffice.dao.OrderDao;
import com.pre.team.uoffice.domain.Order;

/**
 * Description:
 * @author xuejiahao  2015年9月2日
 * 
 */
@Repository
public class OrderDaoImpl extends HibernateBaseDao<Order> implements OrderDao {
	
	@Override
	public boolean saveOrder(Order order) {
		boolean result =false;
		if(save(order)!=null){
			result = true;
		}
		return result;
	}

}
