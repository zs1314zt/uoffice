/**
 * 
 */
package com.pre.team.uoffice.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Description:
 * @author xuejiahao  2015年8月3日
 * 
 */
public class OfficeDetail implements Serializable {

	/**
	 * 
	 */
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
	private String locationAddress;
	
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
	/**
	 * 起租期
	 */
	private Integer rentDate;
	/**
	 * 租金
	 */
	private Integer officeMoney;
	/**
	 * 办公室图片url相对路径
	 */
	private List<String> photoUrl;
	/**
	 * 历史访问次数
	 */
	private Integer viewCount;
	/**
	 * 联系方式邮箱或手机号
	 */
	private String officeContact;
	/**
	 * 办公面积
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

	/**
	 * @return the officeId
	 */
	public Integer getOfficeId() {
		return officeId;
	}

	/**
	 * @param officeId the officeId to set
	 */
	public void setOfficeId(Integer officeId) {
		this.officeId = officeId;
	}

	/**
	 * @return the officeName
	 */
	public String getOfficeName() {
		return officeName;
	}

	/**
	 * @param officeName the officeName to set
	 */
	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	/**
	 * @return the detailAddr
	 */
	public String getDetailAddr() {
		return detailAddr;
	}

	/**
	 * @param detailAddr the detailAddr to set
	 */
	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

	/**
	 * @return the locationAddress
	 */
	public String getLocationAddress() {
		return locationAddress;
	}

	/**
	 * @param locationAddress the locationAddress to set
	 */
	public void setLocationAddress(String locationAddress) {
		this.locationAddress = locationAddress;
	}

	/**
	 * @return the officeDesc
	 */
	public String getOfficeDesc() {
		return officeDesc;
	}

	/**
	 * @param officeDesc the officeDesc to set
	 */
	public void setOfficeDesc(String officeDesc) {
		this.officeDesc = officeDesc;
	}

	/**
	 * @return the officeContent
	 */
	public String getOfficeContent() {
		return officeContent;
	}

	/**
	 * @param officeContent the officeContent to set
	 */
	public void setOfficeContent(String officeContent) {
		this.officeContent = officeContent;
	}

	/**
	 * @return the officeType
	 */
	public String getOfficeType() {
		return officeType;
	}

	/**
	 * @param officeType the officeType to set
	 */
	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}

	/**
	 * @return the rentDate
	 */
	public Integer getRentDate() {
		return rentDate;
	}

	/**
	 * @param rentDate the rentDate to set
	 */
	public void setRentDate(Integer rentDate) {
		this.rentDate = rentDate;
	}

	/**
	 * @return the officeMoney
	 */
	public Integer getOfficeMoney() {
		return officeMoney;
	}

	/**
	 * @param officeMoney the officeMoney to set
	 */
	public void setOfficeMoney(Integer officeMoney) {
		this.officeMoney = officeMoney;
	}

	/**
	 * @return the photoUrl
	 */
	public List<String> getPhotoUrl() {
		return photoUrl;
	}

	/**
	 * @param photoUrl the photoUrl to set
	 */
	public void setPhotoUrl(List<String> photoUrl) {
		this.photoUrl = photoUrl;
	}

	/**
	 * @return the viewCount
	 */
	public Integer getViewCount() {
		return viewCount;
	}

	/**
	 * @param viewCount the viewCount to set
	 */
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	/**
	 * @return the officeContact
	 */
	public String getOfficeContact() {
		return officeContact;
	}

	/**
	 * @param officeContact the officeContact to set
	 */
	public void setOfficeContact(String officeContact) {
		this.officeContact = officeContact;
	}

	/**
	 * @return the officeSize
	 */
	public Integer getOfficeSize() {
		return officeSize;
	}

	/**
	 * @param officeSize the officeSize to set
	 */
	public void setOfficeSize(Integer officeSize) {
		this.officeSize = officeSize;
	}

	/**
	 * @return the publishTime
	 */
	public Date getPublishTime() {
		return publishTime;
	}

	/**
	 * @param publishTime the publishTime to set
	 */
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	/**
	 * @return the officeAdvert
	 */
	public String getOfficeAdvert() {
		return officeAdvert;
	}

	/**
	 * @param officeAdvert the officeAdvert to set
	 */
	public void setOfficeAdvert(String officeAdvert) {
		this.officeAdvert = officeAdvert;
	}

	/**
	 * @return the advertPhotoUrl
	 */
	public String getAdvertPhotoUrl() {
		return advertPhotoUrl;
	}

	/**
	 * @param advertPhotoUrl the advertPhotoUrl to set
	 */
	public void setAdvertPhotoUrl(String advertPhotoUrl) {
		this.advertPhotoUrl = advertPhotoUrl;
	}

	/**
	 * @return the eachPrice
	 */
	public String getEachPrice() {
		return eachPrice;
	}

	/**
	 * @param eachPrice the eachPrice to set
	 */
	public void setEachPrice(String eachPrice) {
		this.eachPrice = eachPrice;
	}

	/**
	 * @return the bannerUrl
	 */
	public String getBannerUrl() {
		return bannerUrl;
	}

	/**
	 * @param bannerUrl the bannerUrl to set
	 */
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	
	public String getLocationId() {
		return locationId;
	}
	
	public void setLocationId(String locationId) {
		this.locationId = locationId;
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
