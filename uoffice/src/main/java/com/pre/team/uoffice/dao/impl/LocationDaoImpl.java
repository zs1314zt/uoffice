/**
 * 
 */
package com.pre.team.uoffice.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.dao.LocationDao;
import com.pre.team.uoffice.domain.OfficeLocation;

/**
 * Description:区域表操作Dao实现类
 * @author xuejiahao  2015年6月28日
 * 
 */
@Repository
public class LocationDaoImpl extends HibernateBaseDao<OfficeLocation> 
	implements LocationDao {

	@Override
	public List<OfficeLocation> getCity(String location_code){
		List<OfficeLocation> list = new ArrayList<OfficeLocation>();
		String hql = "from OfficeLocation loc where loc.locationCode like '"+location_code+"__'";
		List<OfficeLocation> locList = find(hql);
		Iterator<OfficeLocation> it = locList.iterator();
		while(it.hasNext()){
			list.add((OfficeLocation) it.next());
		}
		return list;
	}

	@Override
	public List<OfficeLocation> getArea(String location_code) {
		List<OfficeLocation> list = new ArrayList<OfficeLocation>();
		String hql = "from OfficeLocation loc where loc.locationCode like '"+location_code+"__'";
		List<OfficeLocation> locList = find(hql);
		Iterator<OfficeLocation> it = locList.iterator();
		while(it.hasNext()){
			list.add((OfficeLocation) it.next());
		}
		return list;
	}

	@Override
	public List<OfficeLocation> getTown(String location_code) {
		List<OfficeLocation> list = new ArrayList<OfficeLocation>();
		String hql = null;
		if(location_code.length()==Constants.TOWN_LENGTH){
			hql = "from OfficeLocation loc where loc.locationCode like '"+location_code+"'";
		}
		hql = "from OfficeLocation loc where loc.locationCode like '"+location_code+"__'";
		List<OfficeLocation> locList = find(hql);
		Iterator<OfficeLocation> it = locList.iterator();
		while(it.hasNext()){
			list.add((OfficeLocation) it.next());
		}
		return list;
	}

	@Override
	public List<OfficeLocation> getProvince() {
		List<OfficeLocation> list = new ArrayList<OfficeLocation>();
		String hql = "from OfficeLocation loc where loc.locationCode like '__'";
		List<OfficeLocation> locList = find(hql);
		Iterator<OfficeLocation> it = locList.iterator();
		while(it.hasNext()){
			list.add((OfficeLocation) it.next());
		}
		return list;
	}

	@Override
	public OfficeLocation getOfficeLocation(String location_code) {
		String hql = "from OfficeLocation loc where loc.locationCode = '"+location_code+"'";
		List<OfficeLocation> locList = find(hql);
		if(locList!=null){
			return locList.get(0);
		}
		return null;
	}

	@Override
	public List<OfficeLocation> getAllCity() {
		String hql = "from OfficeLocation loc where loc.locationCode like '____'";
		return (List<OfficeLocation>)find(hql);
	}

}
