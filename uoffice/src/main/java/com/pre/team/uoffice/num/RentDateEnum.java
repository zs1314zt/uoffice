package com.pre.team.uoffice.num;

/**
 * 
 * 租赁时间枚举
 * @author zs1314zt
 *
 */
public enum RentDateEnum {

	RentDateA("00", "不限"),
	RentDateB("01", "1-2个月"),
	RentDateC("02", "3~5个月"),
	RentDateD("03", "6~11个月"),
	RentDateE("04", "12个月以上");
	
	
	private String code;
	private String value;
	
	//构造函数
	private RentDateEnum(String code, String value){
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
