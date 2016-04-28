/**
 * 
 */
package com.pre.team.uoffice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.dao.LocationDao;
import com.pre.team.uoffice.domain.OfficeLocation;
import com.pre.team.uoffice.service.LocationService;

/**
 * Description:
 * @author xuejiahao  2015年7月12日
 * 
 */
@Service
@Transactional
public class LocationServiceImpl implements LocationService {
	
	@Autowired
	private LocationDao locationDao;
	
	@Override
	public List<OfficeLocation> getLocation(String location_code) {
		List<OfficeLocation> list = new ArrayList<OfficeLocation>();
		if(location_code!=null){
			//获取城市
			if(location_code.length()==Constants.PRO_LENGTH){
				list = locationDao.getCity(location_code);
			}else if(location_code.length()==Constants.CITY_LENGTH){
			//获取区
				list = locationDao.getArea(location_code);
			}else if(location_code.length()==Constants.AREA_LEGNGTH){
			//获取区域
				list = locationDao.getTown(location_code);
			}
		}else{
			list = locationDao.getProvince();
		}
		return list;
	}

	@Override
	public List<OfficeLocation> getAllCity() {
		return locationDao.getAllCity();
	}
	
	

}
