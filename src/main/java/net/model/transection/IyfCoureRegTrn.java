package net.model.transection;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import net.model.master.GbltOtpStudentRegTrn;



@Entity 
@Table(name = "iyf_course_reg_trn", schema = "iyf") 
@IdClass(IyfCoureRegTrnPk.class)
public class IyfCoureRegTrn implements Serializable{

	private static final long serialVersionUID = 1L;

	//@EmbeddedId
	//private IyfCoureRegTrnPk Id; 
	@Id
	@Column(name = "num_course_config_id",length=4)
	private Long mICourseConfig;
	
	@Id
	@Column(name = "str_student_id", nullable = false, columnDefinition = "character varying (15)" ,unique = true)
	private String stStudentId;//stUserId;
 
	@ManyToOne(fetch = FetchType.LAZY)
	//@JsonIgnore
	@JoinColumns({
        @JoinColumn(name="str_org_id", referencedColumnName="str_org_id" ,insertable=false, updatable=false),
        @JoinColumn(name="str_student_id", referencedColumnName="str_student_id",insertable=false, updatable=false)
    })
	private GbltOtpStudentRegTrn objGbltOtpStudentRegTrns ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	//@JsonIgnore
    @JoinColumns({
        @JoinColumn(name="str_org_id", referencedColumnName="str_org_id" ,insertable=false, updatable=false),
        @JoinColumn(name="num_course_config_id", referencedColumnName="num_course_config_id",insertable=false, updatable=false)
    })
	private IYFCourseConfig ObjIYFCourseConfig;
	
	
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_registration")
	private Date mDtRegistration;
    
	@Column(name = "str_owner_id", nullable = false, columnDefinition = "character varying (15)")
	private String mStOwnerId;
	
	@Column(name = "str_org_id", nullable = false, columnDefinition = "character varying (20)")
	private String mStOrgId;

	@Column(name = "num_isvalid",length=1)
	private Integer mIsValid;
	
	@Column(name = "num_reg_type",length=1)
	private Integer mRegType;
	
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_entry")
	private Date mDtEntry;
    
    @Transient
    private String tempStatus;
    
    public IyfCoureRegTrn() {
		// TODO Auto-generated constructor stub
	}
 
	public Integer getmRegType() {
		return mRegType;
	}

	public void setmRegType(Integer mRegType) {
		this.mRegType = mRegType;
	}

	public String getTempStatus() {
		return tempStatus;
	}

	public void setTempStatus(String tempStatus) {
		this.tempStatus = tempStatus;
	}

	public GbltOtpStudentRegTrn getObjGbltOtpStudentRegTrns() {
		return objGbltOtpStudentRegTrns;
	}

	public void setObjGbltOtpStudentRegTrns(GbltOtpStudentRegTrn objGbltOtpStudentRegTrns) {
		this.objGbltOtpStudentRegTrns = objGbltOtpStudentRegTrns;
	}

	public IYFCourseConfig getObjIYFCourseConfig() {
		return ObjIYFCourseConfig;
	}

	public void setObjIYFCourseConfig(IYFCourseConfig objIYFCourseConfig) {
		ObjIYFCourseConfig = objIYFCourseConfig;
	}

	public Long getmICourseConfig() {
		return mICourseConfig;
	}

	public void setmICourseConfig(Long mICourseConfig) {
		this.mICourseConfig = mICourseConfig;
	}

	public String getStStudentId() {
		return stStudentId;
	}

	public void setStStudentId(String stStudentId) {
		this.stStudentId = stStudentId;
	}

	public Date getmDtRegistration() {
		return mDtRegistration;
	}

	public void setmDtRegistration(Date mDtRegistration) {
		this.mDtRegistration = mDtRegistration;
	}

	public String getmStOwnerId() {
		return mStOwnerId;
	}

	public void setmStOwnerId(String mStOwnerId) {
		this.mStOwnerId = mStOwnerId;
	}

	public String getmStOrgId() {
		return mStOrgId;
	}

	public void setmStOrgId(String mStOrgId) {
		this.mStOrgId = mStOrgId;
	}

	public Integer getmIsValid() {
		return mIsValid;
	}

	public void setmIsValid(Integer mIsValid) {
		this.mIsValid = mIsValid;
	}

	public Date getmDtEntry() {
		return mDtEntry;
	}

	public void setmDtEntry(Date mDtEntry) {
		this.mDtEntry = mDtEntry;
	}

	@Override
	public String toString() {
		return "IyfCoureRegTrn [mICourseConfig=" + mICourseConfig + ", stStudentId=" + stStudentId
				 + ", mDtRegistration=" + mDtRegistration
				+ ", mStOwnerId=" + mStOwnerId + ", mStOrgId=" + mStOrgId + ", mIsValid=" + mIsValid + ", mDtEntry="
				+ mDtEntry + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(mICourseConfig, stStudentId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IyfCoureRegTrn other = (IyfCoureRegTrn) obj;
		return Objects.equals(mICourseConfig, other.mICourseConfig) && Objects.equals(stStudentId, other.stStudentId);
	}

 
	
    
}
