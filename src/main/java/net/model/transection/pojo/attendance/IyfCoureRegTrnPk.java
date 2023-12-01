package net.model.transection.pojo.attendance;

import java.io.Serializable;
import java.util.Objects;
 
import javax.persistence.Embeddable;  


@Embeddable
public class IyfCoureRegTrnPk implements Serializable {

	private static final long serialVersionUID = 1L;
 
	private Long mICourseConfig;
 
	private String stStudentId;

	public IyfCoureRegTrnPk() {
		// TODO Auto-generated constructor stub
	}

	public IyfCoureRegTrnPk(Long mICourseConfig, String stStudentId) {
		super();
		this.mICourseConfig = mICourseConfig;
		this.stStudentId = stStudentId;
	}

	public Long getmICourseConfig() {
		return mICourseConfig;
	}

	public void setmICourseConfig(Long mICourseConfig) {
		this.mICourseConfig = mICourseConfig;
	}

	public String getStStudentId() {
		return stStudentId;
	}

	public void setStStudentId(String stStudentId) {
		this.stStudentId = stStudentId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IyfCoureRegTrnPk other = (IyfCoureRegTrnPk) obj;
		return Objects.equals(mICourseConfig, other.mICourseConfig) && Objects.equals(stStudentId, other.stStudentId);
	}

}
