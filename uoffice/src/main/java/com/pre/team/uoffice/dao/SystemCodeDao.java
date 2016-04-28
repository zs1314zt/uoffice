/**
 * 
 */
package com.pre.team.uoffice.dao;

import java.util.List;

import com.pre.team.uoffice.domain.SystemCode;

/**
 * Description:系统参数操作dao
 * @author zs1314zt
 * 
 */
public interface SystemCodeDao {
	/**
	 * @return
	 * 注册用户数
	 * 办公室数
	 * 成功案例数
	 */
	public List<SystemCode> getSystemCode();
}
