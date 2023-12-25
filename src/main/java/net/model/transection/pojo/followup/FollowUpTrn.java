package net.model.transection.pojo.followup;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import net.model.master.pojo.followup.FollowUpMaster;
import net.model.transection.pojo.followup.FollowUpTrnPk;

@Entity
@Table(name = "iyf_follow_up_trn", schema = "iyf")
@IdClass(FollowUpTrnPk.class)
public class FollowUpTrn implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "iyf_follow_up_trn", strategy = "net.id.CustumIdGenerator")
	@GeneratedValue(generator = "iyf_follow_up_trn")
	@Column(name = "num_follow_up_id", nullable = false, length = 8, unique = true)
	private Integer followUpId;// generate

	
	@Column(name = "num_mst_id", nullable = false, length = 4)
	private Integer followUpMstId;

	@ManyToOne(fetch = FetchType.LAZY)
	// @JsonIgnore
	@JoinColumns({
			@JoinColumn(name = "str_org_id", referencedColumnName = "str_org_id", insertable = false, updatable = false),
			@JoinColumn(name = "num_mst_id", referencedColumnName = "num_mst_id", insertable = false, updatable = false) })
	private FollowUpMaster objFollowUpMaster;

	@Column(name = "str_name", nullable = false, columnDefinition = "character varying (115)")
	private String stName;

	@Column(name = "str_content", nullable = false, columnDefinition = "character varying (225)")
	private String stContent;

	@Column(name = "str_user_id", nullable = false, columnDefinition = "character varying (15)")
	private String stOwnerId;

	@Id
	@Column(name = "str_org_id", nullable = false, columnDefinition = "character varying (20)")
	private String stOrgId;

	@Column(name = "num_isvalid", length = 1)
	private Integer isValid;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_entry")
	private Date dtEntry;


	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_event")
	private Date dtEvent;
	
	public FollowUpTrn() {
		// TODO Auto-generated constructor stub
	}

	public Integer getFollowUpId() {
		return followUpId;
	}

	public Date getDtEvent() {
		return dtEvent;
	}

	public void setDtEvent(Date dtEvent) {
		this.dtEvent = dtEvent;
	}

	public void setFollowUpId(Integer followUpId) {
		this.followUpId = followUpId;
	}

	public Integer getFollowUpMstId() {
		return followUpMstId;
	}

	public void setFollowUpMstId(Integer followUpMstId) {
		this.followUpMstId = followUpMstId;
	}

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	public String getStContent() {
		return stContent;
	}

	public void setStContent(String stContent) {
		this.stContent = stContent;
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

	public FollowUpMaster getObjFollowUpMaster() {
		return objFollowUpMaster;
	}

	public void setObjFollowUpMaster(FollowUpMaster objFollowUpMaster) {
		this.objFollowUpMaster = objFollowUpMaster;
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
		FollowUpTrn other = (FollowUpTrn) obj;
		return Objects.equals(followUpId, other.followUpId)
				&& Objects.equals(stOrgId, other.stOrgId);
	}

	@Override
	public String toString() {
		return "FollowUpTrn [followUpId=" + followUpId + ", followUpMstId=" + followUpMstId + ", stName=" + stName
				+ ", stContent=" + stContent + ", stOwnerId=" + stOwnerId + ", stOrgId=" + stOrgId + ", isValid="
				+ isValid + ", dtEntry=" + dtEntry + "]";
	}

}