package com.pre.team.uoffice.dao;

import com.pre.team.uoffice.domain.User;

/**
 * 
 * Description:用户表操作dao
 * @author xuejiahao  2015年6月28日
 *
 */
public interface UserDao {
	/**
	 * 
	 * Description:通过用户手机号码获取用户对象  2015年7月9日
	 * 
	 * @author xuejiahao
	 * @param phone 手机号码
	 * @return 用户对象 User
	 */
	public User getUserByUserPhone(String phone);
	/**
	 * 
	 * Description:新增用户信息  2015年7月9日
	 * 
	 * @author xuejiahao
	 * @param user 用户信息对象
	 * @return 是否添加成功
	 */
	public Object addUser(User user);
	/**
	 * 
	 * Description:根据userId获取User对象  2015年7月9日
	 * 
	 * @author xuejiahao
	 * @param userId 用户编号
	 * @return 用户信息
	 */
	public User getUserByUserId(int userId);
	/**
	 * 
	 * Description:通过email获取用户对象  2015年7月10日
	 * 
	 * @author xuejiahao
	 * @param email
	 * @return 用户信息
	 */
	public User getUserByEmail(String email);
	/**
	 * 
	 * Description:修改用户信息  2015年7月10日
	 * 
	 * @author xuejiahao
	 * @param user 用户信息
	 * @return 是否修改成功
	 */
	public boolean updateUser(User user);
	/**
	 * 
	 * Description:忘记密码手机进行验证码修改密码  2015年7月14日
	 * 
	 * @author xuejiahao
	 * @param phone
	 * @param newPwd
	 * @return
	 */
	public boolean forgetPwdAndModifyPwdByPhone(String phone,String newPwd);
	/**
	 * 
	 * Description:忘记密码邮箱进行验证码修改密码  2015年7月16日
	 * 
	 * @author xuejiahao
	 * @param email
	 * @param newPwd
	 * @return
	 */
	public boolean forgetPwdAndModifyPwdByEmail(String email,String newPwd);
}
