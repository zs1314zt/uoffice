package com.pre.team.uoffice.num;

public enum RentPerCntEnum {

	RentPerCntA("00", "不限"),
	RentPerCntB("01", "1~3人"),
	RentPerCntC("02", "4~6人"),
	RentPerCntD("03", "7~9人"),
	RentPerCntE("04", "10~15人"),
	RentPerCntF("05", "16~20人"),
	RentPerCntG("06", "21人及以上");
	
	
	private String code;
	private String value;
	
	//构造函数
	private RentPerCntEnum(String code, String value){
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
