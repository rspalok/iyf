package net.model.transection.pojo.schedule;

import java.io.Serializable;
import java.util.Objects;
 

public class IyfClassSchedTrnPk implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long mIClassId;
	private String mStOrgId;
	private Long mICourseConfig;
	public Long getmIClassId() {
		return mIClassId;
	}
	public void setmIClassId(Long mIClassId) {
		this.mIClassId = mIClassId;
	}
	public String getmStOrgId() {
		return mStOrgId;
	}
	public void setmStOrgId(String mStOrgId) {
		this.mStOrgId = mStOrgId;
	}
	public Long getmICourseConfig() {
		return mICourseConfig;
	}
	public void setmICourseConfig(Long mICourseConfig) {
		this.mICourseConfig = mICourseConfig;
	}
	@Override
	public int hashCode() {
		return Objects.hash(mIClassId, mICourseConfig, mStOrgId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IyfClassSchedTrnPk other = (IyfClassSchedTrnPk) obj;
		return Objects.equals(mIClassId, other.mIClassId) && Objects.equals(mICourseConfig, other.mICourseConfig)
				&& Objects.equals(mStOrgId, other.mStOrgId);
	}
	
}
