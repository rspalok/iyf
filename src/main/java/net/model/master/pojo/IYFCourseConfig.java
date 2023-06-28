package net.model.master.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity 
@Table(name = "iyf_course_config_trn", schema = "iyf") 
public class IYFCourseConfig implements Serializable{
	 
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "num_course_config_id",length=4)
	private Long mICourseConfig;

	@Column(name = "str_name", nullable = false, columnDefinition = "character varying (125)")
	private String stName;
	
	@Column(name = "num_course_id",length=4)
	private Long mICourse;
	
	@Column(name = "num_batch_id",length=4)
	private Long mIBatch;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_course_start")
	private Date mDtCourseStart;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_course_end")
	private Date mDtCourseEnd;
	
	@Column(name = "str_owner_id", nullable = false, columnDefinition = "character varying (15)")
	private String mStOwnerId;
	
	@Column(name = "str_org_id", nullable = false, columnDefinition = "character varying (20)")
	private String mStOrgId;

	@Column(name = "num_isvalid",length=1)
	private Integer mIsValid;
	
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_entry")
	private Date mDtEntry;
    
    public IYFCourseConfig() {
		// TODO Auto-generated constructor stub
	}

	public Long getmICourseConfig() {
		return mICourseConfig;
	}

	public void setmICourseConfig(Long mICourseConfig) {
		this.mICourseConfig = mICourseConfig;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	@Override
	public String toString() {
		return "IYFCourseConfig [mICourseConfig=" + mICourseConfig + ", mICourse=" + mICourse + ", mIBatch=" + mIBatch
				+ ", mDtCourseStart=" + mDtCourseStart + ", mDtCourseEnd=" + mDtCourseEnd + ", mStOwnerId=" + mStOwnerId
				+ ", mStOrgId=" + mStOrgId + ", mIsValid=" + mIsValid + ", mDtEntry=" + mDtEntry + "]";
	} 
}
