/**
 * 
 */
package com.pre.team.uoffice.util;

import java.util.ArrayList;
import java.util.List;

import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.domain.Office;

/**
 * Description:搜索工具类
 * @author xuejiahao  2015年7月18日
 * 
 */
public class SearchUtil {
	
	/**
	 * 
	 * Description:生成短租期查询条件  2015年7月18日
	 * 
	 * @author xuejiahao
	 * @param rentDateType
	 * @return
	 */
	private static String rentDateReturnSQL(String rentDateType){
		String str = "";
	    if(Constants.RENT_DATE_TYPE_1_2.equals(rentDateType)){
			str = "off.rentDate between 1 and 2";
		}else if(Constants.RENT_DATE_TYPE_3_5.equals(rentDateType)){
			str = "off.rentDate between 3 and 5";
		}else if(Constants.RENT_DATE_TYPE_6_11.equals(rentDateType)){
			str = "off.rentDate between 6 and 11";
		}else if(Constants.RENT_DATE_TYPE_12.equals(rentDateType)){
			str = "off.rentDate >= 12";
		}
		return str;
	}
	/**
	 * 
	 * Description:获取查询区域sql  2015年7月18日
	 * 
	 * @author xuejiahao
	 * @param location_code
	 * @return
	 */
	private static String locationReturnSQL(String location_code){
		String str = "";
		if(location_code!=null){
			str = " off.locationId like '" + location_code + "%'";
		}
		return str;
	}
	/**
	 * 
	 * Description:生成查询公司人数sql  2015年7月18日
	 * 
	 * @author xuejiahao
	 * @param CompanySize
	 * @return
	 */
	private static String CompanySizeReturnSql(String CompanySize){
		String str ="";
		if(CompanySize!=null){
		    if(Constants.SEARCH_NONE.equals(CompanySize)){
		    	str = "";
		    }else if(Constants.COMPANY_SIZE_1_3.equals(CompanySize)){
				str = "off.officeSize between 1 and 3";
			}else if(Constants.COMPANY_SIZE_3_5.equals(CompanySize)){
				str = "off.officeSize between 3 and 5";
			}else if(Constants.COMPANY_SIZE_5_8.equals(CompanySize)){
				str = "off.officeSize between 5 and 8";
			}else if(Constants.COMPANY_SIZE_8_11.equals(CompanySize)){
				str = "off.officeSize between 8 and 11";
			}else if(Constants.COMPANY_SIZE_11_15.equals(CompanySize)){
				str = "off.officeSize between 11 and 15";
			}else if(Constants.COMPANY_SIZE_15_20.equals(CompanySize)){
				str = "off.officeSize between 15 and 20";
			}else if(Constants.COMPANY_SIZE_20.equals(CompanySize)){
				str = "off.officeSize >= 20";
			}else if(CompanySize.contains(",")){
				String[] strs = CompanySize.split(",");
				str = "off.officeSize between "+strs[0]+" and "+strs[1];
			}
		}
		return str;
	}
	/**
	 * 
	 * Description:生成查询租金sql  2015年7月18日
	 * 
	 * @author xuejiahao
	 * @param rentMoney
	 * @return
	 */
	private static String rentMoneyReturnSql(String rentMoney){
		String str = "";
		if(rentMoney!=null){
		    if(Constants.SEARCH_NONE.equals(rentMoney)){
		    	str = "";
		    }else if(Constants.RENTMONEY_0_5000.equals(rentMoney)){
				str = "off.officeMoney <5000";
			}else if(Constants.RENTMONEY_5000_10000.equals(rentMoney)){
				str = "off.officeMoney between 5000 and 10000";
			}else if(Constants.RENTMONEY_10000_15000.equals(rentMoney)){
				str = "off.officeMoney between 10000 and 15000";
			}else if(Constants.RENTMONEY_15000_20000.equals(rentMoney)){
				str = "off.officeMoney between 15000 and 20000";
			}else if(Constants.RENTMONEY_20000.equals(rentMoney)){
				str = "off.officeMoney >= 20000";
			}else if(rentMoney.contains(",")){
				String[] strs = rentMoney.split(",");
				str = "off.officeMoney between "+strs[0]+" and "+strs[1];
			}
		}
		return str;
	}
	/**
	 * 
	 * Description:查询办公室类型sql  2015年7月18日
	 * 
	 * @author xuejiahao
	 * @param officeType
	 * @return
	 */
	private static String officeTypeReturnSql(String officeType){
		String str = "";
		if(officeType!=null && !"00".equals(officeType)){
			str = "off.officeType = "+officeType;
		}
		return str;
	} 
	/**
	 * 
	 * Description:创建查询语句  2015年7月18日
	 * 
	 * @author xuejiahao
	 * @param location_code
	 * @param rentDateType
	 * @param rentMoney
	 * @param CompanySize
	 * @param page
	 * @param pagesize
	 * @return
	 */
	public synchronized static String createSearchHQL(String location_code,String rentDateType,String rentMoney,String CompanySize,String officeType){
		boolean flag = false;
		String hql = "from Office as off ";
		String hql_rentMoney = rentMoneyReturnSql(rentMoney);
		String hql_companySize = CompanySizeReturnSql(CompanySize);
		String hql_location= locationReturnSQL(location_code);
		String hql_rentDate = rentDateReturnSQL(rentDateType);
		String hql_officeType = officeTypeReturnSql(officeType);
		if(!"".equals(hql_rentMoney)){
			hql +="where "+hql_rentMoney+" ";
			flag = true;
		}
		if(!"".equals(hql_companySize)){
			if(flag){
				hql += " and "+hql_companySize+" ";
			}else{
				hql +="where "+hql_companySize+" ";
				flag = true;
			}
		}
		if(!"".equals(hql_location)){
			if(flag){
				hql += " and "+hql_location+" ";
			}else{
				hql +="where "+hql_location+" ";
				flag = true;
			}
		}
		if(!"".equals(hql_rentDate)){
			if(flag){
				hql += " and "+hql_rentDate+" ";
			}else{
				hql +="where "+hql_rentDate+" ";
				flag = true;
			}
		}
		if(!"".equals(hql_officeType)){
			if(flag){
				hql += " and "+hql_officeType+" ";
			}else{
				hql +="where "+hql_officeType+" ";
				flag = true;
			}
		}
		return hql;
	}
	/**
	 * 
	 * Description:处理office的photoURL，返回第一个URL  2015年7月20日
	 * 
	 * @author xuejiahao
	 * @param officeList
	 * @return
	 */
	public static List<Office> getOfficeListBySpiltPhotoURL(List<Office> officeList){
		List<Office> list = new ArrayList<Office>();
		for(int i =0;i<officeList.size();i++){
			Office office = officeList.get(i);
			office.setPhotoUrl(StringUtil.spiltPhotoURL(office.getPhotoUrl()));
			list.add(office);
		}
		return list;
	}
}
