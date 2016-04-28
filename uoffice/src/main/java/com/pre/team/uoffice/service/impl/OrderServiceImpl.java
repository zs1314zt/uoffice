/**
 * 
 */
package com.pre.team.uoffice.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.dao.OrderDao;
import com.pre.team.uoffice.domain.Order;
import com.pre.team.uoffice.service.OrderService;
import com.pre.team.uoffice.util.SessionUtil;

/**
 * Description:
 * @author xuejiahao  2015年9月2日
 * 
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	@Override
	public Map<String,Object> dealSaveOrder(Order order) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(SessionUtil.getCurrentUser()==null){
			map.put("code", Constants.OPERATE_FAIL_CODE);
			map.put("message", "您未登录，请先登录再预约");
			return map;
		}
		order.setOrderTime(new Date());
		if(orderDao.saveOrder(order)){
			map.put("code", Constants.OPERATE_SUCCESS_CODE);
			map.put("message", "预约成功，请耐心等待我们联系您哦");
		}else{
			map.put("code", Constants.OPERATE_FAIL_CODE);
			map.put("message", "预约失败，请重新尝试");
		}
		return map;
	}



}
