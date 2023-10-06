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

import net.model.master.YatraSanctuaryMst;

@Entity
@Table(name = "yatra_cruise_trn", schema = "iyf")
@IdClass(YatraCruiseTrnPk.class)
public class YatraCruiseTrn implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "num_yatra_cruise_id", length = 4)
	private Long yatraCruiseId;
	
	@Column(name = "str_sanctuary_id", length = 4)
	private Long stSanctuaryId;

	@ManyToOne(fetch = FetchType.LAZY)
	//@JsonIgnore
	@JoinColumns({
			@JoinColumn(name = "str_sanctuary_id", referencedColumnName = "str_sanctuary_id", insertable = false, updatable = false) })
	private YatraSanctuaryMst objYatraSanctuaryMst;

	@Column(name = "amt_contribution", precision=6, scale=2)
	private Integer amtContribution;

	@Column(name = "amt_sponserd", precision=6, scale=2)
	private Integer amtSponsored;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_yatra_start")
	private Date dtYaraStart;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_yatra_end")
	private Date dtYatraEnd;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_reg_start")
	private Date dtRegStart;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_reg_end")
	private Date dtRegEnd;

	@Column(name = "str_owner_id", nullable = false, columnDefinition = "character varying (15)")
	private String mStUserId;

	@Id
	@Column(name = "str_org_id", nullable = false, columnDefinition = "character varying (20)")
	private String mStOrgId;

	@Column(name = "num_isvalid", length = 1)
	private Integer mIsValid;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_entry")
	private Date mDtEntry;

	public YatraCruiseTrn() {
		// TODO Auto-generated constructor stub
	}

	public Long getYatraCruiseId() {
		return yatraCruiseId;
	}

	public void setYatraCruiseId(Long yatraCruiseId) {
		this.yatraCruiseId = yatraCruiseId;
	}

	public YatraSanctuaryMst getObjYatraSanctuaryMst() {
		return objYatraSanctuaryMst;
	}

	public void setObjYatraSanctuaryMst(YatraSanctuaryMst objYatraSanctuaryMst) {
		this.objYatraSanctuaryMst = objYatraSanctuaryMst;
	}

	public Long getStSanctuaryId() {
		return stSanctuaryId;
	}

	public void setStSanctuaryId(Long stSanctuaryId) {
		this.stSanctuaryId = stSanctuaryId;
	}

	public Integer getAmtContribution() {
		return amtContribution;
	}

	public void setAmtContribution(Integer amtContribution) {
		this.amtContribution = amtContribution;
	}

	public Integer getAmtSponsored() {
		return amtSponsored;
	}

	public void setAmtSponsored(Integer amtSponsored) {
		this.amtSponsored = amtSponsored;
	}

	public Date getDtYaraStart() {
		return dtYaraStart;
	}

	public void setDtYaraStart(Date dtYaraStart) {
		this.dtYaraStart = dtYaraStart;
	}

	public Date getDtYatraEnd() {
		return dtYatraEnd;
	}

	public void setDtYatraEnd(Date dtYatraEnd) {
		this.dtYatraEnd = dtYatraEnd;
	}

	public Date getDtRegStart() {
		return dtRegStart;
	}

	public void setDtRegStart(Date dtRegStart) {
		this.dtRegStart = dtRegStart;
	}

	public Date getDtRegEnd() {
		return dtRegEnd;
	}

	public void setDtRegEnd(Date dtRegEnd) {
		this.dtRegEnd = dtRegEnd;
	}

	public String getmStUserId() {
		return mStUserId;
	}

	public void setmStUserId(String mStUserId) {
		this.mStUserId = mStUserId;
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
		return Objects.hash(mStOrgId, yatraCruiseId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		YatraCruiseTrn other = (YatraCruiseTrn) obj;
		return Objects.equals(mStOrgId, other.mStOrgId) && Objects.equals(yatraCruiseId, other.yatraCruiseId);
	}

	@Override
	public String toString() {
		return "YatraCruiseTrn [yatraCruiseId=" + yatraCruiseId + ", stSanctuaryId=" + stSanctuaryId
				+ ", amtContribution=" + amtContribution + ", amtSponsored=" + amtSponsored + ", dtYaraStart="
				+ dtYaraStart + ", dtYatraEnd=" + dtYatraEnd + ", dtRegStart=" + dtRegStart + ", dtRegEnd=" + dtRegEnd
				+ ", mStUserId=" + mStUserId + ", mStOrgId=" + mStOrgId + ", mIsValid=" + mIsValid + ", mDtEntry="
				+ mDtEntry + "]";
	}
	
}
