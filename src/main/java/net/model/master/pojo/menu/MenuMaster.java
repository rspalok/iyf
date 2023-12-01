package net.model.master.pojo.menu;

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
@Table(name = "gblt_menu_master_trn", schema = "iyf")
@IdClass(MenuMasterPk.class)
public class MenuMaster implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "str_menu_id", nullable = false, columnDefinition = "character varying (15)")
	private String mStMenuId; 

	@Column(name = "str_display_order", nullable = false, columnDefinition = "character varying (15)")
	private String mStDisplayOrder; 

	@Column(name = "num_menu_level", nullable = false, length=2) 
	private Integer mIMenuLevel;

	@Column(name = "mum_menu_position", nullable = false, length=2)
	private Integer mIMenuPosition;

	@Column(name = "str_parent_id", nullable = false, columnDefinition = "character varying (15)")
	private String mStParentId;
	 
	@Column(name = "str_menu_name", nullable = false, columnDefinition = "character varying (225)")
	private String mStMenuName;

	@Column(name = "str_url", nullable = false, columnDefinition = "character varying (215)")
	private String mStURL;

	@Column(name = "str_module_name", nullable = false, columnDefinition = "character varying (215)")
	private String mStModuleName; 
	
	@Column(name = "str_user_id", nullable = false, columnDefinition = "character varying (15)")
	private String mStOwnerId;
	
	@Id
	@Column(name = "str_org_id", nullable = false, columnDefinition = "character varying (20)")
	private String mStOrgId;

	@Column(name = "num_isvalid",length=1)
	private Integer mIsValid;
	
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_entry")
	private Date mDtEntry;
    
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dt_last_modified")
	private Date mDtLastModified;
    
    public MenuMaster() {
		// TODO Auto-generated constructor stub
	}

	public String getmStMenuId() {
		return mStMenuId;
	}

	public void setmStMenuId(String mStMenuId) {
		this.mStMenuId = mStMenuId;
	}

	public String getmStDisplayOrder() {
		return mStDisplayOrder;
	}

	public void setmStDisplayOrder(String mStDisplayOrder) {
		this.mStDisplayOrder = mStDisplayOrder;
	}

	public Integer getmIMenuLevel() {
		return mIMenuLevel;
	}

	public void setmIMenuLevel(Integer mIMenuLevel) {
		this.mIMenuLevel = mIMenuLevel;
	}

	public Integer getmIMenuPosition() {
		return mIMenuPosition;
	}

	public void setmIMenuPosition(Integer mIMenuPosition) {
		this.mIMenuPosition = mIMenuPosition;
	}

	public String getmStParentId() {
		return mStParentId;
	}

	public void setmStParentId(String mStParentId) {
		this.mStParentId = mStParentId;
	}

	public String getmStMenuName() {
		return mStMenuName;
	}

	public void setmStMenuName(String mStMenuName) {
		this.mStMenuName = mStMenuName;
	}

	public String getmStURL() {
		return mStURL;
	}

	public void setmStURL(String mStURL) {
		this.mStURL = mStURL;
	}

	public String getmStModuleName() {
		return mStModuleName;
	}

	public void setmStModuleName(String mStModuleName) {
		this.mStModuleName = mStModuleName;
	}

	public String getmStOwnerId() {
		return mStOwnerId;
	}

	public void setmStOwnerId(String mStOwnerId) {
		this.mStOwnerId = mStOwnerId;
	}

	public String getmStOrgId() {
		return mStOrgId;
	}

	public void setmStOrgId(String mStOrgId) {
		this.mStOrgId = mStOrgId;
	}

	public Integer getmIsValid() {
		return mIsValid;
	}

	public void setmIsValid(Integer mIsValid) {
		this.mIsValid = mIsValid;
	}

	public Date getmDtEntry() {
		return mDtEntry;
	}

	public void setmDtEntry(Date mDtEntry) {
		this.mDtEntry = mDtEntry;
	}

	public Date getmDtLastModified() {
		return mDtLastModified;
	}

	public void setmDtLastModified(Date mDtLastModified) {
		this.mDtLastModified = mDtLastModified;
	}

	@Override
	public int hashCode() {
		return Objects.hash(mStMenuId, mStOrgId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MenuMaster other = (MenuMaster) obj;
		return Objects.equals(mStMenuId, other.mStMenuId) && Objects.equals(mStOrgId, other.mStOrgId);
	}

	@Override
	public String toString() {
		return "MenuMaster [mStMenuId=" + mStMenuId + ", mStDisplayOrder=" + mStDisplayOrder + ", mIMenuLevel="
				+ mIMenuLevel + ", mIMenuPosition=" + mIMenuPosition + ", mStParentId=" + mStParentId + ", mStMenuName="
				+ mStMenuName + ", mStURL=" + mStURL + ", mStModuleName=" + mStModuleName + ", mStOwnerId=" + mStOwnerId
				+ ", mStOrgId=" + mStOrgId + ", mIsValid=" + mIsValid + ", mDtEntry=" + mDtEntry + ", mDtLastModified="
				+ mDtLastModified + "]";
	}
	
}
