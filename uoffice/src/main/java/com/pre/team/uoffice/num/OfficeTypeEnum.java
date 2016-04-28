package com.pre.team.uoffice.num;

public enum OfficeTypeEnum {

	OfficeTypeA("00", "不限"),
	OfficeTypeB("01", "联合办公"),
	OfficeTypeC("02", "孵化器"),
	OfficeTypeD("03", "短期租赁"),
	OfficeTypeE("04", "长期租赁");
	
	private String code;
	private String value;
	
	//构造函数
	private OfficeTypeEnum(String code, String value){
		this.code = code;
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
