package com.pre.team.uoffice.service;

import java.util.Map;

import com.pre.team.uoffice.domain.User;

/**
 * 
 * Description:用户相关操作Service
 * @author xuejiahao  2015年7月9日
 *
 */
public interface UserService {
	/**
	 * 
	 * Description:登录  2015年7月9日
	 * 
	 * @author xuejiahao
	 * @param userName 用户名
	 * @param password 密码
	 * @return 登录结果
	 */
	public Map<String,Object> login(String userName,String password); 
	/**
	 * 
	 * Description:注册  2015年7月9日
	 * 
	 * @author xuejiahao
	 * @param userPhone 手机号
	 * @param userEmail 邮箱
	 * @param userPwd 密码
	 * @param verificationCode 验证码
	 * @return 注册结果
	 */
	public Map<String,Object> register(String loginName,String userPwd,String verificationCode);
	/**
	 * 
	 * Description:修改用户密码  2015年7月10日
	 * 
	 * @author xuejiahao
	 * @param userId 用户编号
	 * @param oldPwd 旧密码
	 * @param newPwd 新密码
	 * @return 修改结果
	 */
	public Map<String,Object> modifyUserPwd(String oldPwd,String newPwd);
	/**
	 * 
	 * Description:判断手机号是否已被注册  2015年7月13日
	 * 
	 * @author xuejiahao
	 * @param phone
	 * @return
	 */
	public boolean judgePhoneIsExist(String phone);
	/**
	 * 
	 * Description:判断邮箱是否存在  2015年7月16日
	 * 
	 * @author xuejiahao
	 * @param email
	 * @return
	 */
	public boolean judgeEmailIsExist(String email);
	/**
	 * 
	 * Description:忘记密码修改密码  2015年7月14日
	 * 
	 * @author xuejiahao
	 * @param loginName
	 * @param verificationCode
	 * @param newPwd
	 * @return
	 */
	public Map<String, Object> ForgetPwd(String loginName,String verificationCode,String newPwd);
	/**
	 * 
	 * Description:维护个人信息  2015年8月3日
	 * 
	 * @author xuejiahao
	 * @param user
	 * @return
	 */
	public Map<String,Object> modifyPersonalInfo(User user);
}
