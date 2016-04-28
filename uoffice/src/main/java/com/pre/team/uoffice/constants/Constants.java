package com.pre.team.uoffice.constants;
/**
 * 
 * Description:实体类
 * @author xuejiahao  2015年7月12日
 *
 */
public class Constants {
	
	/**
	 * 当前用户
	 */
	public static final String CURRENT_USER = "current_user";
	
	public static final String CURRENT_CITY = "current_city";
	/**
	 * 浙江
	 */
	public static final String ZHEJIANG_CODE = "01";
	/**
	 * 杭州编号
	 */
	public static final String HANGZHOU_CODE = "0101";
	
	/****************************用户权限开始****************************/
	/**
	 * 普通用户类型
	 */
	public final static String ORDINARY_USER = "01";
	/**
	 * 管理员类型
	 */
	public final static String ADMIN_USER = "02";
	/****************************用户权限结束****************************/
	
	/****************************页面路径开始****************************/
	/**
	 * home.jsp路径
	 */
	public static final String HOME_JSP = "/view/screen/home/home.jsp";
	/**
	 * 登录jsp
	 */
	public static final String LOGIN_JSP = "/view/screen/login/login.jsp";
	
	/**
	 * 房源发布界面
	 */
	public static final String RELEASE_JSP = "/view/screen/release/release.jsp"; 
	
	/**
	 * 办公室类型分类展示页面
	 */
	public static final String SORT_OFFICE = "/view/screen/officeList/officeList.jsp"; 
	
	/**
	 * filter.jsp路径
	 */
	public static final String FILTER_OFFICE = "/view/screen/filter/filter.jsp";
	/**
	 * 办公室详情页面
	 */
	public static final String OFFICE_DETAIL = "/view/screen/detail/detail.jsp";
	/**
	 * 个人信息
	 */
	public static final String PERSONAL_INFO = "/view/screen/userinfo/userinfo.jsp";
	
	public static final String ERROR_INFO = "/view/screen/error/error.jsp";
	/****************************页面路径结束****************************/
	
	/****************************区域编号长度开始****************************/
	
	/****************************区域编号长度开始****************************/
	/**
	 * 省
	 */
	public static final int PRO_LENGTH = 2; 
	/**
	 * 城市
	 */
	public static final int CITY_LENGTH = 4;
	/**
	 * 区
	 */
	public static final int AREA_LEGNGTH = 6;
	/**
	 * 范围
	 */
	public static final int TOWN_LENGTH = 8;
	
	/****************************区域编号长度结束****************************/
	
	/****************************获取验证码类型开始****************************/
	/**
	 * 注册获取验证码
	 */
	public static final String REGISTER_TYPE = "01";
	/**
	 * 忘记密码及快速登录获取验证码
	 */
	public static final String FORGET_PWD_AND_QUICK_LOGIN = "02";
	/****************************获取验证码类型结束****************************/
	/****************************操作状态码开始****************************/
	/**
	 * 手机未注册状态码 201
	 */
	public static final String PHONE_NOT_REGISTER_CODE = "201"; 
	/**
	 * 手机已注册状态码 301
	 */
	public static final String PHONE_IS_REGISTER_CODE = "301"; 
	/**
	 * 邮箱未注册状态码 204
	 */
	public static final String EMAIL_NOT_REGISTER_CODE = "204"; 
	/**
	 * 邮箱已注册状态码 205
	 */
	public static final String EMAIL_IS_REGISTER_CODE = "205"; 
	/**
	 * 操作成功状态码 200
	 */
	public static final String OPERATE_SUCCESS_CODE = "200";
	/**
	 * 操作失败状态码 199
	 */
	public static final String OPERATE_FAIL_CODE = "199";
	/**
	 * 密码错误状态码 202
	 */
	public static final String WRONG_PASSWORD_CODE = "202";
	/**
	 * 验证码错误 302
	 */
	public static final String WRONG_VERIFICATIONCODE_CODE = "302";
	/**
	 * 需要输入验证码
	 */
	public static final String PUBLISH_MEAASGECODE_CODE = "401";
	/****************************操作状态码结束****************************/
	
