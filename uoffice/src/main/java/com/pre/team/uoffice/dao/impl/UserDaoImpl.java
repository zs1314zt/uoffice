package com.pre.team.uoffice.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.pre.team.uoffice.dao.UserDao;
import com.pre.team.uoffice.domain.User;
import com.pre.team.uoffice.util.Md5Util;

/**
 * 
 * Description:用户表操作Dao实现类
 * @author xuejiahao  2015年6月28日
 *
 */
@Repository
public class UserDaoImpl extends HibernateBaseDao<User> 
	implements UserDao {
	private Logger LOG = Logger.getLogger(UserDaoImpl.class);
	@Override
	public User getUserByUserPhone(String phone) {
		String hql = "from User u where u.userPhone = ?";
		List<User> list = find(hql, phone);
		if(list.size() == 0){
			return null;
		}
		return list.get(0);
	}

	@Override
	public Object addUser(User user) {
		Object obj = null;
		try{
			obj  = save(user);
		}catch(Exception e){
			LOG.error("新增用户出错:"+e);
		}
		return obj;
	}

	@Override
	public User getUserByUserId(int userId) {
		return get(userId);
	}

	@Override
	public User getUserByEmail(String email) {
		String hql = "from User u where u.userEmail = ?";
		List<User> list = find(hql, email);
		if(list.size() == 0){
			return null;
		}
		return list.get(0);
	}

	@Override
	public boolean updateUser(User user) {
		boolean result = false;
		try{
			update(user);
			result = true;
		}catch(Exception e){
			LOG.error("修改用户信息出错:"+e);
			result = false;
		}
		return result;
	}

	@Override
	public boolean forgetPwdAndModifyPwdByPhone(String phone, String newPwd) {
		String hql = "update User as u set u.userPwd = ? where u.userPhone = ?";
		Query query = createQuery(hql, Md5Util.md5(newPwd),phone);
		if(query.executeUpdate()!=0){
			return true;
		}
		return false;
	}

	@Override
	public boolean forgetPwdAndModifyPwdByEmail(String email, String newPwd) {
		String hql = "update User as u set u.userPwd = ? where u.userEmail = ?";
		Query query = createQuery(hql, Md5Util.md5(newPwd),email);
		if(query.executeUpdate()!=0){
			return true;
		}
		return false;
	}

}
