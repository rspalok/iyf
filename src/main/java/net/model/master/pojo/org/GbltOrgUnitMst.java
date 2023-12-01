package net.model.master.pojo.org;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "iyf_org_unit_mst", schema = "iyf")
@IdClass(GbltOrgUnitMstPk.class)
public class GbltOrgUnitMst implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "str_orgId", nullable = false, columnDefinition = "character varying (15)")
	private String stOrgId;
	
	@Id
	@Column(name = "num_org_unit", length = 3 )
	private Integer mOrgUnit;

	@Column(name = "str_name", columnDefinition = "character varying (100)")
	private String strName;
	
	@Column(name = "str_address", columnDefinition = "character varying (225)")
	private String strAddress;
	
	@Column(name = "str_user_id", nullable = false, columnDefinition = "character varying (20)")//str_address
	private String stOwnerId;

	@Column(name = "num_isvalid", length = 1)
	private Integer IIsValid;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_entry")
	private Date dtEntry;

	public GbltOrgUnitMst() {
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

	public String getStrAddress() {
		return strAddress;
	}

	public void setStrAddress(String strAddress) {
		this.strAddress = strAddress;
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

	public Integer getmOrgUnit() {
		return mOrgUnit;
	}

	public void setmOrgUnit(Integer mOrgUnit) {
		this.mOrgUnit = mOrgUnit;
	}
	@Override
	public int hashCode() {
		return Objects.hash(mOrgUnit, stOrgId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GbltOrgUnitMst other = (GbltOrgUnitMst) obj;
		return Objects.equals(mOrgUnit, other.mOrgUnit) && Objects.equals(stOrgId, other.stOrgId);
	}
	
	@Override
	public String toString() {
		return "GbltOrgUnitMst [stOrgId=" + stOrgId + ", mOrgUnit=" + mOrgUnit + ", stOwnerId=" + stOwnerId
				+ ", strName=" + strName + ", IIsValid=" + IIsValid + ", dtEntry=" + dtEntry + "]";
	}

}
