package net.model.master;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity 
@Table(name = "iyf_batch_mst", schema = "iyf") 
@IdClass(IYFBatchMstPk.class)
public class IYFBatchMst implements Serializable{
	 
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "num_batch_id",length=4)
	private Long IBatch;
	
	@Column(name = "str_name", nullable = false, columnDefinition = "character varying (15)")
	private String stName;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_batch_start")
	private Date dtBatchStart;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_batch_end")
	private Date dtBatchEnd;
	
	@Column(name = "str_owner_id", nullable = false, columnDefinition = "character varying (15)")
	private String stOwnerId;
	
	@Id
	@Column(name = "str_org_id", nullable = false, columnDefinition = "character varying (20)")
	private String stOrgId;

	@Column(name = "num_isvalid",length=1)
	private Integer IsValid;
	
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_entry")
	private Date dtEntry;
    
    public IYFBatchMst() {
		// TODO Auto-generated constructor stub
	}

	public Long getIBatch() {
		return IBatch;
	}

	public void setIBatch(Long iBatch) {
		IBatch = iBatch;
	}

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	public Date getDtBatchStart() {
		return dtBatchStart;
	}

	public void setDtBatchStart(Date dtBatchStart) {
		this.dtBatchStart = dtBatchStart;
	}

	public Date getDtBatchEnd() {
		return dtBatchEnd;
	}

	public void setDtBatchEnd(Date dtBatchEnd) {
		this.dtBatchEnd = dtBatchEnd;
	}

	public String getStOwnerId() {
		return stOwnerId;
	}

	public void setStOwnerId(String stOwnerId) {
		this.stOwnerId = stOwnerId;
	}

	public String getStOrgId() {
		return stOrgId;
	}

	public void setStOrgId(String stOrgId) {
		this.stOrgId = stOrgId;
	}

	public Integer getIsValid() {
		return IsValid;
	}

	public void setIsValid(Integer isValid) {
		IsValid = isValid;
	}

	public Date getDtEntry() {
		return dtEntry;
	}

	public void setDtEntry(Date dtEntry) {
		this.dtEntry = dtEntry;
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
		IYFBatchMst other = (IYFBatchMst) obj;
		return Objects.equals(IBatch, other.IBatch) && Objects.equals(stOrgId, other.stOrgId);
	}

	@Override
	public String toString() {
		return "IYFBatchMst [IBatch=" + IBatch + ", stName=" + stName + ", dtBatchStart=" + dtBatchStart
				+ ", dtBatchEnd=" + dtBatchEnd + ", stOwnerId=" + stOwnerId + ", stOrgId=" + stOrgId + ", IsValid="
				+ IsValid + ", dtEntry=" + dtEntry + "]";
	}
    
}
