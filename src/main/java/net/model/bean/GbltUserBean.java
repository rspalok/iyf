package net.model.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import net.model.master.pojo.role.GbltRolMst;
 
public class GbltUserBean implements Serializable {

	private static final long serialVersionUID = 1L;
 
	private String IUserId;// stUserId;
 
	private String stUserName;// stEmail; occupation
 
	private String stFirstName;
 
	private String stLastName;// stLastName;
 
	private String stEmail;// stEmail; occupation
 
	private String stPassword;// stEmail; occupation
 
	private Integer IIsValid;
	 
	private boolean mEnabled;
 
	private String stOrgId;

	private String stOrgNameId;
	
	private Set<GbltRolMst> roles = new HashSet<>();

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy") 
	private Date dtEntry;

	public GbltUserBean() {
		// TODO Auto-generated constructor stub
	}

	public String getIUserId() {
		return IUserId;
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

	public boolean ismEnabled() {
		return mEnabled;
	}

	public void setmEnabled(boolean mEnabled) {
		this.mEnabled = mEnabled;
	}

	public String getStOrgId() {
		return stOrgId;
	}

	public void setStOrgId(String stOrgId) {
		this.stOrgId = stOrgId;
	}

	public String getStOrgNameId() {
		return stOrgNameId;
	}

	public void setStOrgNameId(String stOrgNameId) {
		this.stOrgNameId = stOrgNameId;
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
	public String toString() {
		return "GbltUserBean [IUserId=" + IUserId + ", stUserName=" + stUserName + ", stFirstName=" + stFirstName
				+ ", stLastName=" + stLastName + ", stEmail=" + stEmail + ", stPassword=" + stPassword + ", IIsValid="
				+ IIsValid + ", mEnabled=" + mEnabled + ", stOrgId=" + stOrgId + ", stOrgNameId=" + stOrgNameId
				+ ", roles=" + roles + ", dtEntry=" + dtEntry + "]";
	}
	

}
