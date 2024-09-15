package net.model.transection.pojo.registration;

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
@Table(name = "gblt_otp_reg_trn", schema = "iyf")
@IdClass(GbltOtpStudentRegTrnPk.class)
public class GbltOtpStudentRegTrn implements Serializable{
	 
	private static final long serialVersionUID = 1L;

	@Id 
	@GenericGenerator(name = "gblt_otp_reg_trn",strategy  = "net.id.CustumIdGenerator")
	@GeneratedValue(generator = "gblt_otp_reg_trn") 
	@Column(name = "str_student_id", nullable = false, columnDefinition = "character varying (15)" ,unique = true)
	private String stStudentId;

	@Id
	@Column(name = "str_org_id", nullable = false, columnDefinition = "character varying (20)")
	private String stOrgId;
	
	@Column(name = "str_user_id", nullable = false, columnDefinition = "character varying (20)")
	private String stOwnerId;

	@Column(name = "str_first_name", columnDefinition = "character varying (100)")
	private String firstName;
	
	@Column(name = "str_last_name", columnDefinition = "character varying (100)")
	private String lastName;//stLastName;
	
	@Column(name = "str_email", columnDefinition = "character varying (50)")
	private String email;
	
	@Column(name = "str_address", columnDefinition = "character varying (200)")
	private String stAddress;
	
	@Column(name = "num_mobile",length=12)
	private Long IMobile;
	
	@Column(name = "num_chanting_round",length=3)
	private Long mChanting;
	
	@Column(name = "str_occupation", columnDefinition = "character varying (100)")
	private String stOccupation;
	
	@Column(name = "str_stage", columnDefinition = "character varying (50)")
	private String stStage;
	
	@Column(name = "num_counselor_id",length=12)
	private Integer ICounselor;
	
	@Column(name = "num_facilitator_id",length=12)
	private Integer IFacilitator;
	
	@Column(name = "num_isvalid",length=1)
	private Integer IIsValid;
	
	@Column(name = "num_org_unit",length=3)
	private Integer mOrgUnit;
	
	@Column(name = "num_reg_mode",length=1)
	private Integer mRegMode;//1 normal only registration , 2 on course registration, 
	//3 course attendance registration ,
	//5 yatra registration

	@DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_birth")
	private Date dtBirth;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_registration")
	private Date dtRegistration;
	
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_entry")
	private Date dtEntry;
    
	public GbltOtpStudentRegTrn() {
		// TODO Auto-generated constructor stub
	}
	
	public Integer getmOrgUnit() {
		return mOrgUnit;
	}

	public void setmOrgUnit(Integer mOrgUnit) {
		this.mOrgUnit = mOrgUnit;
	}

	public Date getDtBirth() {
		return dtBirth;
	}

	public void setDtBirth(Date dtBirth) {
		this.dtBirth = dtBirth;
	}

	public Long getmChanting() {
		return mChanting;
	}

	public void setmChanting(Long mChanting) {
		this.mChanting = mChanting;
	}

	public String getStStudentId() {
		return stStudentId;
	}

	public void setStStudentId(String stStudentId) {
		this.stStudentId = stStudentId;
	}

	public String getStOrgId() {
		return stOrgId;
	}

	public void setStOrgId(String stOrgId) {
		this.stOrgId = stOrgId;
	}

	public String getStOwnerId() {
		return stOwnerId;
	}

	public void setStOwnerId(String stOwnerId) {
		this.stOwnerId = stOwnerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStAddress() {
		return stAddress;
	}

	public void setStAddress(String stAddress) {
		this.stAddress = stAddress;
	}

	public Long getIMobile() {
		return IMobile;
	}

	public void setIMobile(Long iMobile) {
		IMobile = iMobile;
	}

	public String getStOccupation() {
		return stOccupation;
	}

	public void setStOccupation(String stOccupation) {
		this.stOccupation = stOccupation;
	}

	public String getStStage() {
		return stStage;
	}

	public void setStStage(String stStage) {
		this.stStage = stStage;
	}

	public Integer getICounselor() {
		return ICounselor;
	}

	public void setICounselor(Integer iCounselor) {
		ICounselor = iCounselor;
	}

	public Integer getIFacilitator() {
		return IFacilitator;
	}

	public void setIFacilitator(Integer iFacilitator) {
		IFacilitator = iFacilitator;
	}

	public Integer getIIsValid() {
		return IIsValid;
	}

	public void setIIsValid(Integer iIsValid) {
		IIsValid = iIsValid;
	}

	public Date getDtRegistration() {
		return dtRegistration;
	}

	public void setDtRegistration(Date dtRegistration) {
		this.dtRegistration = dtRegistration;
	}

	public Date getDtEntry() {
		return dtEntry;
	}

	public void setDtEntry(Date dtEntry) {
		this.dtEntry = dtEntry;
	}

	public Integer getmRegMode() {
		return mRegMode;
	}

	public void setmRegMode(Integer mRegMode) {
		this.mRegMode = mRegMode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(stOrgId, stStudentId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GbltOtpStudentRegTrn other = (GbltOtpStudentRegTrn) obj;
		return Objects.equals(stOrgId, other.stOrgId) && Objects.equals(stStudentId, other.stStudentId);
	}


	@Override
	public String toString() {
		return "GbltOtpStudentRegTrn [stStudentId=" + stStudentId + ", stOrgId=" + stOrgId + ", stOwnerId=" + stOwnerId
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", stAddress="
				+ stAddress + ", IMobile=" + IMobile + ", stOccupation=" + stOccupation + ", stStage=" + stStage
				+ ", ICounselor=" + ICounselor + ", IFacilitator=" + IFacilitator + ", IIsValid=" + IIsValid
				+ ", dtRegistration=" + dtRegistration + ", dtEntry=" + dtEntry + "]";
	}
}
