package net.service.transection;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;

import net.model.GbltOtpStudentRegTrn;
import net.model.GbltUserMst;
import net.model.bean.FollowUpBean;
import net.model.master.FollowUpMaster;
import net.model.master.pojo.IYFCourseConfig;
import net.model.transection.FollowUpResponseTrn;
import net.model.transection.FollowUpTrn;

public interface FollowUpSer {

	public List<FollowUpMaster> getFolowUpList(HttpServletRequest request);

	public List<IYFCourseConfig> getCourseConfigList(HttpServletRequest request);

	public List<GbltUserMst> getCallerListFromOTPStudent(HttpServletRequest request);

	public void saveFollowUpDetails(FollowUpBean followUpBean, HttpServletRequest request) throws ParseException;

	public List<FollowUpResponseTrn> getFolowUpStudentList(HttpServletRequest request);

	public String getStudentForFolloup(String stStudentId, Integer followUpId, HttpServletRequest objRequest_p);

	public void saveFollowUpResponse(FollowUpBean followUpBean, HttpServletRequest request);

	public List<FollowUpResponseTrn> getMyFolowUpStudentList(HttpServletRequest request);

	public Page<FollowUpTrn> findPaginated(int pageNo, int pageSize, String sortField, String sortDir, HttpServletRequest request);

	public void saveFollowUpConfig(FollowUpBean followUpBean, HttpServletRequest request);

	public List<FollowUpTrn> getconfigFolowUpList(HttpServletRequest request);
	
	
}