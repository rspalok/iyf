package net.model.transection.pojo.yatra;

import java.io.Serializable;
import java.util.Objects;

public class YatraRegTrnPk implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long yatraCruiseId;
	private String stStudentId;// stUserId;
	private String stOrgId;

	public YatraRegTrnPk() {
		// TODO Auto-generated constructor stub
	}


	public String getStStudentId() {
		return stStudentId;
	}

	public void setStStudentId(String stStudentId) {
		this.stStudentId = stStudentId;
	}


	public Long getYatraCruiseId() {
		return yatraCruiseId;
	}


	public void setYatraCruiseId(Long yatraCruiseId) {
		this.yatraCruiseId = yatraCruiseId;
	}


	public String getStOrgId() {
		return stOrgId;
	}


	public void setStOrgId(String stOrgId) {
		this.stOrgId = stOrgId;
	}


	@Override
	public int hashCode() {
		return Objects.hash(stOrgId, stStudentId, yatraCruiseId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		YatraRegTrnPk other = (YatraRegTrnPk) obj;
		return Objects.equals(stOrgId, other.stOrgId) && Objects.equals(stStudentId, other.stStudentId)
				&& Objects.equals(yatraCruiseId, other.yatraCruiseId);
	}

	
	
}
