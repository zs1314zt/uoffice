/**
 * 
 */
package com.pre.team.uoffice.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.domain.Message;
import com.pre.team.uoffice.domain.Office;
import com.pre.team.uoffice.domain.OfficeDetail;
import com.pre.team.uoffice.domain.QueryResult;
import com.pre.team.uoffice.service.MessageService;
import com.pre.team.uoffice.service.OfficeDetailViewService;

/**
 * Description:详情页面action
 * @author xuejiahao  2015年8月3日
 * 
 */
//@ParentPackage("struts-defaulto") 
@ParentPackage("json-default")
public class OfficeDetailAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private OfficeDetailViewService officeDetailViewService;
	@Autowired
	private MessageService messageService;
	
	private Integer officeId;
	
	private Integer page;
	
	private Integer pagesize;
	
	private OfficeDetail officeDetail;
	
	private QueryResult queryResult;
	
	private Map<String,Object> map;
	
	private Message message;
	
	private List<Office> officeList;
	
	private String verifyCode;
	/**
	 * 
	 * Description:打开办公室详情页面  2015年8月3日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	@Action(value = "officeDetail", results = { @Result(name = "success",location=Constants.OFFICE_DETAIL),@Result(name = Constants.ERROR_INFO,location=Constants.ERROR_INFO)})
//	@Action(value = "officeDetail", results = { @Result(name = "success", type="json",params={"root","officeList"})})
	public String officeDetail() throws Exception{
		//办公室页面显示详情对象
		officeDetail = officeDetailViewService.getOfficeDetailInfoById(officeId);
		if(officeDetail==null){
			return Constants.ERROR_INFO;
		}
		initAction();
		queryResult = messageService.getMessagesByOfficeId(officeId, page, pagesize);
		officeList = officeDetailViewService.getOffices(officeDetail.getOfficeMoney(), officeDetail.getLocationId(),officeDetail.getOfficeId());
		return SUCCESS;
	}
	/**
	 * 
	 * Description:获取留言信息  2015年8月3日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	@Action(value = "Message", results = { @Result(name = "success", type="json",params={"root","map"})})
	public String getUserMessage() throws Exception{
		initAction();
		queryResult = messageService.getMessagesByOfficeId(officeId, page, pagesize);
		map = new HashMap<String,Object>();
		map.put("messageList",queryResult.getMessageList());
		map.put("page",page);
		map.put("totalPages", queryResult.getTotalPages());
		return SUCCESS;
	}
	/**
	 * 
	 * Description:发布留言  2015年8月3日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	@Action(value = "publishMes", results = { @Result(name = "success", type="json",params={"root","map"})})
	public String publishMessage() throws Exception{
		if(message!=null){
			map = messageService.publishMessage(message,verifyCode);
		}
		return SUCCESS;
	}
	/**
	 * 
	 * Description:初始化  2015年8月3日
	 * 
	 * @author xuejiahao
	 */
	private void initAction(){
		if(page == null){
			page = 0;
		}
		if(pagesize == null){
			pagesize = 0;
		}
	}
	
	/**
	 * @return the officeId
	 */
	public Integer getOfficeId() {
		return officeId;
	}
	/**
	 * @param officeId the officeId to set
	 */
	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}
	/**
	 * @return the officeDetail
	 */
	public OfficeDetail getOfficeDetail() {
		return officeDetail;
	}
	/**
	 * @param officeDetail the officeDetail to set
	 */
	public void setOfficeDetail(OfficeDetail officeDetail) {
		this.officeDetail = officeDetail;
	}


	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}


	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}


	/**
	 * @return the pagesize
	 */
	public Integer getPagesize() {
		return pagesize;
	}


	/**
	 * @param pagesize the pagesize to set
	 */
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	/**
	 * @return the queryResult
	 */
	public QueryResult getQueryResult() {
		return queryResult;
	}
	/**
	 * @param queryResult the queryResult to set
	 */
	public void setQueryResult(QueryResult queryResult) {
		this.queryResult = queryResult;
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
	 * @param message the message to set
	 */
	public void setMessage(Message message) {
		this.message = message;
	}
	/**
	 * @return the message
	 */
	public Message getMessage() {
		return message;
	}
	
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	public String getVerifyCode() {
		return verifyCode;
	}
	
	
	
	
	
}
