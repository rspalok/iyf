package net.model.master;

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
@Table(name = "iyf_org_mst", schema = "iyf")
public class GbltOrgMst implements Serializable {
	@Id
	@Column(name = "str_org_id", nullable = false, columnDefinition = "character varying (15)")
	private String stOrgId;

	@Column(name = "str_user_id", nullable = false, columnDefinition = "character varying (20)")
	private String stOwnerId;

	@Column(name = "str_name", columnDefinition = "character varying (100)")
	private String strName;

	@Column(name = "num_isvalid", length = 1)
	private Integer IIsValid;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_entry")
	private Date dtEntry;

	public GbltOrgMst() {
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

	@Override
	public String toString() {
		return "GbltOrgMst [stOrgId=" + stOrgId + ", stOwnerId=" + stOwnerId + ", strName=" + strName + ", IIsValid="
				+ IIsValid + ", dtEntry=" + dtEntry + "]";
	}

}
