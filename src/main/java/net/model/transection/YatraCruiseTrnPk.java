package net.model.transection;

import java.io.Serializable;
import java.util.Objects;

public class YatraCruiseTrnPk implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long yatraCruiseId;
	private String mStOrgId;

	public YatraCruiseTrnPk() {
		// TODO Auto-generated constructor stub
	}


	public Long getYatraCruiseId() {
		return yatraCruiseId;
	}


	public void setYatraCruiseId(Long yatraCruiseId) {
		this.yatraCruiseId = yatraCruiseId;
	}


	public String getmStOrgId() {
		return mStOrgId;
	}

	public void setmStOrgId(String mStOrgId) {
		this.mStOrgId = mStOrgId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(yatraCruiseId, mStOrgId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		YatraCruiseTrnPk other = (YatraCruiseTrnPk) obj;
		return Objects.equals(yatraCruiseId, other.yatraCruiseId) && Objects.equals(mStOrgId, other.mStOrgId);
	}


	
}
