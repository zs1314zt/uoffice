/**
 * 
 */
package com.pre.team.uoffice.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.pre.team.uoffice.dao.MessageDao;
import com.pre.team.uoffice.domain.Message;
import com.pre.team.uoffice.domain.QueryResult;

/**
 * Description:留言板表操作dao实现类
 * @author xuejiahao  2015年6月28日
 * 
 */
@Repository
public class MessageDaoImpl extends HibernateBaseDao<Message> 
	implements MessageDao {

	@Override
	public QueryResult getMessageListByOfficeId(int officeId, int page,
			int pagesize) {
		String hql = "select mes.messContent,mes.messTime,user.userName from Message mes,User user where mes.userId = user.userId and mes.officeId = "+officeId+" order by mes.messTime asc";
		Query query = createQuery(hql);
		if(pagesize!=0&&page!=0){
			query.setFirstResult((page-1)*pagesize);
			query.setMaxResults(pagesize);
		}
		List lists= query.list();
		QueryResult queryResult = new QueryResult();
		List<Message> messageList = new ArrayList<Message>();
		for(int i =0;i<lists.size();i++){
			Object[] obj = (Object[]) lists.get(i);
			Message mes = new Message();
			mes.setMessContent((String)obj[0]);
			mes.setMessTime((Date)obj[1]);
			mes.setUserName((String)obj[2]);
			messageList.add(mes);
		}
		queryResult.setMessageList(messageList);
		queryResult.setTotal(getTotal(hql));
		queryResult.calcuateTotalPages(pagesize);
		return queryResult;
	}
	/**
	 * 
	 * Description:获取总数  2015年8月3日
	 * 
	 * @author xuejiahao
	 * @param hql
	 * @return
	 */
	public int getTotal(String hql) {
		int total = 0;
		List<Message> list = getSession().createQuery(hql).list();
		if(list!=null){
			total = list.size();
		}
		return total;
	}
	@Override
	public boolean saveMessage(Message message) {
		if(save(message)!=null){
			return true;
		}
		return false;
	}
}
