package net.model.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class FollowUpBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer followUpId;

	private String stName;
	private String stContent;
	
	private String stStudentId;// stUserId;
	
	private String[] duallistbox_demo1;
	
	private Integer followUpTo;
	
	private Long mICourseConfig;
	
	private String followUpBy;

	private Integer responseType;// 1: Favorable 2:Not Sure 3: Not Favorable

	private String stResponse;
	
	private String stOwnerId;

	private String stOrgId;

	private Integer isValid;
	
	private String dtFrom;

	private String dtTo;


	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtEntry;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
	private Date dtEvent;

	
	public String[] getDuallistbox_demo1() {
		return duallistbox_demo1;
	}

	public Date getDtEvent() {
		return dtEvent;
	}

	public void setDtEvent(Date dtEvent) {
		this.dtEvent = dtEvent;
	}

	public String getDtFrom() {
		return dtFrom;
	}

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	public String getStContent() {
		return stContent;
	}

	public void setStContent(String stContent) {
		this.stContent = stContent;
	}

	public void setDtFrom(String dtFrom) {
		this.dtFrom = dtFrom;
	}

	public String getDtTo() {
		return dtTo;
	}

	public void setDtTo(String dtTo) {
		this.dtTo = dtTo;
	}

	public void setDuallistbox_demo1(String[] duallistbox_demo1) {
		this.duallistbox_demo1 = duallistbox_demo1;
	}

	public FollowUpBean() {
		// TODO Auto-generated constructor stub
	}

	public Integer getFollowUpTo() {
		return followUpTo;
	}

	public void setFollowUpTo(Integer followUpTo) {
		this.followUpTo = followUpTo;
	}

	public Long getmICourseConfig() {
		return mICourseConfig;
	}

	public void setmICourseConfig(Long mICourseConfig) {
		this.mICourseConfig = mICourseConfig;
	}

	public Integer getFollowUpId() {
		return followUpId;
	}

	public void setFollowUpId(Integer followUpId) {
		this.followUpId = followUpId;
	}

	public String getStStudentId() {
		return stStudentId;
	}

	public void setStStudentId(String stStudentId) {
		this.stStudentId = stStudentId;
	}

	public String getFollowUpBy() {
		return followUpBy;
	}

	public void setFollowUpBy(String followUpBy) {
		this.followUpBy = followUpBy;
	}

	public Integer getResponseType() {
		return responseType;
	}

	public void setResponseType(Integer responseType) {
		this.responseType = responseType;
	}

	public String getStResponse() {
		return stResponse;
	}

	public void setStResponse(String stResponse) {
		this.stResponse = stResponse;
	}

	public String getStOwnerId() {
		return stOwnerId;
	}

	public void setStOwnerId(String stOwnerId) {
		this.stOwnerId = stOwnerId;
	}

	public String getStOrgId() {
		return stOrgId;
	}

	public void setStOrgId(String stOrgId) {
		this.stOrgId = stOrgId;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Date getDtEntry() {
		return dtEntry;
	}

	public void setDtEntry(Date dtEntry) {
		this.dtEntry = dtEntry;
	}

	@Override
	public String toString() {
		return "FollowUpBean [followUpId=" + followUpId + ", stStudentId=" + stStudentId + ", duallistbox_demo1="
				+ Arrays.toString(duallistbox_demo1) + ", followUpTo=" + followUpTo + ", mICourseConfig="
				+ mICourseConfig + ", followUpBy=" + followUpBy + ", responseType=" + responseType + ", stResponse="
				+ stResponse + ", stOwnerId=" + stOwnerId + ", stOrgId=" + stOrgId + ", isValid=" + isValid
				+ ", dtFrom=" + dtFrom + ", dtTo=" + dtTo + ", dtEntry=" + dtEntry + "]";
	}
}
