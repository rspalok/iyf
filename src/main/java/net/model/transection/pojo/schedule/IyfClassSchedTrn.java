package net.model.transection.pojo.schedule;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import net.model.transection.pojo.courseconfig.IYFCourseConfig;


@Entity 
@Table(name = "iyf_schedule_class_trn", schema = "iyf")
@IdClass(IyfClassSchedTrnPk.class)
@NamedQueries({ 
	@NamedQuery(name = "iyf_schedule_class_trn_pk", 
	query = "select coalesce(max(ff.mIClassId),0)+1 from IyfClassSchedTrn ff where ff.mICourseConfig=:mICourseConfig and ff.mStOrgId=:mStOrgId") 
	})

public class IyfClassSchedTrn implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "iyf_schedule_class_trn",strategy  = "net.id.CustumIdGenerator")
	@GeneratedValue(generator = "iyf_schedule_class_trn")	
	@Column(name = "num_class_id",length=4)
	private Long mIClassId;
	
	@Id
	@Column(name = "num_course_config_id",length=4)//num_course_config_id
	private Long mICourseConfig;

	@Column(name = "num_class_type",length=2)//Regular Revision Special Workshop
	private Integer mIClassType;

	@ManyToOne(fetch = FetchType.LAZY)
	//@JsonIgnore
    @JoinColumns({
        @JoinColumn(name="str_org_id", referencedColumnName="str_org_id" ,insertable=false, updatable=false),
        @JoinColumn(name="num_course_config_id", referencedColumnName="num_course_config_id",insertable=false, updatable=false)
    })
	private IYFCourseConfig ObjIYFCourseConfig;

	@Column(name = "str_name", nullable = false, columnDefinition = "character varying (115)")
	private String mStName;
	
    //@DateTimeFormat(pattern = "dd/MM/yyyy ")
    //@DateTimeFormat(pattern = "yyyy/MM/dd hh:mm a")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_schedule")
	private Date mDtschedule;
    
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
    
    public IyfClassSchedTrn() {
		// TODO Auto-generated constructor stub
	}

	public IYFCourseConfig getObjIYFCourseConfig() {
		return ObjIYFCourseConfig;
	}

	public void setObjIYFCourseConfig(IYFCourseConfig objIYFCourseConfig) {
		ObjIYFCourseConfig = objIYFCourseConfig;
	}

	public Long getmIClassId() {
		return mIClassId;
	}

	public void setmIClassId(Long mIClassId) {
		this.mIClassId = mIClassId;
	}

	public Long getmICourseConfig() {
		return mICourseConfig;
	}

	public void setmICourseConfig(Long mICourseConfig) {
		this.mICourseConfig = mICourseConfig;
	}

	public String getmStName() {
		return mStName;
	}

	public void setmStName(String mStName) {
		this.mStName = mStName;
	}

	public Date getmDtschedule() {
		return mDtschedule;
	}

	public void setmDtschedule(Date mDtschedule) {
		this.mDtschedule = mDtschedule;
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

	public Integer getmIClassType() {
		return mIClassType;
	}

	public void setmIClassType(Integer mIClassType) {
		this.mIClassType = mIClassType;
	}

	@Override
	public String toString() {
		return "IyfClassSchedTrn [mIClassId=" + mIClassId + ", mICourseConfig=" + mICourseConfig + ", mStName="
				+ mStName + ", mDtschedule=" + mDtschedule + ", mStOwnerId=" + mStOwnerId + ", mStOrgId=" + mStOrgId
				+ ", mIsValid=" + mIsValid + ", mDtEntry=" + mDtEntry + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(mIClassId, mICourseConfig, mStOrgId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IyfClassSchedTrn other = (IyfClassSchedTrn) obj;
		return Objects.equals(mIClassId, other.mIClassId) && Objects.equals(mICourseConfig, other.mICourseConfig)
				&& Objects.equals(mStOrgId, other.mStOrgId);
	}
    
    
    
    
}
