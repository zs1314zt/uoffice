package com.pre.team.uoffice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pre.team.uoffice.dao.OfficeDao;
import com.pre.team.uoffice.domain.QueryResult;
import com.pre.team.uoffice.service.OfficeDetailSelectService;
import com.pre.team.uoffice.util.SearchUtil;

/**
 * 
 * Description:
 * @author xuejiahao  2015年6月28日
 *
 */
@Service
@Transactional
public class OfficeDetailSelectServiceImpl implements OfficeDetailSelectService {
	
	@Autowired
	private OfficeDao officeDao;
	@Override
	public QueryResult Search(String location_code, String rentDateType,
			String rentMoney, String CompanySize,String officeType, int page, int pagesize) {
		String hql = SearchUtil.createSearchHQL(location_code, rentDateType, rentMoney, CompanySize,officeType);
		return officeDao.getOfficeListBySearch(hql, page, pagesize);
	}


}
