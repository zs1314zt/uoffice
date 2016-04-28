package com.pre.team.uoffice.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式化工具类 
 */
public class DateFormatUtils {
	public static SimpleDateFormat getDateFormate() {
		return new SimpleDateFormat("yyyy-MM-dd");
	}

	public static SimpleDateFormat getTimeStampFormate() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	public static String format(String format, Date date) {
		SimpleDateFormat formater = new SimpleDateFormat(format);
		return formater.format(date);
	}
	
	public static String formatDate(Date date) {
		return getDateFormate().format(date);
	}

	public static String formatTimeStamp(Date date) {
		return getTimeStampFormate().format(date);
	}
}
