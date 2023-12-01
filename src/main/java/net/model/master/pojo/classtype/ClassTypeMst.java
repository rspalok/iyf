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
@Table(name = "iyf_class_type_mst", schema = "iyf")
public class ClassTypeMst implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "num_class_type", nullable = false, length = 2)
	private String mClassType;

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
	
	public ClassTypeMst() {
		// TODO Auto-generated constructor stub
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

	
	public String getmClassType() {
		return mClassType;
	}

	public void setmClassType(String mClassType) {
		this.mClassType = mClassType;
	}

	public String getStOrgId() {
		return stOrgId;
	}

	public void setStOrgId(String stOrgId) {
		this.stOrgId = stOrgId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mClassType, stOrgId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClassTypeMst other = (ClassTypeMst) obj;
		return Objects.equals(mClassType, other.mClassType) && Objects.equals(stOrgId, other.stOrgId);
	}

	@Override
	public String toString() {
		return "ClassTypeMst [mClassType=" + mClassType + ", strName=" + strName + ", IIsValid=" + IIsValid
				+ ", dtEntry=" + dtEntry + "]";
	}


}
