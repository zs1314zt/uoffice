package com.pre.team.uoffice.action;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("htmlview")
public class TestAction extends ActionSupport {
	private static final long serialVersionUID = 3187484243801946726L;

	private InputStream inputStream;

	public String showPage() throws Exception{
		File reportFile = new File("D://hello.html");
		inputStream = new FileInputStream(reportFile);
		return SUCCESS;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
}
