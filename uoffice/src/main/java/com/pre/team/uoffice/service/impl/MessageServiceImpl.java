/**
 * 
 */
package com.pre.team.uoffice.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.dao.MessageDao;
import com.pre.team.uoffice.domain.Message;
import com.pre.team.uoffice.domain.QueryResult;
import com.pre.team.uoffice.service.MessageService;
import com.pre.team.uoffice.util.SessionUtil;
import com.pre.team.uoffice.util.VerificationCodeUtil;

/**
 * Description:
 * @author xuejiahao  2015年8月3日
 * 
 */
@Service
@Transactional
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageDao messageDao;
	
	@Override
	public QueryResult getMessagesByOfficeId(int officeId, int page,
			int pagesize) {
		return messageDao.getMessageListByOfficeId(officeId, page, pagesize);
	}

	@Override
	public Map<String, Object> publishMessage(Message message,String verify) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(SessionUtil.getCurrentUser()==null){
			map.put("code", Constants.OPERATE_FAIL_CODE);
			map.put("message", "用户未登录，请先登录");
			return map;
		}
		//验证码为空，且发表次数为4，则返回需要验证码
		if(verify==null||"".equals(verify)){
			if(SessionUtil.getCurrentMessageSize()==Constants.PUBLISH_MESSAGE_SIZE_MAX){
				//获取验证码
				String verifyCode = VerificationCodeUtil.creatVerifyCode(Constants.VERIFYCODEIMAGE_LENGTH);
				//获取二进制验证码
				byte[] verifyCodeByte = VerificationCodeUtil.byteVerifyCode(verifyCode);
				//将二进制验证码转换为BASE64编码
				String verifyCodeStr = new sun.misc.BASE64Encoder().encodeBuffer(verifyCodeByte);
				SessionUtil.addCurrentMessageCodeToSession(verifyCode);
				map.put("code", Constants.PUBLISH_MEAASGECODE_CODE);
				map.put("verifyCodeStr", verifyCodeStr);
				map.put("message", "请输入验证码");
				return map;
			}
		}else{
			if(!verify.equalsIgnoreCase(SessionUtil.getCurrentMessageCode())){
				map.put("code", Constants.WRONG_VERIFICATIONCODE_CODE);
				map.put("message", "验证码错误");
				return map;
			}
		}
		message.setMessTime(new Date());
		message.setUserId(SessionUtil.getLoginUserId());
		if(messageDao.saveMessage(message)){
			//发布成功
			message.setUserName(SessionUtil.getCurrentUser().getUserName());
			map.put("code", Constants.OPERATE_SUCCESS_CODE);
			map.put("Message", message);
			map.put("message", "留言成功");
			if(SessionUtil.getCurrentMessageSize()==Constants.PUBLISH_MESSAGE_SIZE_MAX){
				SessionUtil.resetCurrentMessageSize();
			}else{
				SessionUtil.increaseCurrentMessageSize();
			}
		}else{
			//发布失败
			map.put("code", Constants.OPERATE_FAIL_CODE);
			map.put("message", "留言发布失败，请重新尝试");
		}
		return map;
	}
	
	
}
