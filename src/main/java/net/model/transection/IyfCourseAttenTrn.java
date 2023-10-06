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

import org.springframework.format.annotation.DateTimeFormat;

import net.model.master.GbltOtpStudentRegTrn;

@Entity
@Table(name = "iyf_course_atten_trn", schema = "iyf")
@IdClass(IyfCourseAttenTrnPk.class)
public class IyfCourseAttenTrn implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "num_class_id", length = 4) // num_course_config_id
	private Long mIClassId;

	@Id
	@Column(name = "str_student_id", nullable = false, columnDefinition = "character varying (15)", unique = true)
	private String stStudentId;// stUserId;

	@Id
	@Column(name = "num_course_config_id", length = 4) // num_course_config_id
	private Long mICourseConfig;

	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumns({
	 * 
	 * @JoinColumn(name="str_org_id", referencedColumnName="str_org_id"
	 * ,insertable=false, updatable=false),
	 * 
	 * @JoinColumn(name="str_student_id", referencedColumnName="str_student_id"
	 * ,insertable=false, updatable=false),
	 * 
	 * @JoinColumn(name="num_course_config_id",
	 * referencedColumnName="num_course_config_id",insertable=false,
	 * updatable=false) }) private IyfClassSchedTrn ObjIyfClassSchedTrn;
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	//@JsonIgnore
	@JoinColumns({
			@JoinColumn(name = "str_org_id", referencedColumnName = "str_org_id", insertable = false, updatable = false),
			@JoinColumn(name = "num_course_config_id", referencedColumnName = "num_course_config_id", insertable = false, updatable = false) })
	private IYFCourseConfig ObjIYFCourseConfig;

	@ManyToOne(fetch = FetchType.LAZY)
	//@JsonIgnore
	@JoinColumns({
			@JoinColumn(name = "str_org_id", referencedColumnName = "str_org_id", insertable = false, updatable = false),
			@JoinColumn(name = "str_student_id", referencedColumnName = "str_student_id", insertable = false, updatable = false) })
	private GbltOtpStudentRegTrn objGbltOtpStudentRegTrns;

	@ManyToOne(fetch = FetchType.LAZY)
	//@JsonIgnore
	@JoinColumns({
			@JoinColumn(name = "str_org_id", referencedColumnName = "str_org_id", insertable = false, updatable = false),
			@JoinColumn(name = "num_class_id", referencedColumnName = "num_class_id", insertable = false, updatable = false),
			@JoinColumn(name = "num_course_config_id", referencedColumnName = "num_course_config_id", insertable = false, updatable = false) })
	private IyfClassSchedTrn objIyfClassSchedTrn;

	@Column(name = "num_ispresent", length = 1)
	private Integer isPresent;

	@Column(name = "str_owner_id", nullable = false, columnDefinition = "character varying (15)")
	private String mStOwnerId;

	@Id
	@Column(name = "str_org_id", nullable = false, columnDefinition = "character varying (20)")
	private String mStOrgId;

	@Column(name = "num_isvalid", length = 1)
	private Integer mIsValid;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_entry")
	private Date mDtEntry;

	public IyfCourseAttenTrn() {
		// TODO Auto-generated constructor stub
	}

	public Long getmIClassId() {
		return mIClassId;
	}

	public void setmIClassId(Long mIClassId) {
		this.mIClassId = mIClassId;
	}

	public String getStStudentId() {
		return stStudentId;
	}

	public void setStStudentId(String stStudentId) {
		this.stStudentId = stStudentId;
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

	public GbltOtpStudentRegTrn getObjGbltOtpStudentRegTrns() {
		return objGbltOtpStudentRegTrns;
	}

	public void setObjGbltOtpStudentRegTrns(GbltOtpStudentRegTrn objGbltOtpStudentRegTrns) {
		this.objGbltOtpStudentRegTrns = objGbltOtpStudentRegTrns;
	}

	public IyfClassSchedTrn getObjIyfClassSchedTrn() {
		return objIyfClassSchedTrn;
	}

	public void setObjIyfClassSchedTrn(IyfClassSchedTrn objIyfClassSchedTrn) {
		this.objIyfClassSchedTrn = objIyfClassSchedTrn;
	}

	public Integer getIsPresent() {
		return isPresent;
	}

	public void setIsPresent(Integer isPresent) {
		this.isPresent = isPresent;
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
		return "IyfCourseAttenTrn [mIClassId=" + mIClassId + ", stStudentId=" + stStudentId + ", mICourseConfig="
				+ mICourseConfig + ", mStOwnerId=" + mStOwnerId + ", mStOrgId=" + mStOrgId + ", mIsValid=" + mIsValid
				+ ", mDtEntry=" + mDtEntry + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(mIClassId, mICourseConfig, mStOrgId, stStudentId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IyfCourseAttenTrn other = (IyfCourseAttenTrn) obj;
		return Objects.equals(mIClassId, other.mIClassId) && Objects.equals(mICourseConfig, other.mICourseConfig)
				&& Objects.equals(mStOrgId, other.mStOrgId) && Objects.equals(stStudentId, other.stStudentId);
	}

}
