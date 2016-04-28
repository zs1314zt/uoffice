/**
 * 
 */
package com.pre.team.uoffice.domain;

import java.io.Serializable;

import com.pre.team.uoffice.constants.Constants;

/**
 * Description:邮件对象
 * @author xuejiahao  2015年7月17日
 * 
 */
public class SimpleMail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String subject;
	private String content;
	
	public SimpleMail(){
		
	}
	
	public SimpleMail(String verificationCode,int type){
		if(type == Constants.VERIFICATE_REGISTER){
			//注册
			this.subject = Constants.MAIL_SUBJECT_REGISTER;
			this.content = "欢迎注册needOffice，本次注册验证码："+verificationCode+"，该验证码在10分钟内有效。";
		}else if(type == Constants.VERIFICATE_FINDPWD){
			//找回密码
			this.subject = Constants.MAIL_SUBJECT_FINDPWD;
			this.content = "本次密码找回的验证码："+verificationCode+"，该验证码在10分钟内有效。";
		}
		
	}
	
	public SimpleMail(String subject,String content){
		this.subject = subject;
		this.content = content;
	}
	
	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
