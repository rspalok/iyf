package net.model.transection;

import java.io.Serializable;
import java.util.Objects;
 

public class IyfCourseAttenTrnPk implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long mIClassId;
	
	private String stStudentId;//stUserId;
	
	private Long mICourseConfig;
	private String mStOwnerId;
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
	public String getmStOwnerId() {
		return mStOwnerId;
	}
	public void setmStOwnerId(String mStOwnerId) {
		this.mStOwnerId = mStOwnerId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(mIClassId, mICourseConfig, mStOwnerId, stStudentId);
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
				&& Objects.equals(mStOwnerId, other.mStOwnerId) && Objects.equals(stStudentId, other.stStudentId);
	}

}
