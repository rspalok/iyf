package net.model.transection.pojo;

import java.io.Serializable;
import java.util.Objects;

public class BlockedStudentPk implements Serializable {
	private static final long serialVersionUID = 1L;
	private String stOrgId;
	private String stStudentId;
	public BlockedStudentPk() {
		// TODO Auto-generated constructor stub
	}
	
	public String getStOrgId() {
		return stOrgId;
	}

	public void setStOrgId(String stOrgId) {
		this.stOrgId = stOrgId;
	}

	public String getStStudentId() {
		return stStudentId;
	}
	public void setStStudentId(String stStudentId) {
		this.stStudentId = stStudentId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(stOrgId, stStudentId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlockedStudentPk other = (BlockedStudentPk) obj;
		return Objects.equals(stOrgId, other.stOrgId) && Objects.equals(stStudentId, other.stStudentId);
	}
	
}
