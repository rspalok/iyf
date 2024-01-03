package net.model.master.pojo.course;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity 
@Table(name = "iyf_course_mst", schema = "iyf") 

@IdClass(IyfCourseMstPk.class)
public class IyfCourseMst implements Serializable{
	 
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "num_course_id",length=4)
	private Integer mICourse;
	
	@Column(name = "str_name", nullable = false, columnDefinition = "character varying (15)")
	private String mStName;
	 
	@Column(name = "str_user_id", nullable = false, columnDefinition = "character varying (15)")
	private String mStOwnerId;
	
	@Id
	@Column(name = "str_org_id", nullable = false, columnDefinition = "character varying (20)")
	private String mStOrgId;

	@Column(name = "num_isvalid",length=1)
	private Integer mIsValid;
	
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_entry")
	private Date mDtEntry;
    
    public IyfCourseMst() {
		// TODO Auto-generated constructor stub
	}

	public Integer getmICourse() {
		return mICourse;
	}

	public void setmICourse(Integer mICourse) {
		this.mICourse = mICourse;
	}

	public String getmStName() {
		return mStName;
	}

	public void setmStName(String mStName) {
		this.mStName = mStName;
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
		return "IyfCourseMst [mICourse=" + mICourse + ", mStName=" + mStName 
				+ ", mStOwnerId=" + mStOwnerId + ", mStOrgId=" + mStOrgId
				+ ", mIsValid=" + mIsValid + ", mDtEntry=" + mDtEntry + "]";
	}

	 
}
