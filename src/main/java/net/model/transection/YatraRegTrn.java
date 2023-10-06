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
@Table(name = "yatra_reg_trn", schema = "iyf")
@IdClass(YatraRegTrnPk.class)
public class YatraRegTrn implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "num_yatra_cruise_id", length = 4)
	private Long yatraCruiseId;

	@Id
	@Column(name = "str_student_id", nullable = false, columnDefinition = "character varying (15)", unique = true)
	private String stStudentId;// stUserId;

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
			@JoinColumn(name = "num_yatra_cruise_id", referencedColumnName = "num_yatra_cruise_id", insertable = false, updatable = false) })
	private YatraCruiseTrn objYatraCruiseTrn;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_registration")
	private Date dtRegistration;

	@Column(name = "num_reg_status", length = 1)
	private Integer regStatus;

	@Column(name = "num_contribution_mode", length = 1)
	private Integer contributionMode;

	@Column(name = "num_contributed", length = 4)
	private Integer contributed;

	@Column(name = "str_user_id", nullable = false, columnDefinition = "character varying (15)")
	private String stUserId;


	@Column(name = "num_facilitator_id",columnDefinition = "character varying (15)")
	private String IFacilitator;
	
	@Id
	@Column(name = "str_org_id", nullable = false, columnDefinition = "character varying (20)")
	private String stOrgId;

	@Column(name = "num_isvalid", length = 1)
	private Integer isValid;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_entry")
	private Date dtEntry;

	public YatraRegTrn() {
		// TODO Auto-generated constructor stub
	}

	public Integer getContributed() {
		return contributed;
	}

	public void setContributed(Integer contributed) {
		this.contributed = contributed;
	}

	public Long getYatraCruiseId() {
		return yatraCruiseId;
	}

	public void setYatraCruiseId(Long yatraCruiseId) {
		this.yatraCruiseId = yatraCruiseId;
	}

	public YatraCruiseTrn getObjYatraCruiseTrn() {
		return objYatraCruiseTrn;
	}

	public void setObjYatraCruiseTrn(YatraCruiseTrn objYatraCruiseTrn) {
		this.objYatraCruiseTrn = objYatraCruiseTrn;
	}

	public Date getDtRegistration() {
		return dtRegistration;
	}

	public void setDtRegistration(Date dtRegistration) {
		this.dtRegistration = dtRegistration;
	}

	public Integer getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(Integer regStatus) {
		this.regStatus = regStatus;
	}

	public Integer getContributionMode() {
		return contributionMode;
	}

	public void setContributionMode(Integer contributionMode) {
		this.contributionMode = contributionMode;
	}

	public String getStUserId() {
		return stUserId;
	}

	public void setStUserId(String stUserId) {
		this.stUserId = stUserId;
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

	public String getStStudentId() {
		return stStudentId;
	}

	public void setStStudentId(String stStudentId) {
		this.stStudentId = stStudentId;
	}

	public GbltOtpStudentRegTrn getObjGbltOtpStudentRegTrns() {
		return objGbltOtpStudentRegTrns;
	}

	public void setObjGbltOtpStudentRegTrns(GbltOtpStudentRegTrn objGbltOtpStudentRegTrns) {
		this.objGbltOtpStudentRegTrns = objGbltOtpStudentRegTrns;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		return Objects.hash(yatraCruiseId, stOrgId, stStudentId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		YatraRegTrn other = (YatraRegTrn) obj;
		return Objects.equals(yatraCruiseId, other.yatraCruiseId) && Objects.equals(stOrgId, other.stOrgId)
				&& Objects.equals(stStudentId, other.stStudentId);
	}

	@Override
	public String toString() {
		return "YatraRegTrn [yatraCruiseId=" + yatraCruiseId + ", stStudentId=" + stStudentId + ", dtRegistration="
				+ dtRegistration + ", regStatus=" + regStatus + ", contributionMode=" + contributionMode
				+ ", contributed=" + contributed + ", stUserId=" + stUserId + ", stOrgId=" + stOrgId + ", isValid="
				+ isValid + ", dtEntry=" + dtEntry + "]";
	}

}
