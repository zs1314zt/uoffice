/**
 * 
 */
package com.pre.team.uoffice.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.pre.team.uoffice.constants.Constants;

/**
 * Description:
 * @author xuejiahao  2015年7月18日
 * 
 */
public class MailConfigureUtil {
	private static Logger LOG = Logger.getLogger(MailConfigureUtil.class);
	/**
	 * 
	 * Description:读取mail配置文件  2015年7月18日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	public static  Properties init(){
		Properties props = new Properties();
		InputStream fis;
		try{
			fis = new MailConfigureUtil().getClass().getResourceAsStream(Constants.MAIL_PATH_NAME);// 属性文件输入流  
		    props.load(fis);
		    Constants.MAIl_SMTP_ADDRESS = props.getProperty("mail.host").trim();
		    Constants.MAIL_USERNAME = props.getProperty("mail.username").trim();
		    Constants.MAIL_PASSWORD = props.getProperty("mail.password").trim();
		}catch(Exception e){
			LOG.info("读取mail配置文件出错"+e);
		}
		return props;
	}
}
