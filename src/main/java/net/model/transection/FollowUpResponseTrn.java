package net.model.transection;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import net.model.GbltOtpStudentRegTrn;

@Entity
@Table(name = "iyf_follow_up_response_trn", schema = "iyf")
//@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@IdClass(FollowUpResponsePk.class)
public class FollowUpResponseTrn implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "num_follow_up_id", nullable = false, length=8)
	private Integer followUpId;
	
	@Id
	@Column(name = "str_student_id", nullable = false, columnDefinition = "character varying (15)")
	private String stStudentId;//stUserId;
 
	@ManyToOne(fetch = FetchType.LAZY)
	//@JsonIgnore  num_mst_id
	@JoinColumns({
        @JoinColumn(name="str_org_id", referencedColumnName="str_org_id" ,insertable=false, updatable=false),
        @JoinColumn(name="num_follow_up_id", referencedColumnName="num_follow_up_id",insertable=false, updatable=false)
    })
	private FollowUpTrn objFollowUpTrn ;
	
	@Column(name = "follow_up_by", nullable = false, columnDefinition = "character varying (15)")
	private String followUpBy;
	
	@Column(name = "num_response_type",length=1)
	private Integer responseType;//1: Favorable 2:Not Sure 3: Not Favorable
	
	
	@Column(name = "str_response", columnDefinition = "character varying (225)")
	private String stResponse;
	
	@ManyToOne(fetch = FetchType.LAZY)
	//@JsonIgnore
	@JoinColumns({
        @JoinColumn(name="str_org_id", referencedColumnName="str_org_id" ,insertable=false, updatable=false),
        @JoinColumn(name="str_student_id", referencedColumnName="str_student_id",insertable=false, updatable=false)
    })
	private GbltOtpStudentRegTrn objGbltOtpStudentRegTrns ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	////@JsonIgnore
	@JoinColumns({
        @JoinColumn(name="str_org_id", referencedColumnName="str_org_id" ,insertable=false, updatable=false),
        @JoinColumn(name="follow_up_by", referencedColumnName="str_student_id",insertable=false, updatable=false)
    })
	private GbltOtpStudentRegTrn objFolloupBy ;
	
	@Column(name = "str_owner_id", nullable = false, columnDefinition = "character varying (15)")
	private String stOwnerId;
	
	@Id
	@Column(name = "str_org_id", nullable = false, columnDefinition = "character varying (20)")
	private String stOrgId;

	@Column(name = "num_isvalid",length=1)
	private Integer isValid;
	
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_entry")
	private Date dtEntry;
    
    public FollowUpResponseTrn() {
		// TODO Auto-generated constructor stub
	}

	public Integer getFollowUpId() {
		return followUpId;
	}

	public void setFollowUpId(Integer followUpId) {
		this.followUpId = followUpId;
	}

	public FollowUpTrn getObjFollowUpTrn() {
		return objFollowUpTrn;
	}

	public void setObjFollowUpTrn(FollowUpTrn objFollowUpTrn) {
		this.objFollowUpTrn = objFollowUpTrn;
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

	public String getStStudentId() {
		return stStudentId;
	}

	public void setStStudentId(String stStudentId) {
		this.stStudentId = stStudentId;
	}

	public String getStResponse() {
		return stResponse;
	}

	public void setStResponse(String stResponse) {
		this.stResponse = stResponse;
	}

	public GbltOtpStudentRegTrn getObjGbltOtpStudentRegTrns() {
		return objGbltOtpStudentRegTrns;
	}

	public void setObjGbltOtpStudentRegTrns(GbltOtpStudentRegTrn objGbltOtpStudentRegTrns) {
		this.objGbltOtpStudentRegTrns = objGbltOtpStudentRegTrns;
	}

	public GbltOtpStudentRegTrn getObjFolloupBy() {
		return objFolloupBy;
	}

	public void setObjFolloupBy(GbltOtpStudentRegTrn objFolloupBy) {
		this.objFolloupBy = objFolloupBy;
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
		return "FollowUpResponseTrn [followUpId=" + followUpId + ", followUpBy=" + followUpBy + ", responseType="
				+ responseType + ", stStudentId=" + stStudentId + ", stResponse=" + stResponse + ", stOwnerId="
				+ stOwnerId + ", stOrgId=" + stOrgId + ", isValid=" + isValid + ", dtEntry=" + dtEntry + "]";
	}
    
}
