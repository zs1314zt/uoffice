/**
 * 
 */
package com.pre.team.uoffice.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.util.SessionUtil;
import com.pre.team.uoffice.util.StringUtil;
import com.pre.team.uoffice.util.VerificationCodeUtil;

/**
 * Description:
 * @author xuejiahao  2015年8月30日
 * 
 */
@ParentPackage("json-default")
public class CreateVerificationImage extends ActionSupport{
	private Logger LOG = Logger.getLogger(CreateVerificationImage.class);
	
	private Map<String,Object> map;
	/**
	 * 
	 * Description:获取二进制图片验证码  2015年8月30日
	 * 
	 * @author xuejiahao
	 * @return
	 */
	@Action(value = "getVeriryCodeImage", results = { @Result(name = "success", type="json",params={"root","map"})})
	public String getVeriryCodeImage(){
		map = new HashMap<String,Object>();
		//获取验证码
		String verifyCode = VerificationCodeUtil.creatVerifyCode(Constants.VERIFYCODEIMAGE_LENGTH);
		//获取二进制验证码
		byte[] verifyCodeByte = VerificationCodeUtil.byteVerifyCode(verifyCode);
		//将二进制验证码转换为BASE64编码
		String verifyCodeStr = new sun.misc.BASE64Encoder().encodeBuffer(verifyCodeByte);
		map.put("verifyCodeStr", verifyCodeStr);
		SessionUtil.addCurrentMessageCodeToSession(verifyCode);
		return SUCCESS;
	}
	
	public Map<String, Object> getMap() {
		return map;
	}
	
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
}
