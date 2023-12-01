package net.model.master.pojo.followup;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;


@Entity 
@Table(name = "iyf_follow_up_mst", schema = "iyf")
@IdClass(FollowUpMasterPk.class)
public class FollowUpMaster  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "iyf_follow_up_mst", strategy = "net.id.CustumIdGenerator")
	@GeneratedValue(generator = "iyf_follow_up_mst")
	@Column(name = "num_mst_id", nullable = false, length=8)
	private Integer followUpId;

	@Column(name = "str_name", nullable = false, columnDefinition = "character varying (225)")
	private String stName;
	
	@Column(name = "str_user_id", nullable = false, columnDefinition = "character varying (15)")
	private String stOwnerId;
	
	@Id
	@Column(name = "str_org_id", nullable = false, columnDefinition = "character varying (20)")
	private String stOrgId;

	@Column(name = "num_isvalid",length=1)
	private Integer isValid;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_event")
	private Date dtEvent;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_entry")
	private Date dtEntry;
    
    public FollowUpMaster() {
		// TODO Auto-generated constructor stub
	}

	public Date getDtEvent() {
		return dtEvent;
	}

	public void setDtEvent(Date dtEvent) {
		this.dtEvent = dtEvent;
	}

	public Integer getFollowUpId() {
		return followUpId;
	}

	public void setFollowUpId(Integer followUpId) {
		this.followUpId = followUpId;
	}
	
	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
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
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Date getDtEntry() {
		return dtEntry;
	}

	public void setDtEntry(Date dtEntry) {
		this.dtEntry = dtEntry;
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
		FollowUpMaster other = (FollowUpMaster) obj;
		return Objects.equals(followUpId, other.followUpId) && Objects.equals(stOrgId, other.stOrgId);
	}

	@Override
	public String toString() {
		return "FallowUpMaster [followUpId=" + followUpId + ", stName=" + stName + ", stOwnerId=" + stOwnerId
				+ ", stOrgId=" + stOrgId + ", isValid=" + isValid + ", dtEntry=" + dtEntry + "]";
	}
    
}
