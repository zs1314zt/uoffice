package com.pre.team.uoffice.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.pre.team.uoffice.num.OfficeTypeEnum;
import com.pre.team.uoffice.util.StringUtil;

/**
 * Description:办公室实体类
 * @author xuejiahao  2015年6月28日
 * 
 */
public class Office implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 办公室id
	 */
	private Integer officeId;
	/**
	 * 办公室名
	 */
	private String officeName;
	/**
	 * 办公室详细地址
	 */
	private String detailAddr;
	/**
	 * 区域地址
	 */
	private String locationId;
	/**
	 * 办公室描述
	 */
	private String officeDesc;
	/**
	 * 办公室详情
	 */
	private String officeContent;
	/**
	 * 01联合办公，02孵化器，03短期租赁，04长期租赁
	 */
	private String officeType;
	
	private String Type;
	/**
	 * 起租期
	 */
	private Integer rentDate;
	/**
	 * 租金
	 */
	private Integer officeMoney;
	/**
	 * 办公室图片url相对路径，以；相隔
	 */
	private String photoUrl;
	
	private List<String> photoUrls;
	/**
	 * 历史访问次数
	 */
	private Integer viewCount;
	/**
	 * 联系方式邮箱或手机号
	 */
	private String officeContact;
	/**
	 * 办公人数
	 */
	private Integer officeArea;
	/**
	 * 办公人数
	 */
	private Integer officeSize;
	/**
	 * 办公室发布时间
	 */
	private Date publishTime;
	
	/**
	 * 广告标识
	 * 01，02，03，04，05
	 */
	private String officeAdvert;
	
	/**
	 * 广告图片url
	 */
	private String advertPhotoUrl;
	
	/**
	 * 单个工位价钱
	 */
	private String eachPrice;
	/**
	 * 地图描述
	 */
	private String mapDesc;
	/**
	 * 地图图片url
	 */
	private String mapUrl;
	/**
	 * 详情页面横图
	 */
	private String bannerUrl;
	
	
	private String remark;
	
	public Office(){
		
	}
	public Office(Integer officeId){
		this.officeId = officeId;
	}

	public Integer getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getDetailAddr() {
		return detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getOfficeDesc() {
		return officeDesc;
	}

	public void setOfficeDesc(String officeDesc) {
		this.officeDesc = officeDesc;
	}

	public String getOfficeContent() {
		return officeContent;
	}

	public void setOfficeContent(String officeContent) {
		this.officeContent = officeContent;
	}

	public String getOfficeType() {
		return officeType;
	}

	public void setOfficeType(String officeType) {
		this.officeType = officeType;
		if(this.officeType!=null){
			setType(officeType);
		}
	}

	public Integer getRentDate() {
		return rentDate;
	}

	public void setRentDate(Integer rentDate) {
		this.rentDate = rentDate;
	}

	public Integer getOfficeMoney() {
		return officeMoney;
	}

	public void setOfficeMoney(Integer officeMoney) {
		this.officeMoney = officeMoney;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
		
		if(photoUrl!=null){
			photoUrls = new ArrayList<String>();
			if(photoUrl.contains(";")){
				photoUrls = Arrays.asList(photoUrl.split(";"));
			}else{
				photoUrls.add(photoUrl);
			}
		}
		
	}

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public String getOfficeContact() {
		return officeContact;
	}

	public void setOfficeContact(String officeContact) {
		this.officeContact = officeContact;
	}

	public Integer getOfficeSize() {
		return officeSize;
	}

	public void setOfficeSize(Integer officeSize) {
		this.officeSize = officeSize;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOfficeAdvert() {
		return officeAdvert;
	}
	public void setOfficeAdvert(String officeAdvert) {
		this.officeAdvert = officeAdvert;
	}
	public String getAdvertPhotoUrl() {
		return advertPhotoUrl;
	}
	public void setAdvertPhotoUrl(String advertPhotoUrl) {
		this.advertPhotoUrl = advertPhotoUrl;
	}
	public String getEachPrice() {
		return eachPrice;
	}
	public void setEachPrice(String eachPrice) {
		this.eachPrice = eachPrice;
	}
	public String getBannerUrl() {
		return bannerUrl;
	}
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return Type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		String officeType = null;
		if(OfficeTypeEnum.OfficeTypeB.getCode().equals(type)){
			officeType = OfficeTypeEnum.OfficeTypeB.getValue();
		}else if(OfficeTypeEnum.OfficeTypeC.getCode().equals(type)){
			officeType = OfficeTypeEnum.OfficeTypeC.getValue();
		}else if(OfficeTypeEnum.OfficeTypeD.getCode().equals(type)){
			officeType = OfficeTypeEnum.OfficeTypeD.getValue();
		}else if(OfficeTypeEnum.OfficeTypeE.getCode().equals(type)){
			officeType = OfficeTypeEnum.OfficeTypeE.getValue();
		}
		Type = officeType;
	}
	/**
	 * @return the mapDesc
	 */
	public String getMapDesc() {
		return mapDesc;
	}
	/**
	 * @param mapDesc the mapDesc to set
	 */
	public void setMapDesc(String mapDesc) {
		this.mapDesc = mapDesc;
	}
	/**
	 * @return the mapUrl
	 */
	public String getMapUrl() {
		return mapUrl;
	}
	/**
	 * @param mapUrl the mapUrl to set
	 */
	public void setMapUrl(String mapUrl) {
		this.mapUrl = mapUrl;
	}
	/**
	 * @return the photoUrls
	 */
	public List<String> getPhotoUrls() {
		return photoUrls;
	}
	/**
	 * @param photoUrls the photoUrls to set
	 */
	public void setPhotoUrls(List<String> photoUrls) {
		this.photoUrls = photoUrls;
	}
	/**
	 * @return the officeArea
	 */
	public Integer getOfficeArea() {
		return officeArea;
	}
	/**
	 * @param officeArea the officeArea to set
	 */
	public void setOfficeArea(Integer officeArea) {
		this.officeArea = officeArea;
	}
	
	
	
	
}
