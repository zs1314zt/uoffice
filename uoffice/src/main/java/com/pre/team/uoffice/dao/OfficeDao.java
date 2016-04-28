/**
 * 
 */
package com.pre.team.uoffice.dao;

import java.util.List;
import com.pre.team.uoffice.domain.Office;
import com.pre.team.uoffice.domain.QueryResult;

/**
 * Description:办公室表操作dao
 * @author xuejiahao  2015年6月28日
 * 
 */
public interface OfficeDao {

	public Office getOfficeById(int officeId);
	
	public Office getModelOffice(String sql);
	
	public List<Office> getMoreViewOffice();
	/**
	 * 
	 * Description:保存Office  2015年7月15日
	 * 
	 * @author xuejiahao
	 * @param office
	 * @return
	 */
	public boolean saveOffice(Office office);
	/**
	 * 
	 * Description:修改office  2015年7月15日
	 * 
	 * @author xuejiahao
	 * @param office
	 * @return
	 */
	public boolean updateOffice(Office office);
	/**
	 * 
	 * Description:删除office信息  2015年7月15日
	 * 
	 * @author xuejiahao
	 * @param officeId 办公室编号
	 * @return
	 */
	public boolean deleteOffice(Integer officeId);
	
	/**
	 * @author zs1314zt
	 * @param officeType 办公室类型
	 * @return 某一个类型的办公室列表
	 */
	public List<Office> getOfficeList(String officeType);
	/**
	 * 
	 * Description:查询分页显示  2015年7月18日
	 * 
	 * @author xuejiahao
	 * @param hql
	 * @param page
	 * @param pagesize
	 * @return
	 */
	public QueryResult getOfficeListBySearch(String hql,int page,int pagesize);
	/**
	 * 
	 * Description:获取总记录数  2015年7月18日
	 * 
	 * @author xuejiahao
	 * @param hql
	 * @return
	 */
	public int getTotal(String hql);
	/**
	 * 
	 * Description:随意获取n条记录  2015年8月3日
	 * 
	 * @author xuejiahao
	 * @param hql
	 * @return
	 */
	public List<Office> getOfficeListRandom(String hql);
	/**
	 * 
	 * Description:清空办公室广告位信息  2015年8月11日
	 * 
	 * @author xuejiahao
	 * @param officeAdvert
	 */
	public void updateOfficeByAdvert(String officeAdvert);

}
