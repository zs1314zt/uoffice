package com.pre.team.uoffice.num;

/**
 * 租金
 * @author zs1314zt
 *
 */
public enum RentMoneyEnum {

	RentMoneyA("00", "不限"),
	RentMoneyB("01", "0~5000元/月"),
	RentMoneyC("02", "5000~10000元/月"),
	RentMoneyD("03", "10000~15000元/月"),
	RentMoneyE("04", "15000~20000元/月"),
	RentMoneyF("03", "20000元/月以上");
	
	
	private String code;
	private String value;
	
	//构造函数
	private RentMoneyEnum(String code, String value){
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
