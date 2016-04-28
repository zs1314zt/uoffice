/**
 * 
 */
package com.pre.team.uoffice.thread;

import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;

import com.pre.team.uoffice.domain.Verification;
import com.pre.team.uoffice.util.VerificationCodeUtil;

/**
 * Description:验证码10分钟失效线程处理
 * @author xuejiahao  2015年7月13日
 * 
 */
public class VerificationCodeThread implements Runnable{
	private Logger LOG = Logger.getLogger(VerificationCodeThread.class);
	@Override
	public void run() {
		LOG.info("开启验证码10分钟失效线程");
		while(true){
			process();
		}
	}
	/**
	 * 
	 * Description:移除过期的验证码  2015年7月13日
	 * 
	 * @author xuejiahao
	 */
	private void process(){
		Iterator<Map.Entry<String, Verification>> iter = VerificationCodeUtil.map.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry<String, Verification> entry= iter.next();
			//超出10分钟,移除验证码
			if(System.currentTimeMillis()-entry.getValue().getTime()>10*60*1000){
				iter.remove();
			}
		}
	}
	
}
