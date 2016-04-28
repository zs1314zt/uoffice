/**
 * 
 */
package com.pre.team.uoffice.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.dao.OfficeDao;
import com.pre.team.uoffice.domain.Office;
import com.pre.team.uoffice.service.ModifyOfficeService;

/**
 * Description:
 * @author xuejiahao  2015年6月28日
 * 
 */
@Service
@Transactional
public class ModifyOfficeServiceImpl implements ModifyOfficeService {
	
	@Autowired
	private OfficeDao officeDao;
	

	@Override
	public Map<String, Object> addOfficeInfo(Office office) {
		Map<String,Object> map = new HashMap<String,Object>();
		//保存成功
		office.setPublishTime(new Date());
/*		String photoUrl = office.getPhotoUrl();
		if(photoUrl!=null){
			office.setPhotoUrl(photoUrl.substring(0,photoUrl.length()-1));
		}*/
		//判断是否已存在该广告位的办公室信息，若存在，清除广告位信息
		if(!"00".equals(office.getOfficeAdvert())){
			officeDao.updateOfficeByAdvert(office.getOfficeAdvert());
		}
		if(officeDao.saveOffice(office)){
			map.put("message", "发布成功");
			map.put("code", Constants.OPERATE_SUCCESS_CODE);
		}else{
			map.put("message", "发布失败");
			map.put("code", Constants.OPERATE_FAIL_CODE);
		}
		return map;
	}

	@Override
	public Map<String, Object> updateOfficeInfo(Office office) {
		Map<String,Object> map = new HashMap<String,Object>();
		//修改成功
		if(officeDao.updateOffice(office)){
			map.put("message", "修改成功");
			map.put("code", Constants.OPERATE_SUCCESS_CODE);
		}else{
			map.put("message", "修改失败，请重试");
			map.put("code", Constants.OPERATE_FAIL_CODE);
		}
		return map;
	}

	@Override
	public Map<String, Object> deleteOfficeInfo(Integer officeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(officeDao.deleteOffice(officeId)){
			//删除成功
			map.put("message", "删除成功");
			map.put("code",Constants.OPERATE_SUCCESS_CODE);
		}else{
			map.put("message", "删除失败，请重试");
			map.put("code",Constants.OPERATE_FAIL_CODE);
		}
		return map;
	}

	@Override
	public Office getOfficeByOfficeId(Integer officeId) {
		
		return officeDao.getOfficeById(officeId);
	}

	
}
