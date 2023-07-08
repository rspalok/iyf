package net.model.master;

import java.io.Serializable;
import java.util.Objects;

public class GbltUserMstPk implements Serializable{
	private static final long serialVersionUID = 1L;
	private String IUserId;
	private String stOrgId;
	public GbltUserMstPk() {
		// TODO Auto-generated constructor stub
	}
	public String getIUserId() {
		return IUserId;
	}
	public void setIUserId(String iUserId) {
		IUserId = iUserId;
	}
	public String getStOrgId() {
		return stOrgId;
	}
	public void setStOrgId(String stOrgId) {
		this.stOrgId = stOrgId;
	}
	@Override
	public int hashCode() {
		return Objects.hash(IUserId, stOrgId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GbltUserMstPk other = (GbltUserMstPk) obj;
		return Objects.equals(IUserId, other.IUserId) && Objects.equals(stOrgId, other.stOrgId);
	}
	
}