	/****************************邮箱开始****************************/
	/**
	 * 邮箱smtp地址
	 */
	public static String MAIl_SMTP_ADDRESS = "";
	/**
	 * 邮箱号
	 */
	public static String MAIL_USERNAME = "";
	/**
	 * 登录密码
	 */
	public static String MAIL_PASSWORD = "";
	/**
	 * 注册邮箱主题
	 */
	public static final String MAIL_SUBJECT_REGISTER = "欢迎注册needOffice";
	/**
	 * 密码找回邮箱主题
	 */
	public static final String MAIL_SUBJECT_FINDPWD = "欢迎使用needOffice-密码找回";
	
	public static final String MAIL_PATH_NAME = "/mail/mail-configure.properties";
	/****************************邮箱结束********************************/
	
	/****************************搜索开始********************************/
	/**
	 * 不限
	 */
	public static final String SEARCH_NONE = "00";
	/**
	 * 短租期1~2个月
	 */
	public static final String RENT_DATE_TYPE_1_2 = "01";
	/**
	 * 短租期3~5个月
	 */
	public static final String RENT_DATE_TYPE_3_5 = "02";
	/**
	 * 短租期6~11个月
	 */
	public static final String RENT_DATE_TYPE_6_11 = "03";
	/**
	 * 短租期12个月以上
	 */
	public static final String RENT_DATE_TYPE_12 = "04";
	/**
	 * 公司人数 1~3
	 */
	public static final String COMPANY_SIZE_1_3 = "01";
	/**
	 * 公司人数3~5
	 */
	public static final String COMPANY_SIZE_3_5 = "02";
	/**
	 * 公司人数5~8
	 */
	public static final String COMPANY_SIZE_5_8 = "03";
	/**
	 * 公司人数8~11
	 */
	public static final String COMPANY_SIZE_8_11 = "04";
	/**
	 * 公司人数11~15
	 */
	public static final String COMPANY_SIZE_11_15 = "05";
	/**
	 * 公司人数15~20
	 */
	public static final String COMPANY_SIZE_15_20 = "06";
	/**
	 * 公司人数20以上
	 */
	public static final String COMPANY_SIZE_20 = "07";
	/**
	 * 租金 0~5000一个月
	 */
	public static final String RENTMONEY_0_5000 = "01";
	/**
	 * 租金 0~5000一个月
	 */
	public static final String RENTMONEY_5000_10000 = "02";
	/**
	 * 租金 0~5000一个月
	 */
	public static final String RENTMONEY_10000_15000 = "03";
	/**
	 * 租金 0~5000一个月
	 */
	public static final String RENTMONEY_15000_20000 = "04";
	/**
	 * 租金 0~5000一个月
	 */
	public static final String RENTMONEY_20000 = "05";
	
	/****************************搜索结束********************************/
	
	/****************************系统参数开始********************************/
	public static final String USER_TOTAL = "100001";
	
	public static final String OFFICE_TOTAL = "100002";
	
	public static final String SUCCESS_TOTAL = "100003";
	/****************************系统参数结束********************************/
	/**
	 * 发送注册验证码 
	 */
	public static final int VERIFICATE_REGISTER = 0;
	/**
	 * 发送密码找回验证码
	 */
	public static final int VERIFICATE_FINDPWD = 1;
	
	/**
	 * 验证码图片内容长度
	 */
	public static final int VERIFYCODEIMAGE_LENGTH = 6;
	/**
	 * 发表留言计数
	 */
	public static final String PUBLISH_MESSAGE_SIZE ="Publish_Message_Size" ;
	/**
	 * 该用户发表留言当前验证码
	 */
	public static final String PUBLISH_MESSAGE_CODE = "Publish_Message_Code";
	/**
	 * 留言计数最大值
	 */
	public static final int PUBLISH_MESSAGE_SIZE_MAX = 4;

}
