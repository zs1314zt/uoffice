/**
 * 
 */
package com.pre.team.uoffice.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.domain.Order;
import com.pre.team.uoffice.service.OrderService;
import com.pre.team.uoffice.util.SessionUtil;
import com.pre.team.uoffice.util.StringUtil;

/**
 * Description:
 * @author xuejiahao  2015年9月2日
 * 
 */
@ParentPackage("json-default")
public class SendOrderAction extends ActionSupport{
	@Autowired
	private OrderService orderService;
	private Map<String,Object> map;
	private Order order;
	/**
	 * 
	 * Description:保存预约看房信息请求  2015年9月2日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	@Action(value = "sendOrder", results = { @Result(name = "success", type="json",params={"root","map"})})
	public String sendOrder(){
		if(order==null||order.getOfficeId()==null||StringUtil.isBlank(order.getOfficeName())||
				(StringUtil.isBlank(order.getPhone())&&StringUtil.isBlank(order.getEmail()))){
			return NONE;
		}
		map = orderService.dealSaveOrder(order);
		
		return SUCCESS;
	}
	/**
	 * @return the orderService
	 */
	public OrderService getOrderService() {
		return orderService;
	}
	/**
	 * @param orderService the orderService to set
	 */
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	/**
	 * @return the map
	 */
	public Map<String, Object> getMap() {
		return map;
	}
	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	
	public Order getOrder() {
		return order;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	
	
}
