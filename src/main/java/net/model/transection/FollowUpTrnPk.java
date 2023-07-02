package net.model.transection;

import java.io.Serializable;
import java.util.Objects;

public class FollowUpTrnPk implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer followUpId;
	private String stOrgId;
	
	public FollowUpTrnPk() {
		// TODO Auto-generated constructor stub
	}

	public Integer getFollowUpId() {
		return followUpId;
	}

	public void setFollowUpId(Integer followUpId) {
		this.followUpId = followUpId;
	}

	public String getStOrgId() {
		return stOrgId;
	}

	public void setStOrgId(String stOrgId) {
		this.stOrgId = stOrgId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(followUpId, stOrgId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FollowUpTrnPk other = (FollowUpTrnPk) obj;
		return Objects.equals(followUpId, other.followUpId) 
				&& Objects.equals(stOrgId, other.stOrgId);
	}
	
}
