package com.pre.team.uoffice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pre.team.uoffice.dao.VisitDao;
import com.pre.team.uoffice.domain.Visit;
import com.pre.team.uoffice.service.VisitService;
@Service
@Transactional
public class VisitServiceImpl implements VisitService{
	
	@Autowired
	private VisitDao visitDao;
	@Override
	public void saveVisit(Visit visit) {
		// TODO Auto-generated method stub
		visitDao.saveVisit(visit);
	}
	/**
	 * @return the visitDao
	 */
	public VisitDao getVisitDao() {
		return visitDao;
	}
	/**
	 * @param visitDao the visitDao to set
	 */
	public void setVisitDao(VisitDao visitDao) {
		this.visitDao = visitDao;
	}
	
}
