/**
 * 
 */
package com.pre.team.uoffice.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pre.team.uoffice.dao.OfficeDao;
import com.pre.team.uoffice.domain.Office;
import com.pre.team.uoffice.domain.QueryResult;
import com.pre.team.uoffice.util.SearchUtil;
import com.pre.team.uoffice.util.StringUtil;

/**
 * Description:办公室表操作dao实现类
 * @author xuejiahao  2015年6月28日
 * 
 */
@Repository
public class OfficeDaoImpl extends HibernateBaseDao<Office> 
	implements OfficeDao{
	
	private Logger LOG = Logger.getLogger(OfficeDaoImpl.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 根据officeId加载办公室详细信息
	 */
	@Override
	public Office getOfficeById(int officeId) {
		return get(officeId);
	}

	@Override
	public Office getModelOffice(String sql) {
		return jdbcTemplate.queryForObject(sql.toString(), new RowMapper<Office>(){

			@Override
			public Office mapRow(ResultSet rs, int arg1) throws SQLException {
				Office office = new Office();
				office.setOfficeId(rs.getInt("office_id"));
				office.setOfficeName(rs.getString("office_name"));
				office.setOfficeAdvert(rs.getString("office_advert"));
				office.setAdvertPhotoUrl(rs.getString("advert_photoUrl"));
				office.setOfficeMoney(rs.getInt("office_money"));
				office.setEachPrice(rs.getString("each_price"));
				office.setOfficeArea(rs.getInt("office_area"));
				return office;
			}});
	}

	@Override
	public List<Office> getMoreViewOffice() {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM t_office ORDER BY view_count DESC LIMIT 2 ");
		return jdbcTemplate.query(sql.toString(), new RowMapper<Office>(){

			@Override
			public Office mapRow(ResultSet rs, int arg1) throws SQLException {
				Office office = new Office();
				office.setOfficeId(rs.getInt("office_id"));
				office.setOfficeName(rs.getString("office_name"));
				office.setOfficeDesc(rs.getString("office_desc"));
				office.setOfficeMoney(rs.getInt("office_money"));
				office.setPhotoUrl(StringUtil.spiltPhotoURL(rs.getString("photo_url")));
				return office;
			}});
	}

	@Override
	public boolean saveOffice(Office office) {
		if(save(office)!=null){
			return true;
		}
		return false;
	}

	@Override
	public boolean updateOffice(Office office) {
		boolean result = false;
		try{
			update(office);
			result = true;
		}catch(Exception e){
			LOG.info("更新office出错:"+e);
			result = false;
		}
		return result;
	}

	@Override
	public boolean deleteOffice(Integer officeId) {
		boolean result = false;
		try{
			remove(new Office(officeId));
			result = true;
		}catch(Exception e){
			result = false;
			LOG.info("删除办公室信息出错:"+e);
		}
		return result;
	}


	@Override
	public List<Office> getOfficeList(String officeType) {
		String hql="from Office off where off.officeType = ? order by off.publishTime desc ";
		Query query = createQuery(hql, new Object[]{officeType});
		query.setFirstResult(0);
		query.setMaxResults(10);
		List<Office> officeList = (List<Office>)query.list();
		return SearchUtil.getOfficeListBySpiltPhotoURL(officeList);
	}

	@Override
	public QueryResult getOfficeListBySearch(final String hql,final int page,
			final int pagesize) {
//		QueryResult queryResult = getHibernateTemplate().execute(new HibernateCallback<QueryResult>() {
//
//			@Override
//			public QueryResult doInHibernate(Session session) throws HibernateException,
//					SQLException {
//				Query query = session.createQuery(hql).setFirstResult((page-1)*pagesize).setMaxResults(pagesize);
//				QueryResult result = new QueryResult((List<Office>)query.list(),getTotal(hql));
//				result.calcuateTotalPages(pagesize);
//				return result;
//			}
//		});
		Query query = createQuery(hql);
		query.setFirstResult((page-1)*pagesize);
		query.setMaxResults(pagesize);
		QueryResult queryResult = new QueryResult((List<Office>)query.list(),getTotal(hql));
		queryResult.calcuateTotalPages(pagesize);
		return queryResult;
	}

	@Override
	public int getTotal(String hql) {
		int total = 0;
		List<Office> list = getSession().createQuery(hql).list();
		if(list!=null){
			total = list.size();
		}
		return total;
	}

	@Override
	public List<Office> getOfficeListRandom(String hql) {
		Query query = getSession().createQuery(hql);
		query.setMaxResults(5);
		return query.list();
	}

	@Override
	public void updateOfficeByAdvert(String officeAdvert) {
		String sql= "update t_office set office_advert = '00',advert_photoUrl = null where office_advert = '"+officeAdvert+"'";
		jdbcTemplate.execute(sql);
	}
	
	
	
}
