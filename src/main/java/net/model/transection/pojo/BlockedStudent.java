package net.model.transection.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import net.model.master.pojo.classtype.BlackListTypeMst;

@Entity
@Table(name = "iyf_black_listed_student", schema = "iyf")
@IdClass(BlockedStudentPk.class)
public class BlockedStudent implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	 
	@Id
	@Column(name = "str_student_id", nullable = false, columnDefinition = "character varying (15)" ,unique = true)
	private String stStudentId;
	
	@Column(name = "num_black_list_type", nullable = false, length = 2)
	private String mBlackListType;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
        @JoinColumn(name="str_org_id", referencedColumnName="str_org_id" ,insertable=false, updatable=false),
        @JoinColumn(name="num_black_list_type", referencedColumnName="num_black_list_type",insertable=false, updatable=false)
    })
	//@ManyToOne(fetch = FetchType.LAZY)
	private BlackListTypeMst objBlackListTypeMst ;
	
	@Column(name = "str_remarks", nullable = false, columnDefinition = "character varying (20)")
	private String remarks;

	@Id
	@Column(name = "str_org_id", nullable = false, columnDefinition = "character varying (20)")
	private String stOrgId;
	
	@Column(name = "num_isvalid",length=1)
	private Integer IIsValid;
	
	@Column(name = "str_user_id", nullable = false, columnDefinition = "character varying (20)")
	private String stOwnerId;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_entry")
	private Date dtEntry;
    
    public BlockedStudent() {
		// TODO Auto-generated constructor stub
	}

	public String getStStudentId() {
		return stStudentId;
	}

	public void setStStudentId(String stStudentId) {
		this.stStudentId = stStudentId;
	}

	public String getmBlackListType() {
		return mBlackListType;
	}

	public void setmBlackListType(String mBlackListType) {
		this.mBlackListType = mBlackListType;
	}

	public BlackListTypeMst getObjBlackListTypeMst() {
		return objBlackListTypeMst;
	}

	public void setObjBlackListTypeMst(BlackListTypeMst objBlackListTypeMst) {
		this.objBlackListTypeMst = objBlackListTypeMst;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStOrgId() {
		return stOrgId;
	}

	public void setStOrgId(String stOrgId) {
		this.stOrgId = stOrgId;
	}

	public Integer getIIsValid() {
		return IIsValid;
	}

	public void setIIsValid(Integer iIsValid) {
		IIsValid = iIsValid;
	}


	public String getStOwnerId() {
		return stOwnerId;
	}

	public void setStOwnerId(String stOwnerId) {
		this.stOwnerId = stOwnerId;
	}

	public Date getDtEntry() {
		return dtEntry;
	}

	public void setDtEntry(Date dtEntry) {
		this.dtEntry = dtEntry;
	}

	

	@Override
	public String toString() {
		return "BlockedStudent [stStudentId=" + stStudentId + ", mBlackListType=" + mBlackListType + ", remarks="
				+ remarks + ", stOrgId=" + stOrgId + ", IIsValid=" + IIsValid + ", stOwnerId=" + stOwnerId
				+ ", dtEntry=" + dtEntry + "]";
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
		BlockedStudent other = (BlockedStudent) obj;
		return Objects.equals(stOrgId, other.stOrgId) && Objects.equals(stStudentId, other.stStudentId);
	}

	
    
    
}
