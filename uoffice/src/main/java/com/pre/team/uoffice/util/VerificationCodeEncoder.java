/**
 * 
 */
package com.pre.team.uoffice.util;

import java.util.Random;

/**
 * Description:验证码编码器
 * @author xuejiahao  2015年7月13日
 * 
 */
public class VerificationCodeEncoder {
	/**
	 * 
	 * Description:动态生成6位随机数字字符串  2015年7月13日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	public static String buildVerificationCode(){
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		for(int i = 0;i<6;i++){
			buffer.append(StringUtil.toString(random.nextInt(10)));
		}
		return new String(buffer);
	}
}
