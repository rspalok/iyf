package net.model.transection;

import java.io.Serializable;
import java.util.Objects;
 

public class IyfCourseAttenTrnPk implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long mIClassId;
	
	private String stStudentId;//stUserId;
	
	private Long mICourseConfig;
	private String mStOrgId;
	public IyfCourseAttenTrnPk() {
		// TODO Auto-generated constructor stub
	}
	public Long getmIClassId() {
		return mIClassId;
	}
	public void setmIClassId(Long mIClassId) {
		this.mIClassId = mIClassId;
	}
	public String getStStudentId() {
		return stStudentId;
	}
	public void setStStudentId(String stStudentId) {
		this.stStudentId = stStudentId;
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
	@Override
	public int hashCode() {
		return Objects.hash(mIClassId, mICourseConfig, mStOrgId, stStudentId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IyfCourseAttenTrnPk other = (IyfCourseAttenTrnPk) obj;
		return Objects.equals(mIClassId, other.mIClassId) && Objects.equals(mICourseConfig, other.mICourseConfig)
				&& Objects.equals(mStOrgId, other.mStOrgId) && Objects.equals(stStudentId, other.stStudentId);
	}

}
