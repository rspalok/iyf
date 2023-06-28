package net.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class GbltLeavePlannerTrn {
	
	@Id 
	private String stStudentId;//stUserId;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_registration")
	private Date dtOut;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_registration")
	private Date dtJoin;

	@Column(name = "str_remarks", nullable = false, columnDefinition = "character varying (225)")
	private String stRemarks;
	
	@Id
	@Column(name = "str_orgId", nullable = false, columnDefinition = "character varying (20)")
	private String stOrgId;
	
	@Column(name = "str_ownerId", nullable = false, columnDefinition = "character varying (20)")
	private String stOwnerId;
	
	@Column(name = "num_isvalid",length=1)
	private Integer IsValid;
	
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_entry")
	private Date dtEntry;

	public GbltLeavePlannerTrn() {
		// TODO Auto-generated constructor stub
	}

	public String getStStudentId() {
		return stStudentId;
	}

	public void setStStudentId(String stStudentId) {
		this.stStudentId = stStudentId;
	}

	public Date getDtOut() {
		return dtOut;
	}

	public void setDtOut(Date dtOut) {
		this.dtOut = dtOut;
	}

	public Date getDtJoin() {
		return dtJoin;
	}

	public void setDtJoin(Date dtJoin) {
		this.dtJoin = dtJoin;
	}

	public String getStRemarks() {
		return stRemarks;
	}

	public void setStRemarks(String stRemarks) {
		this.stRemarks = stRemarks;
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

	public Integer getIsValid() {
		return IsValid;
	}

	public void setIsValid(Integer isValid) {
		IsValid = isValid;
	}

	public Date getDtEntry() {
		return dtEntry;
	}

	public void setDtEntry(Date dtEntry) {
		this.dtEntry = dtEntry;
	}

	@Override
	public String toString() {
		return "GbltLeavePlannerTrn [stStudentId=" + stStudentId + ", dtOut=" + dtOut + ", dtJoin=" + dtJoin
				+ ", stRemarks=" + stRemarks + ", stOrgId=" + stOrgId + ", stOwnerId=" + stOwnerId + ", IsValid="
				+ IsValid + ", dtEntry=" + dtEntry + "]";
	}
    
    
}
