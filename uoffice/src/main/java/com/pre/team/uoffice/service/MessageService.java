/**
 * 
 */
package com.pre.team.uoffice.service;

import java.util.List;
import java.util.Map;

import com.pre.team.uoffice.domain.Message;
import com.pre.team.uoffice.domain.QueryResult;

/**
 * Description:留言接口
 * @author xuejiahao  2015年8月3日
 * 
 */
public interface MessageService {
	/**
	 * 
	 * Description:根据办公室Id获取办公室留言集合  2015年8月3日
	 * 
	 * @author xuejiahao
	 * @param officeId
	 * @param page
	 * @param pagesize
	 * @return
	 */
	public QueryResult getMessagesByOfficeId(int officeId,int page,int pagesize);
	/**
	 * 
	 * Description:发布留言  2015年8月3日
	 * 
	 * @author xuejiahao
	 * @param message
	 * @param verifyCode
	 * @return
	 */
	public Map<String,Object> publishMessage(Message message,String verifyCode);
}
