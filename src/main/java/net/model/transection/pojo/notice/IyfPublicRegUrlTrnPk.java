package net.model.transection.pojo.notice;

import java.io.Serializable;
import java.util.Objects;
 
import javax.persistence.Embeddable;  


@Embeddable
public class IyfPublicRegUrlTrnPk implements Serializable {

	private static final long serialVersionUID = 1L;
 
	private Long mICourseConfig;
 
	private Integer publishId;
	
	private String mStOrgId;

	public IyfPublicRegUrlTrnPk() {
		// TODO Auto-generated constructor stub
	}

	public IyfPublicRegUrlTrnPk(Long mICourseConfig, Integer publishId) {
		super();
		this.mICourseConfig = mICourseConfig;
		this.publishId = publishId;
	}

	public Long getmICourseConfig() {
		return mICourseConfig;
	}

	public void setmICourseConfig(Long mICourseConfig) {
		this.mICourseConfig = mICourseConfig;
	}

	public Integer getPublishId() {
		return publishId;
	}

	public void setPublishId(Integer publishId) {
		this.publishId = publishId;
	}

	public String getmStOrgId() {
		return mStOrgId;
	}

	public void setmStOrgId(String mStOrgId) {
		this.mStOrgId = mStOrgId;
	}
 
	@Override
	public int hashCode() {
		return Objects.hash(mICourseConfig, mStOrgId, publishId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IyfPublicRegUrlTrnPk other = (IyfPublicRegUrlTrnPk) obj;
		return Objects.equals(mICourseConfig, other.mICourseConfig) && Objects.equals(mStOrgId, other.mStOrgId)
				&& Objects.equals(publishId, other.publishId);
	}

}
