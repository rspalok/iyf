package net.service.transection;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import net.model.master.GbltOtpStudentRegTrn;
import net.model.master.MenuMaster;

public interface GbltOtpStudentRegTrnService {
	List<GbltOtpStudentRegTrn> getAllGbltOtpStudentRegTrns(HttpServletRequest request);

	GbltOtpStudentRegTrn getGbltOtpStudentRegTrnById(String id, HttpServletRequest objRequest_p);

	void deleteGbltOtpStudentRegTrnById(GbltOtpStudentRegTrn employee, HttpServletRequest request);

	Page<GbltOtpStudentRegTrn> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, HttpServletRequest objRequest_p);

	List<GbltOtpStudentRegTrn> searchBy(String theName, HttpServletRequest request);

	void saveGbltOtpStudentRegTrn(GbltOtpStudentRegTrn employee, HttpServletRequest request,
			HttpServletResponse response);

	List<GbltOtpStudentRegTrn> getAllValidStudent(HttpServletRequest request);

	Map<String, List<MenuMaster>> getMemuList(HttpServletRequest request);

	String studentByMobileNo(Long mobileNumber, HttpServletRequest objRequest_p);

	List<GbltOtpStudentRegTrn> allCurrentRegStudent( HttpServletRequest objRequest_p);
}
