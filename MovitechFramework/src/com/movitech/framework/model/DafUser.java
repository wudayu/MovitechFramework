package com.movitech.framework.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.movitech.framework.constant.Constant;

/**
 * 
 * @author: Wu Dayu
 * @En_Name: David Wu
 * @E-mail: wudayu@gmail.com
 * @Created Time: Dec 8, 2014, 2:56:18 PM
 * @Description: DafUser是UserModel的Object Json对象
 * 
 **/

@JsonIgnoreProperties(ignoreUnknown=Constant.jsonIgnoreUnknown)
public class DafUser {
	String id;
	String name;
	String screenName;
	String mphone;
	String photosrc;
	String approveState; // Constant.APPROVE_STATE
	String brokerType;
	String superiorId;
	String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getMphone() {
		return mphone;
	}

	public void setMphone(String mphone) {
		this.mphone = mphone;
	}

	public String getPhotosrc() {
		return photosrc;
	}

	public void setPhotosrc(String photosrc) {
		this.photosrc = photosrc;
	}

	public String getApproveState() {
		return approveState;
	}

	public void setApproveState(String approveState) {
		this.approveState = approveState;
	}

	public String getBrokerType() {
		return brokerType;
	}

	public void setBrokerType(String brokerType) {
		this.brokerType = brokerType;
	}

	public String getSuperiorId() {
		return superiorId;
	}

	public void setSuperiorId(String superiorId) {
		this.superiorId = superiorId;
	}

	@Override
	public String toString() {
		return "XcfcUser [id=" + id + ", name=" + name + ", screenName="
				+ screenName + ", mphone=" + mphone + ", photosrc=" + photosrc
				+ ", approveState=" + approveState + "]";
	}
}
