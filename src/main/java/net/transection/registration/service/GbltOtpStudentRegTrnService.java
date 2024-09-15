package net.transection.registration.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import net.model.master.pojo.menu.MenuMaster;
import net.model.transection.pojo.registration.GbltOtpStudentRegTrn;

public interface GbltOtpStudentRegTrnService {
	public List<GbltOtpStudentRegTrn> getAllGbltOtpStudentRegTrns(HttpServletRequest request);

	public GbltOtpStudentRegTrn getGbltOtpStudentRegTrnById(String id, HttpServletRequest objRequest_p);

	public void deleteGbltOtpStudentRegTrnById(GbltOtpStudentRegTrn gbltOtpStudentRegTrn, HttpServletRequest request);

	public Page<GbltOtpStudentRegTrn> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, HttpServletRequest objRequest_p);

	public List<GbltOtpStudentRegTrn> searchBy(String theName, HttpServletRequest request);

	public void saveGbltOtpStudentRegTrn(GbltOtpStudentRegTrn gbltOtpStudentRegTrn, HttpServletRequest request,
			HttpServletResponse response);

	public List<GbltOtpStudentRegTrn> getAllValidStudent(HttpServletRequest request);

	public Map<String, List<MenuMaster>> getMemuList(HttpServletRequest request);

	public String studentByMobileNo(Long mobileNumber, HttpServletRequest objRequest_p);

	public List<GbltOtpStudentRegTrn> allCurrentRegStudent( HttpServletRequest objRequest_p);

	public String studentByStudentId(String stStudentId, HttpServletRequest objRequest_p);

	
}
