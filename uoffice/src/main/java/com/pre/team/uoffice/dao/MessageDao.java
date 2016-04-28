/**
 * 
 */
package com.pre.team.uoffice.dao;

import com.pre.team.uoffice.domain.Message;
import com.pre.team.uoffice.domain.QueryResult;

/**
 * Description:留言板表操作dao
 * @author xuejiahao  2015年6月28日
 * 
 */
public interface MessageDao {
	/**
	 * 
	 * Description:根据办公室Id获取留言分页信息  2015年8月3日
	 * 
	 * @author xuejiahao
	 * @param officeId
	 * @param page
	 * @param pagesize
	 * @return
	 */
	public QueryResult getMessageListByOfficeId(int officeId,int page,int pagesize);
	/**
	 * 
	 * Description:保存message  2015年8月3日
	 * 
	 * @author xuejiahao
	 * @param message
	 * @return
	 */
	public boolean saveMessage(Message message);
}
