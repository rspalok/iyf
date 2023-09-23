package net.model.master;

import java.io.Serializable;
import java.util.Objects;

public class GbltOrgUnitMstPk implements Serializable {

	private static final long serialVersionUID = 1L;

	private String stOrgId;
	
	private Integer mOrgUnit;
	public GbltOrgUnitMstPk() {
		// TODO Auto-generated constructor stub
	}
	public String getStOrgId() {
		return stOrgId;
	}
	public void setStOrgId(String stOrgId) {
		this.stOrgId = stOrgId;
	}
	public Integer getmOrgUnit() {
		return mOrgUnit;
	}
	public void setmOrgUnit(Integer mOrgUnit) {
		this.mOrgUnit = mOrgUnit;
	}
	@Override
	public int hashCode() {
		return Objects.hash(mOrgUnit, stOrgId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GbltOrgUnitMstPk other = (GbltOrgUnitMstPk) obj;
		return Objects.equals(mOrgUnit, other.mOrgUnit) && Objects.equals(stOrgId, other.stOrgId);
	}
	
}
