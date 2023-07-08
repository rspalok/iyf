package net.model.transection;

import java.io.Serializable;
import java.util.Objects;
 
public class IYFCourseConfigPk implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long mICourseConfig; 
	private String mStOrgId;

	public IYFCourseConfigPk() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(mICourseConfig, mStOrgId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IYFCourseConfigPk other = (IYFCourseConfigPk) obj;
		return Objects.equals(mICourseConfig, other.mICourseConfig) && Objects.equals(mStOrgId, other.mStOrgId);
	}

	public Long getmICourseConfig() {
		return mICourseConfig;
	}

	public void setmICourseConfig(Long mICourseConfig) {
		this.mICourseConfig = mICourseConfig;
	}

	public String getmStOrgId() {
		return mStOrgId;
	}

	public void setmStOrgId(String mStOrgId) {
		this.mStOrgId = mStOrgId;
	}

}
