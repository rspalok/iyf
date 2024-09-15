package net.transection.decision.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import net.model.transection.bean.GbltStudentRegBean;
import net.model.transection.pojo.registration.GbltOtpStudentRegTrn;

public interface DecisionService {

	Page<GbltOtpStudentRegTrn> findPaginated(int pageNo, int pageSize, String sortField, String sortDir,
			HttpServletRequest request);

	List<GbltOtpStudentRegTrn> allCurrentRegStudent(HttpServletRequest request);

	void saveGbltOtpStudentRegTrn(GbltStudentRegBean gbltStudentRegBean, HttpServletRequest request,
			HttpServletResponse response);

}
