package com.pre.team.uoffice.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.dao.UserDao;
import com.pre.team.uoffice.domain.User;
import com.pre.team.uoffice.service.UserService;
import com.pre.team.uoffice.util.Md5Util;
import com.pre.team.uoffice.util.RegexValidateUtil;
import com.pre.team.uoffice.util.SessionUtil;
import com.pre.team.uoffice.util.VerificationCodeUtil;
/**
 * 
 * Description:
 * @author xuejiahao  2015年6月28日
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	private Logger LOG = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	@Override
	public Map<String, Object> login(String userName, String password) {
		Map<String,Object> map = new HashMap<String,Object>();
		User user = null;
		if(userName!=null&&password!=null){
			//邮箱
			if(RegexValidateUtil.checkEmail(userName)){
				//邮箱
				user = userDao.getUserByEmail(userName);
				if(user == null){
					//用户名不存在
					map.put("message", "该邮箱尚未注册");
					map.put("code", Constants.EMAIL_NOT_REGISTER_CODE);
				}else{
					if(Md5Util.md5(password).equals(user.getUserPwd())){
						//登录成功
						map.put("message", "登录成功");
						map.put("code", Constants.OPERATE_SUCCESS_CODE);
						//向Session中添加user
						SessionUtil.addUserToSession(user);
					}else{
						//密码错误
						map.put("message", "密码错误");
						map.put("code", Constants.WRONG_PASSWORD_CODE);
					}
				}
			}
			//手机号
			if(RegexValidateUtil.checkMobileNumber(userName)){
				//手机
				user = userDao.getUserByUserPhone(userName);
				if(user == null){
					//用户名不存在
					map.put("message", "该手机号尚未注册，请先注册");
					map.put("code", Constants.PHONE_NOT_REGISTER_CODE);
				}else{
					if(Md5Util.md5(password).equals(user.getUserPwd())){
						//登录成功
						map.put("message", "登录成功");
						map.put("code", Constants.OPERATE_SUCCESS_CODE);
						//向Session中添加user
						SessionUtil.addUserToSession(user);
					}else{
						//密码错误
						map.put("message", "密码错误");
						map.put("code", Constants.WRONG_PASSWORD_CODE);
					}
				}
			}
		}
		return map;
	}
	@Override
	public Map<String, Object> register(String loginName, String userPwd,String verificationCode) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(loginName!=null&&userPwd!=null&&verificationCode!=null){
			//邮箱登录
			if(RegexValidateUtil.checkEmail(loginName)){
				if(userDao.getUserByEmail(loginName)!=null){
				//该邮箱已注册
					map.put("result", "该邮箱已被注册");
					map.put("code", Constants.EMAIL_IS_REGISTER_CODE);
					return map;
				}
				//验证错误
				if(!VerificationCodeUtil.equals(loginName, verificationCode)){
					map.put("message", "验证码错误，请重新输入验证码");
					map.put("code", Constants.WRONG_VERIFICATIONCODE_CODE);
					return map;
				}
				Object obj = userDao.addUser(newUserEmail(loginName,userPwd));
				if(obj == null){
					//注册失败
					map.put("message", "注册失败");
					map.put("code", Constants.OPERATE_FAIL_CODE);
					return map;
				}
				//注册成功，返回用户对象
				map.put("message", "注册成功");
				map.put("code", Constants.OPERATE_SUCCESS_CODE);
				//移除验证码
				VerificationCodeUtil.removeVerificationCode(loginName);
			}
			//手机登录
			if(RegexValidateUtil.checkMobileNumber(loginName)){
				//判断手机是否已注册
				if(userDao.getUserByUserPhone(loginName)!=null){
					//该手机号已注册
					map.put("message", "该手机号码已注册");
					map.put("code", Constants.PHONE_IS_REGISTER_CODE);
					return map;
				}
				//验证错误
				if(!VerificationCodeUtil.equals(loginName, verificationCode)){
					map.put("message", "验证码错误，请重新输入验证码");
					map.put("code", Constants.WRONG_VERIFICATIONCODE_CODE);
					return map;
				}

				Object obj = userDao.addUser(newUserPhone(loginName,userPwd));
				if(obj == null){
					//注册失败
					map.put("message", "注册失败");
					map.put("code", Constants.OPERATE_FAIL_CODE);
					return map;
				}
				//注册成功，返回用户对象
				map.put("message", "注册成功");
				map.put("code", Constants.OPERATE_SUCCESS_CODE);
				//移除验证码
				VerificationCodeUtil.removeVerificationCode(loginName);
			}
		}
		return map;
	}
	
	@Override
	public Map<String, Object> modifyUserPwd(String oldPwd,String newPwd) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(oldPwd!=null&&newPwd!=null){
			User user = userDao.getUserByUserId(SessionUtil.getLoginUserId());
			if(user!=null){
				//原密码不正确
				if(!user.getUserPwd().equals(Md5Util.md5(oldPwd))){
					map.put("message", "原密码错误");
					map.put("code", Constants.WRONG_PASSWORD_CODE);
					return map;
				}
				//设置新密码,并用MD5加密
				user.setUserPwd(Md5Util.md5(newPwd));
				//返回密码修改结果
				if(userDao.updateUser(user)){
					//修改成功
					map.put("message", "密码修改成功");
					map.put("code", Constants.OPERATE_SUCCESS_CODE);
				}else{
					//修改失败
					map.put("message", "密码修改失败");
					map.put("code", Constants.OPERATE_FAIL_CODE);
				}
			}
		}
		return map;
	}
	

	@Override
	public boolean judgePhoneIsExist(String phone) {
		if(phone!=null){
			//手机号码存在
			if(userDao.getUserByUserPhone(phone)!=null){
				return false;
			}
		}
		//手机号码不存在
		return true;
	}
	
	/**
	 * 
	 * Description:创建对象  2015年7月9日
	 * 
	 * @author xuejiahao
	 * @param userPhone
	 * @param userPwd
	 * @return
	 */
	private User newUserPhone(String userPhone, String userPwd){
		User user = new User();
		user.setUserPhone(userPhone);
		//密码进行MD5加密
		user.setUserPwd(Md5Util.md5(userPwd));
		user.setUserName("新用户_"+userPhone);
		user.setUserType(Constants.ORDINARY_USER);
		return user;
	}
	/**
	 * 
	 * Description:创建对象  2015年7月16日
	 * 
	 * @author xuejiahao
	 * @param userEmail
	 * @param userPwd
	 * @return
	 */
	private User newUserEmail(String userEmail, String userPwd){
		User user = new User();
		user.setUserEmail(userEmail);
		//密码进行MD5加密
		user.setUserPwd(Md5Util.md5(userPwd));
		user.setUserName("新用户_"+userEmail);
		user.setRegisterTime(new Date());
		user.setIp(SessionUtil.getClientIp(ServletActionContext.getRequest()));
		user.setUserType(Constants.ORDINARY_USER);
		return user;
	}
	@Override
	public Map<String, Object> ForgetPwd(String loginName, String verificationCode,
			String newPwd) {
		Map<String,Object> map = new HashMap<String,Object>();
		//邮箱
		if(RegexValidateUtil.checkEmail(loginName)){
			//判断手机是否已注册
			if(userDao.getUserByEmail(loginName)==null){
				//该手机号已注册
				map.put("message", "该邮箱尚未注册，请先注册");
				map.put("code", Constants.EMAIL_NOT_REGISTER_CODE);
				return map;
			}
			//验证错误
			if(!VerificationCodeUtil.equals(loginName, verificationCode)){
				map.put("message", "验证码错误，请重新输入验证码");
				map.put("code", Constants.WRONG_VERIFICATIONCODE_CODE);
				return map;
			}
			//修改密码
			if(userDao.forgetPwdAndModifyPwdByEmail(loginName, newPwd)){
				map.put("message", "密码修改成功");
				map.put("code", Constants.OPERATE_SUCCESS_CODE);
				VerificationCodeUtil.removeVerificationCode(loginName);
			}else{
				map.put("message", "密码修改失败");
				map.put("code", Constants.OPERATE_FAIL_CODE);
			}
		}
		//手机
		if(RegexValidateUtil.checkMobileNumber(loginName)){
			//判断手机是否已注册
			if(userDao.getUserByUserPhone(loginName)==null){
				//该手机号已注册
				map.put("message", "该手机号码尚未注册，请先注册");
				map.put("code", Constants.PHONE_NOT_REGISTER_CODE);
				return map;
			}
			//验证错误
			if(!VerificationCodeUtil.equals(loginName, verificationCode)){
				map.put("message", "验证码错误，请重新输入验证码");
				map.put("code", Constants.WRONG_VERIFICATIONCODE_CODE);
				return map;
			}
			//修改密码
			if(userDao.forgetPwdAndModifyPwdByPhone(loginName, newPwd)){
				map.put("message", "密码修改成功");
				map.put("code", Constants.OPERATE_SUCCESS_CODE);
				VerificationCodeUtil.removeVerificationCode(loginName);
			}else{
				map.put("message", "密码修改失败");
				map.put("code", Constants.OPERATE_FAIL_CODE);
			}
		}
		
		return map;
	}
	@Override
	public boolean judgeEmailIsExist(String email) {
		if(email!=null){
			//邮箱存在
			if(userDao.getUserByEmail(email)!=null){
				return false;
			}
		}
		//邮箱不存在
		return true;
	}
	@Override
	public Map<String, Object> modifyPersonalInfo(User user) {
		Map<String,Object> map = new HashMap<String,Object>();
		User currentUser = SessionUtil.getCurrentUser();
		currentUser.setRealName(user.getRealName());
		currentUser.setUserName(user.getUserName());
		currentUser.setUserPhone(user.getUserPhone());
		if(userDao.updateUser(currentUser)){
			map.put("code", Constants.OPERATE_SUCCESS_CODE);
			map.put("message", "修改成功");
		}else{
			map.put("code", Constants.OPERATE_FAIL_CODE);
			map.put("message", "修改失败");
		}
		return map;
	}

}
