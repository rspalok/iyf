package net.model.master.pojo.course;

import java.io.Serializable;
import java.util.Objects;

public class IyfCourseMstPk implements Serializable{
	 
	private static final long serialVersionUID = 1L;

	private Integer mICourse;
	private String mStOrgId;

	
    public IyfCourseMstPk() {
		// TODO Auto-generated constructor stub
	}

	public Integer getmICourse() {
		return mICourse;
	}

	public void setmICourse(Integer mICourse) {
		this.mICourse = mICourse;
	}

	
	public String getmStOrgId() {
		return mStOrgId;
	}

	public void setmStOrgId(String mStOrgId) {
		this.mStOrgId = mStOrgId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mICourse, mStOrgId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IyfCourseMstPk other = (IyfCourseMstPk) obj;
		return Objects.equals(mICourse, other.mICourse) && Objects.equals(mStOrgId, other.mStOrgId);
	}

	

	 
}
