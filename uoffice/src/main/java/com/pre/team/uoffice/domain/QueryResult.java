/**
 * 
 */
package com.pre.team.uoffice.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Description:搜索分页查询结果类
 * @author xuejiahao  2015年7月18日
 * 
 */
public class QueryResult implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 办公室集合
	 */
	private List<Office> officeList;
	/**
	 * 留言集合
	 */
	private List<Message> messageList;
	/**
	 * 总数
	 */
	private Integer total;
	/**
	 * 总页数
	 */
	private Integer totalPages;
	
	
	public QueryResult(){
		
	}
	
	public QueryResult(List<Office> officeList,Integer total){
		this.officeList = officeList;
		this.total = total;
	}

	/**
	 * @return the officeList
	 */
	public List<Office> getOfficeList() {
		return officeList;
	}

	/**
	 * @param officeList the officeList to set
	 */
	public void setOfficeList(List<Office> officeList) {
		this.officeList = officeList;
	}

	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	public void calcuateTotalPages(Integer pageSize) {
		if(pageSize == 0){
			this.totalPages = 1;
			return;
		}
		this.totalPages = total % pageSize == 0 ? total / pageSize
                : (total / pageSize + 1);
	}
	
	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	
	public Integer getTotalPages() {
		return totalPages;
	}

	/**
	 * @return the messageList
	 */
	public List<Message> getMessageList() {
		return messageList;
	}

	/**
	 * @param messageList the messageList to set
	 */
	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}
	
	
	
}