package com.pre.team.uoffice.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;
import com.pre.team.uoffice.util.QRCodeUtil;

@ParentPackage("json-default")
public class CreateQRCodeImgAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 网站URL
	 */
	private String url;
	
	private Map<String, String> dataMap;
	/**
	 * 
	 * Description:获取网页二维码  2015年9月16日
	 * 
	 * @author xuejiahao
	 * @return
	 * @throws Exception
	 */
	@Action(value = "createQRCode", results = { @Result(name = "success", type="json",params={"root","dataMap"})})
	public String createQRCodeImage() throws Exception {
		if(url==null||"".equals(url)){
			return NONE;
		}
		if(!url.contains("http://")){
			url = "http://"+url;
		}
		String verifyCodeStr = new sun.misc.BASE64Encoder().encodeBuffer(QRCodeUtil.createQRCode(url));
		dataMap = new HashMap<String, String>();
		dataMap.put("QRCodeImage", verifyCodeStr);
		return SUCCESS;
	}


	public Map<String, String> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, String> dataMap) {
		this.dataMap = dataMap;
	}


	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}


	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
