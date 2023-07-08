package net.model.master;

import java.io.Serializable;
import java.util.Objects;

public class IYFBatchMstPk implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long IBatch;

	private String stOrgId;

	public IYFBatchMstPk() {
		// TODO Auto-generated constructor stub
	}

	public Long getIBatch() {
		return IBatch;
	}

	public void setIBatch(Long iBatch) {
		IBatch = iBatch;
	}

	public String getStOrgId() {
		return stOrgId;
	}

	public void setStOrgId(String stOrgId) {
		this.stOrgId = stOrgId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(IBatch, stOrgId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IYFBatchMstPk other = (IYFBatchMstPk) obj;
		return Objects.equals(IBatch, other.IBatch) && Objects.equals(stOrgId, other.stOrgId);
	}

}
