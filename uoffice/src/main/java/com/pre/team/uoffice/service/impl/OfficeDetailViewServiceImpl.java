/**
 * 
 */
package com.pre.team.uoffice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pre.team.uoffice.dao.LocationDao;
import com.pre.team.uoffice.dao.OfficeDao;
import com.pre.team.uoffice.domain.Office;
import com.pre.team.uoffice.domain.OfficeDetail;
import com.pre.team.uoffice.num.OfficeTypeEnum;
import com.pre.team.uoffice.service.OfficeDetailViewService;
import com.pre.team.uoffice.util.SearchUtil;
import com.pre.team.uoffice.util.StringUtil;

/**
 * 
 * Description:
 * @author xuejiahao  2015年6月28日
 *
 */
@Service
@Transactional
public class OfficeDetailViewServiceImpl implements OfficeDetailViewService {

	@Autowired
	private OfficeDao officeDao;
	@Autowired
	private LocationDao locationDao;
	
	@Override
	public OfficeDetail getOfficeDetailInfoById(Integer officeId) {
		Office office = officeDao.getOfficeById(officeId);
		return officeChangeToOfficeDetail(office);
	}
	/**
	 * 
	 * Description:将Office对象转换为页面显示的OfficeDetail对象  2015年8月3日
	 * 
	 * @author xuejiahao
	 * @param office
	 * @return
	 */
	private OfficeDetail officeChangeToOfficeDetail(Office office){
		if(office==null){
			return null;
		}
		OfficeDetail officeDetail = new OfficeDetail();
		officeDetail.setAdvertPhotoUrl(office.getAdvertPhotoUrl());
		officeDetail.setBannerUrl(office.getBannerUrl());
		officeDetail.setDetailAddr(office.getDetailAddr());
		officeDetail.setEachPrice(office.getEachPrice());
		officeDetail.setOfficeAdvert(office.getOfficeAdvert());
		officeDetail.setOfficeContact(office.getOfficeContact());
		officeDetail.setOfficeContent(office.getOfficeContent());
		officeDetail.setOfficeDesc(office.getOfficeDesc());
		officeDetail.setOfficeId(office.getOfficeId());
		officeDetail.setOfficeMoney(office.getOfficeMoney());
		officeDetail.setOfficeName(office.getOfficeName());
		officeDetail.setOfficeArea(office.getOfficeArea());
		officeDetail.setOfficeSize(office.getOfficeSize());
		String officeType = null;
		if(OfficeTypeEnum.OfficeTypeB.getCode().equals(office.getOfficeType())){
			officeType = OfficeTypeEnum.OfficeTypeB.getValue();
		}else if(OfficeTypeEnum.OfficeTypeC.getCode().equals(office.getOfficeType())){
			officeType = OfficeTypeEnum.OfficeTypeC.getValue();
		}else if(OfficeTypeEnum.OfficeTypeD.getCode().equals(office.getOfficeType())){
			officeType = OfficeTypeEnum.OfficeTypeD.getValue();
		}else if(OfficeTypeEnum.OfficeTypeE.getCode().equals(office.getOfficeType())){
			officeType = OfficeTypeEnum.OfficeTypeE.getValue();
		}
		officeDetail.setOfficeType(officeType);
		officeDetail.setPublishTime(office.getPublishTime());
		officeDetail.setRentDate(office.getRentDate());
		officeDetail.setViewCount(office.getViewCount());
		officeDetail.setMapDesc(office.getMapDesc());
		officeDetail.setMapUrl(office.getMapUrl());
		//设置区域地址
		String locationCode = office.getLocationId();
		officeDetail.setLocationId(locationCode);
		officeDetail.setLocationAddress(locationDao.getOfficeLocation(locationCode.substring(0,locationCode.length()-2)).getLocationName()+"-"+
				locationDao.getOfficeLocation(locationCode).getLocationName());
		//设置照片url集合
		officeDetail.setPhotoUrl(StringUtil.getURLsBysplitPhotoURL(office.getPhotoUrl()));
		return officeDetail;
	}
	@Override
	public List<Office> getOffices(int officeMoney, String locationId,int officeId) {
		String hql = "from Office off where (off.officeMoney between "+0.8*officeMoney+" and "+1.2*officeMoney+") and off.locationId = '"+locationId +"' and off.officeId <> "+officeId+" order by rand()";
		return SearchUtil.getOfficeListBySpiltPhotoURL(officeDao.getOfficeListRandom(hql));
	}
}
