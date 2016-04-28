package com.pre.team.uoffice.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * 
 * Description:MD5加密工具类
 * @author xuejiahao  2015年7月12日
 *
 */
public class Md5Util {
	/**
	 * 
	 * Description:MD5加密  2015年7月12日
	 * 
	 * @author xuejiahao
	 * @param value
	 * @return
	 */
	public static String md5(String value) {
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] e = md.digest(value.getBytes());
			return toHex(e);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return value;
		}
	}
	/**
	 * 
	 * Description:MD5加密  2015年7月12日
	 * 
	 * @author xuejiahao
	 * @param bytes
	 * @return
	 */
	private static String toHex(byte bytes[]) {
		StringBuilder hs = new StringBuilder();
		String stmp = "";
		for (int n = 0; n < bytes.length; n++) {
			stmp = Integer.toHexString(bytes[n] & 0xff);
			if (stmp.length() == 1) {
				hs.append("0").append(stmp);
			} else {
				hs.append(stmp);
			}
		}
		return hs.toString();
	}
}
