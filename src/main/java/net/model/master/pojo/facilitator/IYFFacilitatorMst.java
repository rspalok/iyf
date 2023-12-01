package net.model.master.pojo.facilitator;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "iyf_facilitator_mst", schema = "iyf")
public class IYFFacilitatorMst {

	@Id
	@Column(name = "num_facilitator_id", nullable = false, length = 4)
	private Integer mFacilitatorId;

	@Column(name = "str_name", columnDefinition = "character varying (115)")
	private String strName;
	
	@Column(name = "str_user_id", nullable = false, columnDefinition = "character varying (15)")
	private String stOwnerId;

	@Column(name = "str_org_id", nullable = false, columnDefinition = "character varying (20)")
	private String stOrgId;

	@Column(name = "num_isvalid", length = 1)
	private Integer IIsValid;
	
	@Column(name = "str_stage", length = 2)
	private Integer strStage;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_entry")
	private Date dtEntry;

	public IYFFacilitatorMst() {
		// TODO Auto-generated constructor stub
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

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public Integer getIIsValid() {
		return IIsValid;
	}

	public void setIIsValid(Integer iIsValid) {
		IIsValid = iIsValid;
	}

	public Date getDtEntry() {
		return dtEntry;
	}

	public void setDtEntry(Date dtEntry) {
		this.dtEntry = dtEntry;
	}

	
	public Integer getmFacilitatorId() {
		return mFacilitatorId;
	}

	public void setmFacilitatorId(Integer mFacilitatorId) {
		this.mFacilitatorId = mFacilitatorId;
	}

	public Integer getStrStage() {
		return strStage;
	}

	public void setStrStage(Integer strStage) {
		this.strStage = strStage;
	}

	@Override
	public String toString() {
		return "IYFFacilitatorMst [mFacilitatorId=" + mFacilitatorId + ", strName=" + strName + ", stOwnerId="
				+ stOwnerId + ", stOrgId=" + stOrgId + ", IIsValid=" + IIsValid + ", strStage=" + strStage
				+ ", dtEntry=" + dtEntry + "]";
	}

}
