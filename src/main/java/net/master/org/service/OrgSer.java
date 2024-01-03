package net.master.org.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;

import net.model.master.pojo.org.GbltOrgMst;
import net.model.master.pojo.org.GbltOrgUnitMst;

public interface OrgSer {

	Page<GbltOrgUnitMst> findPaginated(int pageNo, int pageSize, String sortField, String sortDir,HttpServletRequest request);

	void saveGbltOrgUnitMst(GbltOrgUnitMst batch, HttpServletRequest request, HttpServletResponse response);

	GbltOrgUnitMst getOrgUnitById(Integer id,HttpServletRequest request);

	void deleteOrgUnitById(GbltOrgUnitMst batch,HttpServletRequest request);

	List<GbltOrgMst> getOrgList(HttpServletRequest request);

}
