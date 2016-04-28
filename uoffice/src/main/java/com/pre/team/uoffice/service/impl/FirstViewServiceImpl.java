package com.pre.team.uoffice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pre.team.uoffice.action.CreateVerificationAction;
import com.pre.team.uoffice.dao.LocationDao;
import com.pre.team.uoffice.dao.OfficeDao;
import com.pre.team.uoffice.domain.Office;
import com.pre.team.uoffice.service.FirstViewService;

/**
 * 
 * Description:
 * @author zs1314zt
 *
 */
@Service
@Transactional
public class FirstViewServiceImpl implements FirstViewService{

	private Logger LOG = Logger.getLogger(FirstViewServiceImpl.class);
	
	@Autowired
	private OfficeDao officeDao;
	
	@Autowired
	private LocationDao locationDao;
	
	@Override
	public List<Office> getMoreViewOffice() {
		List<Office> list = new ArrayList<Office>();
		for(int i = 1; i <= 5 ; i++){
			String sql = " SELECT * FROM t_office WHERE office_advert = '0"+i+"' ORDER BY publish_time DESC LIMIT 1 ";
			Office uoffice = null;
			try {
				uoffice = officeDao.getModelOffice(sql);
			} catch (Exception e) {
				LOG.error("每周推荐查询出错！");
				continue;
			}
			if(uoffice != null){
				list.add(uoffice);	
			}
		}
		return list;
	}



}
