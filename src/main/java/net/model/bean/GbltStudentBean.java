package net.model.bean;

import java.io.Serializable;
import java.util.Date;
 
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class GbltStudentBean implements Serializable{
	private static final long serialVersionUID = 1L;

	private String stStudentId;//stUserId;
 
	private String stOrgId;
	 
	private String stOwnerId;
 
	private String firstName;
	 
	private String lastName;//stLastName;
	 
	private String email;//stEmail; occupation
	 
	private String stAddress;
	 
	private Long IMobile;
	 
	private Long mChanting;

	private Long mClassId;
	 
	private String stOccupation;
	 
	private String stStage;
	 
	private Integer ICounselor;
	 
	private Integer IFacilitator;
	 
	private Integer IIsValid; 
	
	private Integer mIMode; 
	
	private String stName; 
	
	private Long mICourse;
	private Long mIBatch;
	private Long mICourseConfig;
 
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP) 
	private Date mDtCourseStart;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP) 
	private Date mDtCourseEnd; 
	
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP) 
	private Date mDtEntry;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP) 
	private Date dtRegistration;
	
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP) 
	private Date dtEntry;

    public GbltStudentBean() {
		// TODO Auto-generated constructor stub
	}
    
	public Integer getmIMode() {
		return mIMode;
	}

	public void setmIMode(Integer mIMode) {
		this.mIMode = mIMode;
	}

	public Long getmClassId() {
		return mClassId;
	}

	public void setmClassId(Long mClassId) {
		this.mClassId = mClassId;
	}

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	public Long getmICourse() {
		return mICourse;
	}

	public void setmICourse(Long mICourse) {
		this.mICourse = mICourse;
	}

	public Long getmIBatch() {
		return mIBatch;
	}

	public void setmIBatch(Long mIBatch) {
		this.mIBatch = mIBatch;
	}

	public Date getmDtCourseStart() {
		return mDtCourseStart;
	}

	public void setmDtCourseStart(Date mDtCourseStart) {
		this.mDtCourseStart = mDtCourseStart;
	}

	public Date getmDtCourseEnd() {
		return mDtCourseEnd;
	}

	public void setmDtCourseEnd(Date mDtCourseEnd) {
		this.mDtCourseEnd = mDtCourseEnd;
	}

	public Date getmDtEntry() {
		return mDtEntry;
	}

	public void setmDtEntry(Date mDtEntry) {
		this.mDtEntry = mDtEntry;
	}

	public String getStStudentId() {
		return stStudentId;
	}

	public void setStStudentId(String stStudentId) {
		this.stStudentId = stStudentId;
	}

	public String getStOrgId() {
		return stOrgId;
	}

	public void setStOrgId(String stOrgId) {
		this.stOrgId = stOrgId;
	}

	public String getStOwnerId() {
		return stOwnerId;
	}

	public void setStOwnerId(String stOwnerId) {
		this.stOwnerId = stOwnerId;
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

	public Integer getICounselor() {
		return ICounselor;
	}

	public void setICounselor(Integer iCounselor) {
		ICounselor = iCounselor;
	}

	public Integer getIFacilitator() {
		return IFacilitator;
	}

	public void setIFacilitator(Integer iFacilitator) {
		IFacilitator = iFacilitator;
	}

	public Integer getIIsValid() {
		return IIsValid;
	}

	public void setIIsValid(Integer iIsValid) {
		IIsValid = iIsValid;
	}

	public Long getmICourseConfig() {
		return mICourseConfig;
	}

	public void setmICourseConfig(Long mICourseConfig) {
		this.mICourseConfig = mICourseConfig;
	}
 

	public Date getDtRegistration() {
		return dtRegistration;
	}

	public void setDtRegistration(Date dtRegistration) {
		this.dtRegistration = dtRegistration;
	}

	public Date getDtEntry() {
		return dtEntry;
	}

	public void setDtEntry(Date dtEntry) {
		this.dtEntry = dtEntry;
	}

	@Override
	public String toString() {
		return "GbltStudentBean [stStudentId=" + stStudentId + ", stOrgId=" + stOrgId + ", stOwnerId=" + stOwnerId
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", stAddress="
				+ stAddress + ", IMobile=" + IMobile + ", mChanting=" + mChanting + ", mClassId=" + mClassId
				+ ", stOccupation=" + stOccupation + ", stStage=" + stStage + ", ICounselor=" + ICounselor
				+ ", IFacilitator=" + IFacilitator + ", IIsValid=" + IIsValid + ", mICourseConfig=" + mICourseConfig
				+ ", stName=" + stName + ", mICourse=" + mICourse + ", mIBatch=" + mIBatch + ", mDtCourseStart="
				+ mDtCourseStart + ", mDtCourseEnd=" + mDtCourseEnd + ", mDtEntry=" + mDtEntry + ", dtRegistration="
				+ dtRegistration + ", dtEntry=" + dtEntry + "]";
	}
    
    
}
