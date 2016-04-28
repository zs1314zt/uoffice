/**
 * 
 */
package com.pre.team.uoffice.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.dao.SystemCodeDao;
import com.pre.team.uoffice.domain.SystemCode;

/**
 * Description:系统参数操作dao实现类
 * @author xuejiahao  2015年7月21日
 * 
 */
@Repository
public class SystemCodeDaoImpl extends HibernateBaseDao<SystemCode> implements SystemCodeDao {

	@Override
	public List<SystemCode> getSystemCode() {
		String hql = "from SystemCode sys where sys.parameterCode = ? or sys.parameterCode = ? "
				+ " or sys.parameterCode = ? ";
		List<SystemCode> sysList = find(hql, 
				new Object[]{Constants.USER_TOTAL, Constants.OFFICE_TOTAL, Constants.SUCCESS_TOTAL});
		return sysList;
	}
}
