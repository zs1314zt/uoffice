/**
 * 
 */
package com.pre.team.uoffice.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description:验证
 * @author xuejiahao  2015年7月16日
 * 
 */
public class RegexValidateUtil {
	/**
	 * 
	 * Description:邮箱验证  2015年7月16日
	 * 
	 * @author xuejiahao
	 * @param email
	 * @return
	 */
    public static boolean checkEmail(String email){
        boolean flag = false;
        try{
                String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
                Pattern regex = Pattern.compile(check);
                Matcher matcher = regex.matcher(email);
                flag = matcher.matches();
            }catch(Exception e){
                flag = false;
            }
        return flag;
    }
     
    /**
     * 
     * Description:手机号验证  2015年7月16日
     * 
     * @author xuejiahao
     * @param mobileNumber
     * @return
     */
    public static boolean checkMobileNumber(String mobileNumber){
        boolean flag = false;
        try{
                Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
                Matcher matcher = regex.matcher(mobileNumber);
                flag = matcher.matches();
            }catch(Exception e){
                flag = false;
            }
        return flag;
    }
}
