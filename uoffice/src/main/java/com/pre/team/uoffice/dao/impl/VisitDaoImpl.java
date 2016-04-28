/**
 * 
 */
package com.pre.team.uoffice.dao.impl;

import org.springframework.stereotype.Repository;

import com.pre.team.uoffice.dao.VisitDao;
import com.pre.team.uoffice.domain.Visit;

/**
 * Description:访问Ip表Dao实现类
 * @author xuejiahao  2015年9月14日
 * 
 */
@Repository
public class VisitDaoImpl extends HibernateBaseDao<Visit> implements VisitDao{

	@Override
	public void saveVisit(Visit visit) {
		save(visit);
	}

}
