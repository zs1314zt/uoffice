package com.pre.team.uoffice.dao;

import com.pre.team.uoffice.domain.Replay;

/**
 * 用户发送反馈信息
 * @author zs1314zt
 *
 */
public interface ReplayDao {
	
	public void sendReplayMess(Replay replay);
}
