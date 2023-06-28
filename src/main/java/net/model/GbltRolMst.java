package net.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "gblt_role_mst", schema = "iyf")
public class GbltRolMst implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "sequence_gen", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "sequence_gen", sequenceName = "role_seq", initialValue = 1, allocationSize = 1)
	@Column(name = "num_role_id", nullable = false, length = 4, unique = true)
	private Integer IRoleId;

	@Column(name = "str_role_name", nullable = false, length = 15, unique = true)
	private String stName;
	
	@Column(name = "num_isvalid", length = 1)
	private Integer IIsValid;
 
	 
	// @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "gdt_entry")
	private Date dtEntry;

    public GbltRolMst() {
		// TODO Auto-generated constructor stub
	}
	public Integer getIRoleId() {
		return IRoleId;
	}

	public void setIRoleId(Integer iRoleId) {
		IRoleId = iRoleId;
	}

	
	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	public Integer getIIsValid() {
		return IIsValid;
	}

	public void setIIsValid(Integer iIsValid) {
		IIsValid = iIsValid;
	}

	
	public Date getDtEntry() {
		return dtEntry;
	}

	public void setDtEntry(Date dtEntry) {
		this.dtEntry = dtEntry;
	}

	@Override
	public String toString() {
		return "GbltRolMst [IRoleId=" + IRoleId +  ", stName=" + stName + ", IIsValid="
				+ IIsValid + ", stOrgId="  + ", dtEntry=" + dtEntry + "]";
	}
	
}
