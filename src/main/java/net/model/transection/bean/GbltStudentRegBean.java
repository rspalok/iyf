package net.model.transection.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GbltStudentRegBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String stStudentId;

	private String stOrgId;

	private String stOwnerId;

	private String mBlackListType;

	// @Size(max = 100, min = 3, message = "{GbltStudentRegBean.firstName.invalid}")
	@NotEmpty(message = "Please enter First Name")
	@Size(min = 2, max = 30, message = "First Name must be between 2 and 30 characters")
	private String firstName;

	// @Size(max = 100, min = 3, message = "{GbltStudentRegBean.lastName.invalid}")
	@NotEmpty(message = "Please enter First Name")
	@Size(min = 2, max = 30, message = "Last Name must be between 2 and 30 characters")
	private String lastName;// stLastName;

	@NotEmpty(message = "Email cannot be empty")
	@Email(message = "Email should be valid")
	private String email;

	@NotBlank(message = "Name cannot be blank")
	@Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
	private String stAddress;

	@NotEmpty(message = "Mobile number cannot be empty")
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Mobile number should be 10 digits and start with 6, 7, 8, or 9")
	private Long IMobile;

	private Long mChanting;

	private String stOccupation;

	private String stStage;

	private Integer ICounselor;

	private Integer IFacilitator;

	private Integer IIsValid;

	private Integer mOrgUnit;

	private Integer mRegMode;

	private String remarks;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtBirth;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtRegistration;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtEntry;

	public GbltStudentRegBean() {
		// TODO Auto-generated constructor stub
	}

	public String getRemarks() {
		return remarks;
	}

	public String getmBlackListType() {
		return mBlackListType;
	}

	public void setmBlackListType(String mBlackListType) {
		this.mBlackListType = mBlackListType;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public Long getmChanting() {
		return mChanting;
	}

	public void setmChanting(Long mChanting) {
		this.mChanting = mChanting;
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

	public Integer getmOrgUnit() {
		return mOrgUnit;
	}

	public void setmOrgUnit(Integer mOrgUnit) {
		this.mOrgUnit = mOrgUnit;
	}

	public Integer getmRegMode() {
		return mRegMode;
	}

	public void setmRegMode(Integer mRegMode) {
		this.mRegMode = mRegMode;
	}

	public Date getDtBirth() {
		return dtBirth;
	}

	public void setDtBirth(Date dtBirth) {
		this.dtBirth = dtBirth;
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

}
