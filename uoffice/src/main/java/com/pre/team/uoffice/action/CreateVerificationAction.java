/**
 * 
 */
package com.pre.team.uoffice.action;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.domain.SimpleMail;
import com.pre.team.uoffice.mail.MailSender;
import com.pre.team.uoffice.service.UserService;
import com.pre.team.uoffice.util.RegexValidateUtil;
import com.pre.team.uoffice.util.VerificationCodeEncoder;
import com.pre.team.uoffice.util.VerificationCodeUtil;

/**
 * Description:获取验证码
 * @author xuejiahao  2015年7月13日
 * 
 */
@ParentPackage("json-default") 
public class CreateVerificationAction extends ActionSupport{
	private Logger LOG = Logger.getLogger(CreateVerificationAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UserService userService;
	/**
	 * 手机号码
	 */
	private String loginName;
	private String type;
	
	private Map<String,Object> map;
	/**
	 * 
	 * Description:获取验证码  2015年7月15日
	 * 
	 * @author xuejiahao
	 * @return
	 * @throws Exception
	 */
	@Action(value = "createVerificationCode", results = { @Result(name = "success", type="json",params={"root","map"})})
	public String createVerification()throws Exception{
		map = new HashMap<String,Object>();
		if(loginName!=null&&type!=null){
			//邮箱
			if(RegexValidateUtil.checkEmail(loginName)){
				//邮箱已存在
				if(!userService.judgeEmailIsExist(loginName)){
					//注册
					if(Constants.REGISTER_TYPE.equals(type)){
						map.put("message", "该邮箱已被注册");
						map.put("code", Constants.EMAIL_IS_REGISTER_CODE);
						return SUCCESS;
					}else if(Constants.FORGET_PWD_AND_QUICK_LOGIN.equals(type)){
						//找回密码
						sendMailAndAddVerificationCode(loginName,Constants.VERIFICATE_FINDPWD);
					}
					
				}else{//邮箱不存在
					//忘记密码或快速登录
					if(Constants.FORGET_PWD_AND_QUICK_LOGIN.equals(type)){
						map.put("message", "该邮箱尚未注册，请先注册");
						map.put("code", Constants.EMAIL_NOT_REGISTER_CODE);
						return SUCCESS;
					}else if(Constants.REGISTER_TYPE.equals(type)){
						//注册
						sendMailAndAddVerificationCode(loginName,Constants.VERIFICATE_REGISTER);
					}
				}
//				VerificationCodeUtil.addVerificationCode(loginName, VerificationCodeEncoder.buildVerificationCode());
				map.put("message", "成功获取验证码");
				map.put("code", Constants.OPERATE_SUCCESS_CODE);
				return SUCCESS;
			}
			//手机号
			if(RegexValidateUtil.checkMobileNumber(loginName)){
				//手机号码已存在
				if(!userService.judgePhoneIsExist(loginName)){
					//注册
					if(Constants.REGISTER_TYPE.equals(type)){
						map.put("message", "该手机号码已被注册");
						map.put("code", Constants.PHONE_IS_REGISTER_CODE);
						return SUCCESS;
					}else if(Constants.FORGET_PWD_AND_QUICK_LOGIN.equals(type)){
						//找回密码
						sendMailAndAddVerificationCode(loginName,Constants.VERIFICATE_FINDPWD);
					}
				}else{//手机号码不存在
					//忘记密码或快速登录
					if(Constants.FORGET_PWD_AND_QUICK_LOGIN.equals(type)){
						map.put("message", "该手机号码尚未注册，请先注册");
						map.put("code", Constants.PHONE_NOT_REGISTER_CODE);
						return SUCCESS;
					}else if(Constants.REGISTER_TYPE.equals(type)){
						//注册
						sendMailAndAddVerificationCode(loginName,Constants.VERIFICATE_REGISTER);
					}
				}
//				VerificationCodeUtil.addVerificationCode(loginName, VerificationCodeEncoder.buildVerificationCode());
				map.put("message", "成功获取验证码");
				map.put("code", Constants.OPERATE_SUCCESS_CODE);
				return SUCCESS;
			}
		}
		return NONE;
	}




	/**
	 * @return the map
	 */
	public Map<String,Object> getMap() {
		return map;
	}

	/**
	 * @param map the map to set
	 */
	public void setMap(Map<String,Object> map) {
		this.map = map;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}


	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * 
	 * Description:发送邮件并添加验证码  2015年7月17日
	 * 
	 * @author xuejiahao
	 * @param mailAddress
	 * @throws AddressException
	 * @throws MessagingException
	 */
	private static void sendMailAndAddVerificationCode(String mailAddress,int type) throws AddressException, MessagingException{
		String verificationCode = VerificationCodeEncoder.buildVerificationCode();
		new MailSender().send(mailAddress, new SimpleMail(verificationCode,type));
		VerificationCodeUtil.addVerificationCode(mailAddress, verificationCode);
	}
}
