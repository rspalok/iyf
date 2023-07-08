package net.model.master;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "gblt_user_role_trn", schema = "iyf")
public class GbltUsersRolesTrn implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "num_role_id", nullable = false, length = 4, unique = true)
	private Long IRoleId;

	@Id
	@Column(name = "str_user_id", nullable = false, columnDefinition = "character varying (15)")
	private String IUserId;// stUserId;

	@Id
	@Column(name = "str_OrgId", columnDefinition = "character varying (20)")
	private String stOrgId;

	@Column(name = "num_isvalid", length = 1)
	private Integer IIsValid;

	// @Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "gdt_entry")
	private Date dtEntry;

	public GbltUsersRolesTrn() {
		// TODO Auto-generated constructor stub
	}

	public Long getIRoleId() {
		return IRoleId;
	}

	public void setIRoleId(Long iRoleId) {
		IRoleId = iRoleId;
	}

	public String getIUserId() {
		return IUserId;
	}

	public void setIUserId(String iUserId) {
		IUserId = iUserId;
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

	public String getStOrgId() {
		return stOrgId;
	}

	public void setStOrgId(String stOrgId) {
		this.stOrgId = stOrgId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(IRoleId, IUserId, stOrgId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GbltUsersRolesTrn other = (GbltUsersRolesTrn) obj;
		return Objects.equals(IRoleId, other.IRoleId) && Objects.equals(IUserId, other.IUserId)
				&& Objects.equals(stOrgId, other.stOrgId);
	}

	@Override
	public String toString() {
		return "GbltUsersRolesTrn [IRoleId=" + IRoleId + ", IUserId=" + IUserId + ", IIsValid=" + IIsValid
				+ ", dtEntry=" + dtEntry + ", stOrgId=" + stOrgId + "]";
	}

}
