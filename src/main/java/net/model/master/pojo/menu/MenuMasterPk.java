package net.model.master.pojo.menu;

import java.io.Serializable;
import java.util.Objects;

public class MenuMasterPk implements Serializable{

	private static final long serialVersionUID = 1L;
	private String mStMenuId; 
	private String mStOrgId;
	public String getmStMenuId() {
		return mStMenuId;
	}
	public void setmStMenuId(String mStMenuId) {
		this.mStMenuId = mStMenuId;
	}
	public String getmStOrgId() {
		return mStOrgId;
	}
	public void setmStOrgId(String mStOrgId) {
		this.mStOrgId = mStOrgId;
	}
	 public MenuMasterPk() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(mStMenuId, mStOrgId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuMasterPk other = (MenuMasterPk) obj;
		return Objects.equals(mStMenuId, other.mStMenuId) && Objects.equals(mStOrgId, other.mStOrgId);
	}
	
}
