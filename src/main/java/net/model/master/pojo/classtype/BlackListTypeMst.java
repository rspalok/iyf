package net.model.master.pojo.classtype;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "iyf_blocked_type_mst", schema = "iyf")
public class BlackListTypeMst implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "num_black_list_type", nullable = false, length = 2)
	private String mBlackListType;

	@Column(name = "str_name", columnDefinition = "character varying (100)")
	private String strName;

	@Column(name = "num_isvalid", length = 1)
	private Integer IIsValid;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_entry")
	private Date dtEntry;

	@Id
	@Column(name = "str_org_id", nullable = false, columnDefinition = "character varying (20)")
	private String stOrgId;
	
	public BlackListTypeMst() {
		// TO DO Auto-generated constructor stub
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

	public String getmBlackListType() {
		return mBlackListType;
	}

	public void setmBlackListType(String mBlackListType) {
		this.mBlackListType = mBlackListType;
	}

	
	public String getStOrgId() {
		return stOrgId;
	}

	public void setStOrgId(String stOrgId) {
		this.stOrgId = stOrgId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mBlackListType, stOrgId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlackListTypeMst other = (BlackListTypeMst) obj;
		return Objects.equals(mBlackListType, other.mBlackListType) && Objects.equals(stOrgId, other.stOrgId);
	}

	@Override
	public String toString() {
		return "BlackListTypeMst [mBlackListType=" + mBlackListType + ", strName=" + strName + ", IIsValid=" + IIsValid
				+ ", dtEntry=" + dtEntry + ", stOrgId=" + stOrgId + "]";
	}

	
	
}
