package net.model.transection.pojo.registration;

import java.io.Serializable;
import java.util.Objects;

public class GbltOtpStudentRegTrnPk implements Serializable{
	private static final long serialVersionUID = 1L;
	private String stStudentId;
	private String stOrgId;
	
	public GbltOtpStudentRegTrnPk() {
		// TODO Auto-generated constructor stub
	}

	public GbltOtpStudentRegTrnPk(String stStudentId, String stOrgId) {
		super();
		this.stStudentId = stStudentId;
		this.stOrgId = stOrgId;
	}

	public String getStStudentId() {
		return stStudentId;
	}

	public void setStStudentId(String stStudentId) {
		this.stStudentId = stStudentId;
	}

	public String getStOrgId() {
		return stOrgId;
	}

	public void setStOrgId(String stOrgId) {
		this.stOrgId = stOrgId;
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
		GbltOtpStudentRegTrnPk other = (GbltOtpStudentRegTrnPk) obj;
		return Objects.equals(stOrgId, other.stOrgId) && Objects.equals(stStudentId, other.stStudentId);
	}

	@Override
	public String toString() {
		return "GbltOtpStudentRegTrnPk [stStudentId=" + stStudentId + ", stOrgId=" + stOrgId + "]";
	}
	
}
