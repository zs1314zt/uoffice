package com.pre.team.uoffice.service;

import java.util.List;
import com.pre.team.uoffice.domain.Office;

/**
 * 
 * @author zs1314zt
 * 首页相关处理,第一次进页面就加载好首页的相关数据
 */
public interface FirstViewService {
	
	/**
	 * 显示五条首页要推荐的office信息
	 */
	public List<Office> getMoreViewOffice();

}
