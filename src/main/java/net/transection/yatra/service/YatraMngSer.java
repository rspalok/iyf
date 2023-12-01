package net.transection.yatra.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import net.model.bean.YatraBean;
import net.model.master.pojo.facilitator.IYFFacilitatorMst;
import net.model.transection.pojo.yatra.YatraCruiseTrn;
import net.model.transection.pojo.yatra.YatraRegTrn;

public interface YatraMngSer {

	public Page<YatraCruiseTrn> findPaginated(int pageNo, int pageSize, String sortField, String sortDir,
			HttpServletRequest request);

	public List<YatraCruiseTrn> getYatraCruiseById(Long yatraCruiseId, HttpServletRequest request);

	public void registerStudentForTheYatra(YatraBean yatraBean, HttpServletRequest request,
			HttpServletResponse response);

	public List<YatraRegTrn> yatraRagisterdList(Long yatraCruiseId, HttpServletRequest request);

	public List<IYFFacilitatorMst> getfacilitatorList(HttpServletRequest request);

	public String studentByMobileNofromYatraTable(String studentId, Long yatraCruiseId, HttpServletRequest objRequest_p);

}
