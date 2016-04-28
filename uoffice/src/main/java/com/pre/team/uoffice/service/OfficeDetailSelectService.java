package com.pre.team.uoffice.service;

import com.pre.team.uoffice.domain.QueryResult;

/**
 * @author xuejiahao
 * 办公室详细筛选页面
 * */
public interface OfficeDetailSelectService {
	
	public QueryResult Search(String location_code,String rentDateType,String rentMoney,String CompanySize,String officeType,int page,int pagesize);
}
