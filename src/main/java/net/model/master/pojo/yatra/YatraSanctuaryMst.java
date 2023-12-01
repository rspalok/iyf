package net.model.master.pojo.yatra;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "yatra_sanctuary_mst", schema = "iyf")
public class YatraSanctuaryMst implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "str_sanctuary_id", length = 4)
	private Long yatraSanctuaryId;

	@Column(name = "str_name", nullable = false, columnDefinition = "character varying (115)", unique = true)
	private String stName;// stUserId;

	@Column(name = "str_about", nullable = false)
	private String stAbout;

	@Column(name = "str_significance")
	private String stSignificance;

	@Column(name = "str_place_to_visit")
	private String stPlaceToVisit;

	@Column(name = "str_user_id", nullable = false, columnDefinition = "character varying (15)")
	private String mStUserId;

	@Column(name = "num_isvalid", length = 1)
	private Integer mIsValid;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_entry")
	private Date mDtEntry;

	public YatraSanctuaryMst() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getYatraSanctuaryId() {
		return yatraSanctuaryId;
	}

	public void setYatraSanctuaryId(Long yatraSanctuaryId) {
		this.yatraSanctuaryId = yatraSanctuaryId;
	}

	public String getStName() {
		return stName;
	}

	public void setStName(String stName) {
		this.stName = stName;
	}

	public String getStAbout() {
		return stAbout;
	}

	public void setStAbout(String stAbout) {
		this.stAbout = stAbout;
	}

	public String getStSignificance() {
		return stSignificance;
	}

	public void setStSignificance(String stSignificance) {
		this.stSignificance = stSignificance;
	}

	public String getStPlaceToVisit() {
		return stPlaceToVisit;
	}

	public void setStPlaceToVisit(String stPlaceToVisit) {
		this.stPlaceToVisit = stPlaceToVisit;
	}

	public String getmStUserId() {
		return mStUserId;
	}

	public void setmStUserId(String mStUserId) {
		this.mStUserId = mStUserId;
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

	@Override
	public String toString() {
		return "YatraSanctuaryMst [mIYatraSanctuaryId=" + yatraSanctuaryId + ", stName=" + stName + ", stAbout="
				+ stAbout + ", stSignificance=" + stSignificance + ", stPlaceToVisit=" + stPlaceToVisit + ", mStUserId="
				+ mStUserId + ", mIsValid=" + mIsValid + ", mDtEntry=" + mDtEntry + "]";
	}


}
