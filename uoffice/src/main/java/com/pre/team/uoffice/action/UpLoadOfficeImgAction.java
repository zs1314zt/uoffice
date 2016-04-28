package com.pre.team.uoffice.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;

import com.pre.team.uoffice.util.DateFormatUtils;

@ParentPackage("upload-default")
public class UpLoadOfficeImgAction {

	private List<File> file;
	
	private List<String> fileFileName;
	
	private Map<String, String> dataMap;
	
	@Action(value = "upLoadOfficeImg", results = { @Result(name = "success", type="json",params={"root","dataMap"}) })
	public String upLoadImg() throws Exception {
		//工程保存图片的路径(文件夹)
		String root = ServletActionContext.getServletContext().getRealPath("/officeImg");
		if(file == null || file.size() == 0){
			 dataMap.put("tip", "图片上传失败，请查看是否选择了正确的图片！！");
			 return "success";
		}
        String photoUrl = "";
		for (int i = 0; i < file.size(); ++i) {
			//得到图片保存的位置（根据root来得到图片保存的路径在tomcat下的该工程里）
			String fileName = getNewFileName(fileFileName.get(i));
			File destFile = new File(root, fileName);
			if (!destFile.getParentFile().exists())
				destFile.getParentFile().mkdirs();
	        FileUtils.copyFile(file.get(i), destFile);
	        //返回页面的路径
	        if(i != file.size() - 1){
	        	photoUrl += "/uoffice/officeImg/"+ fileName + ";";
	        }else{
	        	photoUrl += "/uoffice/officeImg/"+ fileName;
	        }
		}
		dataMap = new HashMap<String, String>();
        dataMap.put("tip", "图片上传成功！");
		dataMap.put("photoUrl", photoUrl);
		return "success";
	}

	private String getNewFileName(String itemFileName) {
		String date = DateFormatUtils.formatDate(new Date());
		String fileName = date + "-" + UUID.randomUUID().toString().substring(0, 8) + getFileType(itemFileName);
		return fileName;
	}

	private String getFileType(String itemFileName) {
		return itemFileName.substring(itemFileName.indexOf("."), itemFileName.length());
	}
	
	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public Map<String, String> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, String> dataMap) {
		this.dataMap = dataMap;
	}
	
	
}
