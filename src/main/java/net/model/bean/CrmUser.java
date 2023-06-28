package net.model.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import net.validator.FieldMatch;
import net.validator.ValidEmail;

@FieldMatch.List({
    @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
public class CrmUser {

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String userName;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String password;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String matchingPassword;

	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String registrationId;

	//@NotNull(message = "is required")
	//@Size(min = 1, message = "is required")

	private String firstName;
	private String lastName;

	@ValidEmail
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String email;
	
	//@NotNull(message = "is required")
	//@Size(min = 1, message = "is required")
	private Long RoleName;
	
	//@NotNull(message = "is required")
	//@Size(min = 1, message = "is required")
	private String OrgName;

	public CrmUser() {

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Long getRoleName() {
		return RoleName;
	}

	public void setRoleName(Long roleName) {
		RoleName = roleName;
	}

	public String getOrgName() {
		return OrgName;
	}

	public void setOrgName(String orgName) {
		OrgName = orgName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
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

	@Override
	public String toString() {
		return "CrmUser [userName=" + userName + ", password=" + password + ", matchingPassword=" + matchingPassword
				+ ", registrationId=" + registrationId + ", lastName=" + lastName + ", email=" + email + ", RoleName=" + RoleName
				+ ", OrgName=" + OrgName + "]";
	}

}
