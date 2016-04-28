/**
 * 
 */
package com.pre.team.uoffice.domain;

/**
 * Description:验证存储实体类
 * @author xuejiahao  2015年7月13日
 * 
 */
public class Verification {
	/**
	 * 验证码
	 */
	private String verificationCode;
	/**
	 * 验证码生成时间
	 */
	private long time;
	
	public Verification(){
		
	}
	
	public Verification(String verificationCode,long time){
		this.verificationCode = verificationCode;
		this.time = time;
	}
	/**
	 * @return the verificationCode
	 */
	public String getVerificationCode() {
		return verificationCode;
	}
	/**
	 * @param verificationCode the verificationCode to set
	 */
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}
	
	
}
