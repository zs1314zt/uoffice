package com.pre.team.uoffice.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * Description:字符串工具类
 * @author xuejiahao  2015年7月11日
 *
 */
public class StringUtil {

	/**
	 * 
	 * Description:判断是否为空  2015年8月30日
	 * 
	 * @author xuejiahao
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return str == null || "".equals(str.trim());
	}


	/**
	 * 
	 * Description:返回对象字符串  2015年8月30日
	 * 
	 * @author xuejiahao
	 * @param obj
	 * @return
	 */
	public static String toString(Object obj) {
		try {
			return obj == null ? "" : obj.toString();
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 
	 * Description:是否包含  2015年8月30日
	 * 
	 * @author xuejiahao
	 * @param str
	 * @param bStr
	 * @return
	 */
	public static boolean contains(String str, String bStr) {
		boolean flag = true;
		if (!isBlank(str)) {
			if (!str.toLowerCase().contains(bStr.toLowerCase())) {
				flag = false;
			}
		} else {
			flag = false;
		}
		return flag;
	}
	/**
	 * 
	 * Description:保留小数点后n位  2015年8月30日
	 * 
	 * @author xuejiahao
	 * @param num
	 * @param digit
	 * @return
	 */
	public static String getCashDoubleNumStr(double num, int digit) {
		StringBuffer digitStr = new StringBuffer("0");
		if (digit > 0) {
			digitStr.append(".");
			for (int i = 0; i < digit; i++) {
				digitStr.append("0");
			}
		}
		DecimalFormat cashDf = new DecimalFormat(digitStr.toString());
		return cashDf.format(num);
	}
	
	/**
	 * 
	 * Description:合并数组  2015年8月30日
	 * 
	 * @author xuejiahao
	 * @param arr1
	 * @param arr2
	 * @return
	 */
	public static String[] union(String[] arr1, String[] arr2)
	{
	  Set<String> set = new HashSet<String>();
	  for (String str : arr1)
	  {
	    set.add(str);
	  }
	  for (String str : arr2)
	  {
	    set.add(str);
	  }
	  String[] result = {};
	  return set.toArray(result);
	}
	/**
	 * 
	 * Description:如果包含多张图片获取photo第一张URL  2015年7月20日
	 * 
	 * @author xuejiahao
	 * @param str
	 * @return
	 */
	public static String spiltPhotoURL(String str){
		if(str==null){
			return null;
		}
		if(str.contains(";")){
			String strs[] = str.split(";");
			return strs[0] + ";";
		}
		return str;
	}
	/**
	 * 
	 * Description:分割photoUrl为list  2015年8月3日
	 * 
	 * @author xuejiahao
	 * @param photoUrl
	 * @return
	 */
	public static List<String> getURLsBysplitPhotoURL(String photoUrl){
		List<String> list = new ArrayList<String>();
		String[] urls = photoUrl.split(";");
		list = Arrays.asList(urls);
		return list;
	}
}
