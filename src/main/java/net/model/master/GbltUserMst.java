package net.model.master;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "gblt_user_mst", schema = "iyf")
@IdClass(GbltUserMstPk.class)
public class GbltUserMst implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "str_user_id", nullable = false, columnDefinition = "character varying (15)")
	private String IUserId;// stUserId;

	@ManyToOne(fetch = FetchType.LAZY)
	//@JsonIgnore
	@JoinColumns({
        @JoinColumn(name="str_org_id", referencedColumnName="str_org_id" ,insertable=false, updatable=false),
        @JoinColumn(name="str_user_id", referencedColumnName="str_student_id",insertable=false, updatable=false)
    })
	private GbltOtpStudentRegTrn objGbltOtpStudentRegTrns ;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
        @JoinColumn(name="str_org_id", referencedColumnName="str_org_id" ,insertable=false, updatable=false)
    })
	private GbltOrgMst objGbltOrgMst;
	
	@Column(name = "str_username", nullable = false, columnDefinition = "character varying (20)")
	private String stUserName;// stEmail; occupation

	@Column(name = "str_first_name", columnDefinition = "character varying (100)")
	private String stFirstName;

	@Column(name = "str_last_name", columnDefinition = "character varying (20)")
	private String stLastName;// stLastName;

	@Column(name = "str_email", columnDefinition = "character varying (20)")
	private String stEmail;// stEmail; occupation

	@Column(name = "str_password", nullable = false, columnDefinition = "character varying (68)")
	private String stPassword;// stEmail; occupation

	@Column(name = "num_isvalid", length = 1)
	private Integer IIsValid;
	
	@Column(name = "enabled", length = 1)
	private boolean mEnabled;

	@Id
	@Column(name = "str_org_id", columnDefinition = "character varying (20)")
	private String stOrgId;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "gblt_user_role_trn", schema = "iyf", joinColumns = { @JoinColumn(name = "str_user_id"),
			@JoinColumn(name = "str_orgId") }, inverseJoinColumns = @JoinColumn(name = "num_role_id"))
	private Set<GbltRolMst> roles = new HashSet<>();

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "gdt_entry")
	private Date dtEntry;

	public GbltUserMst() {
		// TODO Auto-generated constructor stub
	}
	
	public GbltOrgMst getObjGbltOrgMst() {
		return objGbltOrgMst;
	}

	public void setObjGbltOrgMst(GbltOrgMst objGbltOrgMst) {
		this.objGbltOrgMst = objGbltOrgMst;
	}
	
	public String getIUserId() {
		return IUserId;
	}

	public GbltOtpStudentRegTrn getObjGbltOtpStudentRegTrns() {
		return objGbltOtpStudentRegTrns;
	}

	public void setObjGbltOtpStudentRegTrns(GbltOtpStudentRegTrn objGbltOtpStudentRegTrns) {
		this.objGbltOtpStudentRegTrns = objGbltOtpStudentRegTrns;
	}

	public boolean getmEnabled() {
		return mEnabled;
	}

	public void setmEnabled(boolean mEnabled) {
		this.mEnabled = mEnabled;
	}

	public void setIUserId(String iUserId) {
		IUserId = iUserId;
	}

	public String getStUserName() {
		return stUserName;
	}

	public void setStUserName(String stUserName) {
		this.stUserName = stUserName;
	}

	public String getStFirstName() {
		return stFirstName;
	}

	public void setStFirstName(String stFirstName) {
		this.stFirstName = stFirstName;
	}

	public String getStLastName() {
		return stLastName;
	}

	public void setStLastName(String stLastName) {
		this.stLastName = stLastName;
	}

	public String getStEmail() {
		return stEmail;
	}

	public void setStEmail(String stEmail) {
		this.stEmail = stEmail;
	}

	public String getStPassword() {
		return stPassword;
	}

	public void setStPassword(String stPassword) {
		this.stPassword = stPassword;
	}

	public Integer getIIsValid() {
		return IIsValid;
	}

	public void setIIsValid(Integer iIsValid) {
		IIsValid = iIsValid;
	}

	public String getStOrgId() {
		return stOrgId;
	}

	public void setStOrgId(String stOrgId) {
		this.stOrgId = stOrgId;
	}

	public Set<GbltRolMst> getRoles() {
		return roles;
	}

	public void setRoles(Set<GbltRolMst> roles) {
		this.roles = roles;
	}

	public Date getDtEntry() {
		return dtEntry;
	}

	public void setDtEntry(Date dtEntry) {
		this.dtEntry = dtEntry;
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
		GbltUserMst other = (GbltUserMst) obj;
		return Objects.equals(IUserId, other.IUserId) && Objects.equals(stOrgId, other.stOrgId);
	}

	@Override
	public String toString() {
		return "GbltUserMst [IUserId=" + IUserId + ", stUserName=" + stUserName + ", stFirstName=" + stFirstName
				+ ", stLastName=" + stLastName + ", stEmail=" + stEmail + ", stPassword=" + stPassword + ", IIsValid="
				+ IIsValid + ", stOrgId=" + stOrgId + ", roles=" + roles + ", dtEntry=" + dtEntry + "]";
	}

}
