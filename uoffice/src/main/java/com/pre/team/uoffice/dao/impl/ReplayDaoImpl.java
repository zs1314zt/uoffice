package com.pre.team.uoffice.dao.impl;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.pre.team.uoffice.domain.Replay;
import com.pre.team.uoffice.dao.ReplayDao;

@Repository
public class ReplayDaoImpl extends HibernateBaseDao<Replay> 
	implements ReplayDao {
	
	@Override
	public void sendReplayMess(Replay replay) {
		//设置反馈时间
		replay.setPubDate(new Date());
		save(replay);
	}

}
