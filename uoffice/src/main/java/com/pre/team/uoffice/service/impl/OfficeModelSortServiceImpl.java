/**
 * 
 */
package com.pre.team.uoffice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pre.team.uoffice.dao.OfficeDao;
import com.pre.team.uoffice.domain.Office;
import com.pre.team.uoffice.service.OfficeModelSortService;

/**
 * 
 * Description:
 * @author xuejiahao  2015年6月28日
 *
 */
@Service
@Transactional
public class OfficeModelSortServiceImpl implements OfficeModelSortService {

	@Autowired
	private OfficeDao officeDao;
	
	@Override
	public List<Office> getOfficeByType(String officeType) {
		return officeDao.getOfficeList(officeType);
	}
	
}
