package net.model.transection.notice;

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

import org.springframework.format.annotation.DateTimeFormat;

import net.model.transection.pojo.courseconfig.IYFCourseConfig;



@Entity 
@Table(name = "iyf_public_reg_url_trn", schema = "iyf") 
@IdClass(IyfPublicRegUrlTrnPk.class)
public class IyfPublicRegUrlTrn implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "num_publish_id",length=4)
	private Integer publishId;
	
	@Id
	@Column(name = "num_course_config_id",length=4)
	private Long mICourseConfig;
 
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name="str_org_id", referencedColumnName="str_org_id" ,insertable=false, updatable=false),
        @JoinColumn(name="num_course_config_id", referencedColumnName="num_course_config_id",insertable=false, updatable=false)
    })
	private IYFCourseConfig ObjIYFCourseConfig;
	

	@Column(name = "str_name", nullable = false, columnDefinition = "character varying (115)")
	private String stName;
	
	@Column(name = "str_url", nullable = false, columnDefinition = "character varying (225)")
	private String stURL;
	
	@Column(name = "str_description", nullable = false, columnDefinition = "character varying (225)")
	private String stDescription;
	
	
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_published")
	private Date dtPublished;//

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_end")
	private Date dtPublishExpiry;//
    
	@Column(name = "num_published",length=1)
	private Integer mPublished;//dt_end
	
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
    
    public IyfPublicRegUrlTrn() {
		// TODO Auto-generated constructor stub
	}

	public Integer getPublishId() {
		return publishId;
	}

	public void setPublishId(Integer publishId) {
		this.publishId = publishId;
	}


	public Long getmICourseConfig() {
		return mICourseConfig;
	}

	public void setmICourseConfig(Long mICourseConfig) {
		this.mICourseConfig = mICourseConfig;
	}

	public IYFCourseConfig getObjIYFCourseConfig() {
		return ObjIYFCourseConfig;
	}

	public void setObjIYFCourseConfig(IYFCourseConfig objIYFCourseConfig) {
		ObjIYFCourseConfig = objIYFCourseConfig;
	}

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	public String getStURL() {
		return stURL;
	}

	public void setStURL(String stURL) {
		this.stURL = stURL;
	}

	public String getStDescription() {
		return stDescription;
	}

	public void setStDescription(String stDescription) {
		this.stDescription = stDescription;
	}

	public Date getDtPublished() {
		return dtPublished;
	}

	public void setDtPublished(Date dtPublished) {
		this.dtPublished = dtPublished;
	}

	public Date getDtPublishExpiry() {
		return dtPublishExpiry;
	}

	public void setDtPublishExpiry(Date dtPublishExpiry) {
		this.dtPublishExpiry = dtPublishExpiry;
	}

	public Integer getmPublished() {
		return mPublished;
	}

	public void setmPublished(Integer mPublished) {
		this.mPublished = mPublished;
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
	public int hashCode() {
		return Objects.hash(mICourseConfig, mStOrgId, publishId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IyfPublicRegUrlTrn other = (IyfPublicRegUrlTrn) obj;
		return Objects.equals(mICourseConfig, other.mICourseConfig) && Objects.equals(mStOrgId, other.mStOrgId)
				&& Objects.equals(publishId, other.publishId);
	}
    
}
