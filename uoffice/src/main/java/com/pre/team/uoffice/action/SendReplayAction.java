package com.pre.team.uoffice.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.pre.team.uoffice.constants.Constants;
import com.pre.team.uoffice.dao.ReplayDao;
import com.pre.team.uoffice.domain.Replay;
/**
 * 用户发送邮件给我们
 * @author zs1314zt
 *
 */

@ParentPackage("json-default") 
public class SendReplayAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private Replay replay;
	private Map<String,Object> dataMap;
	
	@Autowired
	private ReplayDao replayDao;

	@Action(value = "sendReplay", results = { @Result(name = "success", type="json",params={"root","dataMap"})})
	public String sendReplay() throws Exception{
		dataMap = new HashMap<String, Object>();
		try {
			replayDao.sendReplayMess(replay);
		} catch (Exception e) {
			dataMap.put("message", "发送失败");
			dataMap.put("code", Constants.OPERATE_FAIL_CODE);
			return "success";
		}
		dataMap.put("message", "发送成功");
		dataMap.put("code", Constants.OPERATE_SUCCESS_CODE);
		return "success";
	}
	
	public Replay getReplay() {
		return replay;
	}

	public void setReplay(Replay replay) {
		this.replay = replay;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	
}
