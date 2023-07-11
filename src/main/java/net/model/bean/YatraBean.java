package net.model.bean;

import java.io.Serializable;
import java.util.Date;

public class YatraBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private String stStudentId;
	private String firstName;
	 
	private String lastName;
	 
	private String email;
	 
	private String stAddress;
	 
	private Long IMobile;
	 
	private Long mChanting;

	private String stOccupation;
	 
	private String stStage;
	
	private Integer mIMode; 
	
	private Long yatraCruiseId;
	
	private Date mDtRegistration;

	private Integer mContributed;
	
	private Integer mRegStatus;
	
	private Integer contributionMode;

	private String mStUserId;
	
	private Long yatraSanctuaryId;

	private String stName;

	private String stAbout;

	private String stSignificance;

	private String stPlaceToVisit;
	private String facilitator;

    public YatraBean() {
		// TODO Auto-generated constructor stub
	}

	public String getFacilitator() {
		return facilitator;
	}

	public void setFacilitator(String facilitator) {
		this.facilitator = facilitator;
	}

	public String getStStudentId() {
		return stStudentId;
	}

	public void setStStudentId(String stStudentId) {
		this.stStudentId = stStudentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStAddress() {
		return stAddress;
	}

	public void setStAddress(String stAddress) {
		this.stAddress = stAddress;
	}

	public Long getIMobile() {
		return IMobile;
	}

	public void setIMobile(Long iMobile) {
		IMobile = iMobile;
	}

	public Long getmChanting() {
		return mChanting;
	}

	public void setmChanting(Long mChanting) {
		this.mChanting = mChanting;
	}

	public String getStOccupation() {
		return stOccupation;
	}

	public void setStOccupation(String stOccupation) {
		this.stOccupation = stOccupation;
	}

	public String getStStage() {
		return stStage;
	}

	public void setStStage(String stStage) {
		this.stStage = stStage;
	}

	public Integer getmIMode() {
		return mIMode;
	}

	public void setmIMode(Integer mIMode) {
		this.mIMode = mIMode;
	}

	public Long getYatraCruiseId() {
		return yatraCruiseId;
	}

	public void setYatraCruiseId(Long yatraCruiseId) {
		this.yatraCruiseId = yatraCruiseId;
	}

	public Date getmDtRegistration() {
		return mDtRegistration;
	}

	public void setmDtRegistration(Date mDtRegistration) {
		this.mDtRegistration = mDtRegistration;
	}

	public Integer getmContributed() {
		return mContributed;
	}

	public void setmContributed(Integer mContributed) {
		this.mContributed = mContributed;
	}

	public Integer getmRegStatus() {
		return mRegStatus;
	}

	public void setmRegStatus(Integer mRegStatus) {
		this.mRegStatus = mRegStatus;
	}

	public Integer getContributionMode() {
		return contributionMode;
	}

	public void setContributionMode(Integer contributionMode) {
		this.contributionMode = contributionMode;
	}

	public String getmStUserId() {
		return mStUserId;
	}

	public void setmStUserId(String mStUserId) {
		this.mStUserId = mStUserId;
	}

	public Long getYatraSanctuaryId() {
		return yatraSanctuaryId;
	}

	public void setYatraSanctuaryId(Long yatraSanctuaryId) {
		this.yatraSanctuaryId = yatraSanctuaryId;
	}

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	public String getStAbout() {
		return stAbout;
	}

	public void setStAbout(String stAbout) {
		this.stAbout = stAbout;
	}

	public String getStSignificance() {
		return stSignificance;
	}

	public void setStSignificance(String stSignificance) {
		this.stSignificance = stSignificance;
	}

	public String getStPlaceToVisit() {
		return stPlaceToVisit;
	}

	public void setStPlaceToVisit(String stPlaceToVisit) {
		this.stPlaceToVisit = stPlaceToVisit;
	}

	@Override
	public String toString() {
		return "YatraBean [stStudentId=" + stStudentId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", email=" + email + ", stAddress=" + stAddress + ", IMobile=" + IMobile + ", mChanting=" + mChanting
				+ ", stOccupation=" + stOccupation + ", stStage=" + stStage + ", mIMode=" + mIMode + ", yatraCruiseId="
				+ yatraCruiseId + ", mDtRegistration=" + mDtRegistration + ", mContributed=" + mContributed
				+ ", mRegStatus=" + mRegStatus + ", contributionMode=" + contributionMode + ", mStUserId=" + mStUserId
				+ ", yatraSanctuaryId=" + yatraSanctuaryId + ", stName=" + stName + ", stAbout=" + stAbout
				+ ", stSignificance=" + stSignificance + ", stPlaceToVisit=" + stPlaceToVisit + ", facilitator="
				+ facilitator + "]";
	}
    
}
